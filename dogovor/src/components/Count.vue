<template>
    <v-form ref="form" v-model="valid" lazy-validation>
        <v-layout row wrap mt-5>
            <v-flex xs10 offset-xs1 text-xs-left>
                РАСЧЕТ
            </v-flex>
        </v-layout>
        <v-layout row wrap>
            <v-flex xs12 md10 offset-md1>
                <v-divider></v-divider>
            </v-flex>
        </v-layout>
        <v-layout row wrap mt-3>
            <v-flex xs12 md3 offset-md1>
                    <v-text-field
                    v-model.trim="dogovor.strsumma"
                    :rules="strSummRules"
                    label="Страховая сумма"
                    required
                    ></v-text-field>
            </v-flex>
            <v-flex xs6 md3 offset-md1>
                <v-menu
                    ref="startDateMenu"
                    :close-on-content-click="false"
                    v-model="startDateMenu"
                    :nudge-right="40"
                    :return-value.sync="dogovor.startdate"
                    lazy
                    transition="scale-transition"
                    offset-y
                    full-width
                    min-width="290px"
                >
                    <v-text-field
                    slot="activator"
                    v-model="dogovor.startdate"
                    label="Срок действия с"
                    prepend-icon="event"
                    :rules="StartDateRules"
                    required
                    ></v-text-field>
                    <v-date-picker v-model="dogovor.startdate" @input="$refs.startDateMenu.save(dogovor.startdate)" 
                    :first-day-of-week="1" locale="ru-ru" :min="today()"></v-date-picker>

                </v-menu>
            </v-flex>
            <v-flex xs6 md3>
                <v-menu
                    ref="endDateMenu"
                    :close-on-content-click="false"
                    v-model="endDateMenu"
                    :nudge-right="40"
                    :return-value.sync="dogovor.enddate"
                    transition="scale-transition"
                    offset-y
                    full-width
                    min-width="290px"
                >
                    <v-text-field
                    slot="activator"
                    v-model="dogovor.enddate"
                    label="Срок действия по"
                    prepend-icon="event"
                    :rules="DateRules"
                    required
                    @input="$refs.endDateMenu.save(dogovor.enddate)"
                    ></v-text-field>
                    <v-date-picker v-model="dogovor.enddate" @input="$refs.endDateMenu.save(dogovor.enddate)" 
                    :first-day-of-week="1" locale="ru-ru" :min="minDate()" :max="maxDate()"></v-date-picker>
                </v-menu>
            </v-flex>
        </v-layout>
        <v-layout row wrap>
            <v-flex xs12 md3 offset-md1>
                <v-select
                v-model="dogovor.type"
                :items="typesObject"
                :rules="[v => !!v || 'Обязательное поле']"
                label="Тип недвижимости"
                required
                ></v-select>
            </v-flex>
        </v-layout>
        <v-layout row wrap>
            <v-flex xs12 md3 offset-md1>
                <v-select
                id="yearId"
                v-model="dogovor.year"
                :items="yearsDateObject()"
                :rules="[v => !!v || 'Обязательное поле']"
                label="Год постройки"
                required
                ></v-select>
            </v-flex>
        </v-layout>
        <v-layout row wrap>
            <v-flex xs12 md3 offset-md1>
                <v-text-field
                id="squareId"
                v-model="dogovor.square"
                :rules="squareRules"
                label="Площадь, кв.м"
                required
                ></v-text-field>
            </v-flex>
        </v-layout>
        <v-layout row wrap>
            <v-flex xs12 center>
                <v-btn 
                :disabled="validForm"
                @click="getCount()">
                Рассчитать
                </v-btn>                    
            </v-flex>
        </v-layout>
        <v-layout row wrap>
            <v-flex xs6 md3 offset-md1>
                <v-text-field
                v-model="dogovor.countdate"
                label="Дата расчета"
                prepend-icon="event"
                readonly
                ></v-text-field>
            </v-flex>
            <v-flex xs6 md3 offset-md4>
                <v-text-field
                v-model="dogovor.bonus"
                label="Премия"
                prepend-icon="attach_money"
                readonly
                ></v-text-field>
            </v-flex>
        </v-layout>
    </v-form>
</template>

