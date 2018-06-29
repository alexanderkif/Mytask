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
        <!-- Dailog choose -->
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
                <v-btn v-if="!show" flat @click.native="show = true">Новый клиент</v-btn>
                <v-btn v-if="show" flat @click.native="show = false">Список клиентов</v-btn>
              </v-toolbar-items>
            </v-toolbar>
            <transition name="bounce" mode="out-in">
              <v-container v-if="!show" key="search">
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
              </v-container>
                <!-- switch -->
              <v-container v-if="show" key="newstrah">
                <v-layout row wrap mt-5>
                  <v-flex xs8 offset-xs2 text-xs-left>
                      НОВЫЙ СТРАХОВАТЕЛЬ
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
                      ></v-text-field>
                  </v-flex>
                  <v-flex xs4 sm2 offset-sm1>
                    <v-text-field
                      v-model.trim="firstname"
                      label="Имя"
                      ></v-text-field>
                  </v-flex>
                  <v-flex xs4 sm2 offset-sm1>
                    <v-text-field
                      v-model.trim="firstname2"
                      label="Отчество"
                      ></v-text-field>
                  </v-flex>
                </v-layout>
                <v-layout row wrap my-3>
                  <v-flex xs12 md2 offset-md2>
                    <v-menu
                        id="mbd1"
                        ref="menuBirthDate1"
                        :close-on-content-click="false"
                        v-model="menuBirthDate1"
                        :nudge-right="40"
                        lazy
                        transition="scale-transition"
                        offset-y
                        full-width
                        min-width="290px"
                    >
                        <v-text-field
                        id="1"
                        slot="activator"
                        v-model="birth"
                        label="Дата рождения"
                        prepend-icon="event"
                        readonly
                        :rules="[v => !!v || 'Обязательное поле']"
                        required
                        ></v-text-field>
                        <v-date-picker
                        id="11"
                        ref="picker1"
                        v-model="birth"
                        :max="new Date().toISOString().substr(0, 10)"
                        min="1950-01-01"
                        @change="saveBirthDate1"
                        ></v-date-picker>
                    </v-menu>
                  </v-flex>
                  <v-flex xs2 md2 offset-md1 pt-4 pr-3 text-xs-right>
                      Паспорт
                  </v-flex>
                  <v-flex xs4 md1>
                      <v-text-field
                      v-model="passportseria"
                      label="Серия"
                      :rules="[v => !!v || 'Обязательное поле']"
                      required
                      ></v-text-field>
                  </v-flex>
                  <v-flex xs6 md2>
                      <v-text-field
                      v-model="passportnumber"
                      label="Номер"
                      :rules="[v => !!v || 'Обязательное поле']"
                      required
                      ></v-text-field>
                  </v-flex>
                </v-layout>
                <v-layout row wrap my-3>
                  <v-flex xs6 text-xs-right pr-3>
                    <v-btn 
                    @click="createStrahovatel()"
                    >Сохранить</v-btn>
                  </v-flex>
                  <v-flex xs6 text-xs-left pl-3>
                    <v-btn @click="dialog = false"
                    >Отмена</v-btn>
                  </v-flex>
                </v-layout>
              </v-container>
            </transition>
          </v-card>
        </v-dialog>
      <!-- </v-flex>
      <v-flex xs12 md2 offset-md1 text-xs-center pt-3> -->
      <!-- Dailog change -->
        <v-dialog v-model="dialog2" max-width="800px">
          <v-container class="cont">
            <v-layout row wrap mt-5>
              <v-flex xs8 offset-xs2 text-xs-left>
                  ИЗМЕНЕНИЕ ДАННЫХ
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
                  v-model.trim="strahovatel.lastname"
                  label="Фамилия"
                  ></v-text-field>
              </v-flex>
              <v-flex xs4 sm2 offset-sm1>
                <v-text-field
                  v-model.trim="strahovatel.firstname"
                  label="Имя"
                  ></v-text-field>
              </v-flex>
              <v-flex xs4 sm2 offset-sm1>
                <v-text-field
                  v-model.trim="strahovatel.firstname2"
                  label="Отчество"
                  ></v-text-field>
              </v-flex>
            </v-layout>
            <v-layout row wrap my-3>
              <v-flex xs12 md2 offset-md2>
                <v-menu
                    id="mbd2"
                    ref="menuBirthDate2"
                    :close-on-content-click="false"
                    v-model="menuBirthDate2"
                    :nudge-right="40"
                    lazy
                    transition="scale-transition"
                    offset-y
                    full-width
                    min-width="290px"
                >
                    <v-text-field
                    id="2"
                    slot="activator"
                    v-model="strahovatel.birth"
                    label="Дата рождения"
                    prepend-icon="event"
                    readonly
                    :rules="[v => !!v || 'Обязательное поле']"
                    required
                    ></v-text-field>
                    <v-date-picker
                    id="22"
                    ref="picker2"
                    v-model="strahovatel.birth"
                    :max="new Date().toISOString().substr(0, 10)"
                    min="1950-01-01"
                    @change="saveBirthDate2"
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
            <v-layout row wrap my-3>
              <v-flex xs6 text-xs-right pr-3>
                <v-btn 
                @click="saveStrahovatel()"
                >Сохранить</v-btn>
              </v-flex>
              <v-flex xs6 text-xs-left pl-3>
                <v-btn @click="returnChangeStrahovatel()"
                >Отмена</v-btn>
              </v-flex>
            </v-layout>
          </v-container>
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
          <v-btn small @click="dialog2 = true">Изменить</v-btn>
      </v-flex>
    </v-layout>
    <v-layout row wrap>
        <v-flex xs12 md2 offset-md2>
            <!-- <v-menu
                id="mbd1"
                ref="menuBirthDate"
                :close-on-content-click="false"
                v-model="menuBirthDate"
                :nudge-right="40"
                lazy
                transition="scale-transition"
                offset-y
                full-width
                min-width="290px"
            > -->
                <v-text-field
                id="3"
                slot="activator"
                v-model="strahovatel.birth"
                label="Дата рождения"
                prepend-icon="event"
                readonly
                :rules="[v => !!v || 'Обязательное поле']"
                required
                ></v-text-field>
                <!-- <v-date-picker
                ref="picker"
                v-model="strahovatel.birth"
                :max="new Date().toISOString().substr(0, 10)"
                min="1950-01-01"
                @change="saveBirthDate"
                ></v-date-picker> -->
            <!-- </v-menu> -->
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
            readonly
            ></v-text-field>
        </v-flex>
        <v-flex xs6 md2>
            <v-text-field
            v-model="strahovatel.passportnumber"
            label="Номер"
            :rules="[v => !!v || 'Обязательное поле']"
            required
            readonly
            ></v-text-field>
        </v-flex>
    </v-layout>
  </div>
