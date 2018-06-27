<template>
  <v-app :dark="dark">
    <v-navigation-drawer 
    fixed
    clipped
    v-model="drawer" 
    app>
    <v-container text-xs-center>
      <v-layout row wrap my-3>
        <v-flex xs10 offset-xs1 text-xs-left>
            <v-btn @click="createDogovor"
            >Создать договор</v-btn>
        </v-flex>
      </v-layout>
      <v-layout row wrap my-3>
          <v-flex xs10 offset-xs1 text-xs-left>
              <v-text-field
              v-model.trim="dogovorSearch"
              :rules="dogovorSearchRules"
              label="Поиск договора"
              prepend-icon="search"
              @input="getDogovor"
              ></v-text-field>
          </v-flex>
      </v-layout>
    </v-container>
    </v-navigation-drawer>
    <v-toolbar 
    dense
    fixed
    clipped-left
    app>
      <v-toolbar-side-icon @click.stop="drawer = !drawer"></v-toolbar-side-icon>
    </v-toolbar>
    <v-content>
      <transition name="slide-fade" mode="out-in">
      <v-container v-if="!showOneDogovor" grid-list-md text-xs-center class="mt-3" key="table">

        <Dtable/>

      </v-container>
      <!-- 

      switch  

       -->
      <v-container v-if="showOneDogovor" grid-list-md text-xs-center class="mt-3" key="dogovor">
        <v-form ref="mainForm" v-model="validMainForm" lazy-validation>

        <Count/>

        <v-layout row wrap>
            <v-flex xs12 md10 offset-md1>
                <v-divider></v-divider>
            </v-flex>
        </v-layout>
        <v-layout row wrap mt-5>
            <v-flex xs6 md3 offset-md1>
                    <v-text-field
                    v-model="dogovor.iddog"
                    :rules="dogovorNumberRules"
                    label="Номер договора"
                    required
                    ></v-text-field>
            </v-flex>
            <v-flex xs6 md3 offset-md4>
                    <v-text-field
                    v-model="dogovor.regdate"
                    label="Дата заключения"
                    :rules="[v => !!v || 'Обязательное поле']"
                    prepend-icon="event"
                    readonly
                    ></v-text-field>
            </v-flex>
        </v-layout>
        
        <Client/>

        <Estate/>

        <v-layout row wrap mt-5>
          <v-flex xs10 offset-xs1 text-xs-left>
            КОММЕНТАРИЙ
          </v-flex>
        </v-layout>
        <v-layout row wrap>
          <v-flex xs12 md10 offset-md1>
            <v-divider></v-divider>
          </v-flex>
        </v-layout>
        <v-layout row wrap>
          <v-flex xs3 md2 offset-md1 pt-5>   
            <v-subheader>Комментарий к договору (не печатается на полисе)</v-subheader>
          </v-flex>
          <v-flex xs9 md8>   
            <v-text-field
            v-model="dogovor.comment"
            name="input-7-1"
            label="Комментарий"
            multi-line
            ></v-text-field>
          </v-flex>
        </v-layout>  
        <v-layout row wrap mb-5>
          <v-flex xs6 text-xs-right pr-3>
            <v-btn 
            :disabled="isValidCount"
            @click="dogovorSave()"
            >Сохранить</v-btn>
          </v-flex>
          <v-flex xs6 text-xs-left pl-3>
            <v-btn @click="toTheDogovorsList"
            >К списку договоров</v-btn>
          </v-flex>
        </v-layout>
        </v-form>      
      </v-container>
      </transition>
    </v-content>
    <v-footer app class="mt-3 py-4">
      <v-btn
      absolute
      dark
      fab
      top
      right
      @click="dark=!dark"
      >
      <v-icon>invert_colors</v-icon>
      </v-btn>
    </v-footer>
  </v-app>
</template>

<script>
import Count from './components/Count'
import Client from './components/Client'
import Estate from './components/Estate'
import Dtable from './components/Dtable'
export default {
  components: {
    Dtable,
    Count,
    Client,
    Estate
  },
  computed: {
    strahovatel () {
      return this.$store.getters.getStrahovatel
    },
    isValidCount () {
      return (!this.validMainForm || this.$store.getters.getValidCount)
    },
    dogovor () {
      this.$store.dispatch('getStrahovatel', this.$store.getters.getDogovor.strahovatelid)
      return this.$store.getters.getDogovor
    },
    showOneDogovor () {
      return this.$store.getters.getShowOneDogovor
    }
  },
  data () {
    return {
      dark: false,
      drawer: false,
      dogovorSearch: null,
      // showOneDogovor: false,
      validMainForm: false,
      dogovorNumberRules: [
        v => !!v || 'Обязательное поле',
        v => (v && /^[1-9]\d{5}$/.test(v)) || 'Введите 6 цифр'
      ],
      dogovorSearchRules: [
        v => (!v || /^[1-9]\d{5}$/.test(v)) || 'Введите 6 цифр'
      ]
    }
  },
  mounted () {
    // alert('!this.dogovor.regdate ' + !this.dogovor.regdate + ', !this.dogovor.startdate ' + !this.dogovor.startdate)
    // if (!this.dogovor.regdate) {
    //   this.$store.dispatch('setNewRegDate')
    // }
    // if (!this.dogovor.startdate) {
    //   this.$store.dispatch('setNewStartDate')
    // }
  },
  methods: {
    createDogovor () {
      this.$store.dispatch('createDogovor')
      this.$store.dispatch('setShowOneDogovor', true)
      if (!this.dogovor.regdate) {
        this.$store.dispatch('setNewRegDate')
      }
      if (!this.dogovor.startdate) {
        this.$store.dispatch('setNewStartDate')
      }
    },
    getDogovor () {
      if (this.dogovorSearch.length === 6 && /^[1-9]\d{5}$/.test(this.dogovorSearch)) {
        this.$store.dispatch('searchDogovor', this.dogovorSearch)
        this.$store.dispatch('setShowOneDogovor', true)
      }
    },
    dogovorSave () {
      this.validMainForm = this.$refs.mainForm.validate()
      if (!this.isValidCount) {
        this.dogovor.strahovatelid = this.strahovatel.idstrah
        this.dogovor.fullname = this.strahovatel.lastname + ' ' + this.strahovatel.firstname + ' ' + this.strahovatel.firstname2
        this.$store.dispatch('saveDogovor', this.dogovor)
        this.$store.dispatch('setShowOneDogovor', false)
      } else {
        console.log('this.isValidCount ' + this.isValidCount)
        alert('Не сохранено')
      }
    },
    toTheDogovorsList () {
      this.$store.dispatch('setShowOneDogovor', false)
    }
  },
  created () {
    this.startDateMenu = true
    this.startDateMenu = false
  },
  name: 'App'
}
</script>

<style>
.slide-fade-enter-active {
  transition: all .3s ease;
}
.slide-fade-leave-active {
  transition: all .3s cubic-bezier(1.0, 0.5, 0.8, 1.0);
}
.slide-fade-enter {
  transform: translateX(-500px);
  opacity: 0;
}

.slide-fade-leave-to {
  transform: translateX(500px);
  opacity: 0;
}
</style>