<script>
export default {
  name: 'Count',
  data () {
    return {
      valid: true,
      strSummRules: [
        v => !!v || 'Обязательное поле',
        v => (v && !/^0|\D/.test(v)) || 'Введите целое число'
      ],
      squareRules: [
        v => !!v || 'Обязательное поле',
        v => (v && !/^0/.test(v)) || 'Числа не начинаются с 0',
        v => (v && /\d+\.\d$/.test(v)) || 'Введите число с одним знаком после точки'
      ],
      StartDateRules: [
        v => !!v || 'Обязательное поле',
        v => (v && /^(19|20)\d\d-((0[1-9]|1[012])-(0[1-9]|[12]\d)|(0[13-9]|1[012])-30|(0[13578]|1[02])-31)$/.test(v)) || 'с 1900-01-01 по 2099-12-31'
      ],
      DateRules: [
        v => !!v || 'Обязательное поле',
        v => (v && /^(19|20)\d\d-((0[1-9]|1[012])-(0[1-9]|[12]\d)|(0[13-9]|1[012])-30|(0[13578]|1[02])-31)$/.test(v)) || 'с 1900-01-01 по 2099-12-31'
      ],
      typesObject: [
        'Квартира',
        'Дом',
        'Комната'
      ],
      startDateMenu: false,
      endDateMenu: false
    }
  },
  methods: {
    changeStartDate () {
      this.dogovor.enddate = null
    },
    today () {
      return new Date().toISOString().substr(0, 10)
    },
    minDate () {
      if (this.dogovor.startdate) {
        const [year, month, day] = this.dogovor.startdate.split('-')
        var d = new Date(parseInt(year), parseInt(month) - 1, parseInt(day) + 2)
        return d.toISOString().substr(0, 10)
      } else {
        return null
      }
    },
    maxDate () {
      if (this.dogovor.startdate) {
        const [year, month, day] = this.dogovor.startdate.split('-')
        var d = new Date(parseInt(year) + 1, parseInt(month) - 1, parseInt(day))
        return d.toISOString().substr(0, 10)
      } else {
        return null
      }
    },
    yearsDateObject () {
      var fromYear = 1900
      var arr = []
      for (let i = parseInt(new Date().toISOString().substr(0, 4)); i > fromYear; i--) {
        arr.push(i.toString())
      }
      return arr
    },
    getCount () {
      if ((Date.parse(this.dogovor.startdate) - (Date.parse(new Date().toISOString().substr(0, 10)))) < 0) {
        this.dogovor.startdate = new Date().toISOString().substr(0, 10)
        this.dogovor.enddate = null
        return null
      }

      if ((Date.parse(this.dogovor.enddate) - Date.parse(this.dogovor.startdate)) <= 0) {
        this.dogovor.enddate = null
        return null
      }

      this.dogovor.countdate = new Date().toISOString().substr(0, 10)

      if (this.$refs.form.validate()) {
        var KTH
        switch (this.dogovor.type) {
          case 'Квартира':
            KTH = 1.7
            break
          case 'Дом':
            KTH = 1.5
            break
          default:
            KTH = 1.3
            break
        }
        var KGP
        if (this.dogovor.year >= 2015) {
          KGP = 2
        } else {
          if (this.dogovor.year >= 2000) {
            KGP = 1.6
          } else {
            KGP = 1.3
          }
        }
        var KS
        if (this.dogovor.square > 100) {
          KS = 2
        } else {
          if (this.dogovor.square >= 50) {
            KS = 1.5
          } else {
            KS = 1.2
          }
        }
        var days = (Date.parse(this.dogovor.enddate) - Date.parse(this.dogovor.startdate)) / (1000 * 60 * 60 * 24)
        this.dogovor.bonus = Math.round((this.dogovor.strsumma / days) * KTH * KGP * KS * 100) / 100
        this.dogovor.countdate = new Date().toISOString().substr(0, 10)
      }
    }
  },
  computed: {
    validForm () {
      this.$store.dispatch('setValidCount', !this.valid)
      return !this.valid
    },
    dogovor () {
      return this.$store.getters.getDogovor
    }
  },
  created () {
    this.startDateMenu = true
    this.startDateMenu = false
  }
}
</script>