</template>

<script>
export default {
  name: 'Client',
  props: {
    selected: null
  },
  data () {
    return {
      menuBirthDate1: false,
      menuBirthDate2: false,
      dialog: false,
      dialog2: false,
      notifications: false,
      sound: true,
      widgets: false,
      lastname: '',
      firstname: '',
      firstname2: '',
      birth: null,
      passportseria: null,
      passportnumber: null,
      show: false,
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
    saveBirthDate1 (birth) {
      this.$refs.menuBirthDate1.save(birth)
    },
    saveBirthDate2 (birth) {
      this.$refs.menuBirthDate2.save(birth)
    },
    getStrahovatels () {
      this.$store.dispatch('getStrahovatels', this.lastname + '=' + this.firstname + '=' + this.firstname2)
    },
    openSelected (item) {
      this.dialog = false
      this.$store.dispatch('setStrahovatel', item)
    },
    createStrahovatel () {
      this.strahovatel.idstrah = null
      this.strahovatel.lastname = this.lastname
      this.strahovatel.firstname = this.firstname
      this.strahovatel.firstname2 = this.firstname2
      this.strahovatel.birth = this.birth
      this.strahovatel.passportseria = this.passportseria
      this.strahovatel.passportnumber = this.passportnumber
      this.dialog = false
      this.$store.dispatch('saveStrahovatel', this.strahovatel)
    },
    saveStrahovatel () {
      this.dialog = false
      this.dialog2 = false
      this.$store.dispatch('saveStrahovatel', this.strahovatel)
    },
    returnChangeStrahovatel () {
      this.dialog2 = false
      this.$store.dispatch('getStrahovatel', this.strahovatel.idstrah)
    }
  },
  watch: {
    menuBirthDate1 (val) {
      val && this.$nextTick(() => (this.$refs.picker1.activePicker = 'YEAR'))
    },
    menuBirthDate2 (val) {
      val && this.$nextTick(() => (this.$refs.picker2.activePicker = 'YEAR'))
    }
  }
}
</script>

<style scoped>

.bounce-enter-active {
  transition-delay: 1s;
  animation: bounce-in .5s;
}
.bounce-leave-active {
  animation: bounce-in .5s reverse;
}
.bounce2-enter-active {
  transition-delay: 1s;
  animation: bounce-in .5s;
}
.bounce2-leave-active {
  animation: bounce-in .5s reverse;
}
@keyframes bounce-in {
  0% {
    transform: scale(0);
  }
  70% {
    transform: scale(1.2);
  }
  100% {
    transform: scale(1);
  }
}
</style>
