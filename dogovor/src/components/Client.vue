<template>
  <div>
    <v-layout row wrap mt-5>
      <v-flex xs10 offset-xs1 text-xs-left>
          СТРАХОВАТЕЛЬ
      </v-flex>
    </v-layout>
    <v-layout row wrap>
      <v-flex xs12 md10 offset-md1>
          <v-divider></v-divider>
      </v-flex>
    </v-layout>
    <v-layout row wrap mt-3>
      <v-flex xs12 md2 offset-md1 text-xs-center pt-3>
          <!-- <v-btn small @click="choose">Выбрать</v-btn> -->
           <v-dialog v-model="dialog" fullscreen hide-overlay transition="dialog-bottom-transition">
            <v-btn slot="activator" small @click="getStrahovatels">Выбрать</v-btn>
            <v-card>
              <v-toolbar >
                <v-btn icon @click.native="dialog = false">
                  <v-icon>close</v-icon>
                </v-btn>
                <v-toolbar-title>Выбор страхователя</v-toolbar-title>
                <v-spacer></v-spacer>
                <v-toolbar-items>
                  <v-btn flat @click.native="dialog = false">Новый клиент</v-btn>
                </v-toolbar-items>
              </v-toolbar>
              <v-layout row wrap mt-5>
                <v-flex xs8 offset-xs2 text-xs-left>
                    ПОИСК СТРАХОВАТЕЛЯ
                </v-flex>
              </v-layout>
              <v-layout row wrap>
                <v-flex xs12 sm8 offset-sm2>
                    <v-divider></v-divider>
                </v-flex>
              </v-layout>
              <v-layout row wrap my-3>
                <v-flex xs4 sm2 offset-sm2>
                  <v-text-field
                    v-model.trim="lastname"
                    label="Фамилия"
                    @change="getStrahovatels"
                    ></v-text-field>
                </v-flex>
                <v-flex xs4 sm2 offset-sm1>
                  <v-text-field
                    v-model.trim="firstname"
                    label="Имя"
                    @change="getStrahovatels"
                    ></v-text-field>
                </v-flex>
                <v-flex xs4 sm2 offset-sm1>
                  <v-text-field
                    v-model.trim="firstname2"
                    label="Отчество"
                    @change="getStrahovatels"
                    ></v-text-field>
                </v-flex>
              </v-layout>
              <v-layout row wrap>
                <v-flex xs12 md10 offset-md1 mb-5>
                <v-data-table
                    v-model="selected"
                    :headers="headers"
                    :items="strahovatels"
                    item-key="iddog"
                    class="elevation-1"
                >
                    <template slot="items" slot-scope="props">
                    <tr :active="props.selected" @click="openSelected(props.item)">
                    <td class="text-xs-right">{{ props.item.lastname }} {{ props.item.firstname }} {{ props.item.firstname2 }}</td>
                    <td class="text-xs-center">{{ props.item.birth }}</td>
                    <td class="text-xs-left">{{ props.item.passportseria }} - {{ props.item.passportnumber }}</td>
                    </tr>
                    </template>
                </v-data-table>
                </v-flex>
              </v-layout>
              <!-- <v-layout v-if="caseToShow === 'change'" row wrap my-5>
                <v-flex xs12 md10 offset-md1>
                  Изменить
                </v-flex>
              </v-layout> -->
            </v-card>
          </v-dialog>
      </v-flex>
      <v-flex xs12 md6>
          <v-text-field
          v-model="fio"
          label="ФИО"
          prepend-icon="person"
          :rules="[v => !!v || 'Обязательное поле']"
          readonly
          ></v-text-field>
      </v-flex>
      <v-flex xs12 md2 text-xs-center pt-3>
          <v-btn small>Изменить</v-btn>
      </v-flex>
    </v-layout>
    <v-layout row wrap>
        <v-flex xs12 md2 offset-md2>
            <v-menu
                ref="menuBirthDate"
                :close-on-content-click="false"
                v-model="menuBirthDate"
                :nudge-right="40"
                lazy
                transition="scale-transition"
                offset-y
                full-width
                min-width="290px"
            >
                <v-text-field
                slot="activator"
                v-model="strahovatel.birth"
                label="Дата рождения"
                prepend-icon="event"
                readonly
                :rules="[v => !!v || 'Обязательное поле']"
                required
                ></v-text-field>
                <v-date-picker
                ref="picker"
                v-model="strahovatel.birth"
                :max="new Date().toISOString().substr(0, 10)"
                min="1950-01-01"
                @change="saveBirthDate"
                ></v-date-picker>
            </v-menu>
        </v-flex>
        <v-flex xs2 md2 offset-md1 pt-4 pr-3 text-xs-right>
            Паспорт
        </v-flex>
        <v-flex xs4 md1>
            <v-text-field
            v-model="strahovatel.passportseria"
            label="Серия"
            :rules="[v => !!v || 'Обязательное поле']"
            required
            ></v-text-field>
        </v-flex>
        <v-flex xs6 md2>
            <v-text-field
            v-model="strahovatel.passportnumber"
            label="Номер"
            :rules="[v => !!v || 'Обязательное поле']"
            required
            ></v-text-field>
        </v-flex>
    </v-layout> 
  </div> 
</template>

<script>
export default {
  name: 'Client',
  data () {
    return {
      menuBirthDate: false,
      dialog: false,
      notifications: false,
      sound: true,
      widgets: false,
      lastname: '',
      firstname: '',
      firstname2: '',
      headers: [
        {
          text: 'ФИО',
          align: 'right',
          // sortable: false,
          value: 'idstrah'
        },
        { text: 'Дата рождения', align: 'center', value: 'regdate' },
        { text: 'Паспорт', align: 'left', value: 'passport' }
      ]
    }
  },
  computed: {
    strahovatel () {
      return this.$store.getters.getStrahovatel
    },
    fio () {
      if (this.strahovatel.lastname) {
        return this.strahovatel.lastname + ' ' + this.strahovatel.firstname + ' ' + this.strahovatel.firstname2
      } else {
        return ''
      }
    },
    strahovatels () {
      return this.$store.getters.getStrahovatels
    }
  },
  methods: {
    saveBirthDate (birth) {
      this.$refs.menuBirthDate.save(birth)
    },
    getStrahovatels () {
      this.$store.dispatch('getStrahovatels', this.lastname + '=' + this.firstname + '=' + this.firstname2)
    },
    openSelected (item) {
      this.$store.dispatch('setStrahovatel', item)
      this.dialog = false
    }
  },
  watch: {
    menuBirthDate (val) {
      val && this.$nextTick(() => (this.$refs.picker.activePicker = 'YEAR'))
    }
  }
}
</script>
