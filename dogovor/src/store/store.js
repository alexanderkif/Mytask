import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)
const SERVER_URL = 'http://dogov.herokuapp.com'
// const SERVER_URL = 'http://localhost'

export default new Vuex.Store({
  state: {
    validCount: false,
    dogovor: {},
    strahovatel: {},
    dogovors: [],
    strahovatels: [],
    showOneDogovor: false,
    dogovorExist: true,
    strahovatelExist: true
  },
  getters: {
    getValidCount: state => state.validCount,
    getDogovor: state => state.dogovor,
    getStrahovatel: state => state.strahovatel,
    getAllDogovors: state => state.dogovors,
    getShowOneDogovor: state => state.showOneDogovor,
    getStrahovatels: state => state.strahovatels,
    isDogovorExist: state => state.dogovorExist
  },
  actions: {
    checkDogovor (context, iddog) {
      axios
      .get(SERVER_URL + '/api/getdogovor/' + iddog)
      .then(response => {
        if (response.data.iddog) {
          context.commit('checkDogovor', true)
        } else {
          context.commit('checkDogovor', false)
        }
      })
      .catch(e => { console.log(e) })
    },
    saveStrahovatel (context, strahovatel) {
      if (strahovatel.idstrah) {
        axios
        .post(SERVER_URL + '/api/updatestrahovatel/' + strahovatel.idstrah, strahovatel)
        .catch(e => { console.log(e) })
      } else {
        axios
        .post(SERVER_URL + '/api/savestrahovatel', strahovatel)
        .catch(e => { console.log(e) })
      }
      // context.commit('saveStrahovatel', strahovatel)
    },
    saveDogovor (context, dogovor) {
      if (dogovor.iddog) {
        axios
        .post(SERVER_URL + '/api/updatedogovor/' + dogovor.iddog, dogovor)
        .catch(e => { console.log(e) })
      } else {
        axios
        .post(SERVER_URL + '/api/savedogovor', dogovor)
        .catch(e => { console.log(e) })
      }
    },
    createDogovor (context) {
      context.commit('setDogovor', {})
      context.commit('setStrahovatel', {})
    },
    setDogovor (context, dogovor) {
      context.commit('setDogovor', dogovor)
    },
    setStrahovatel (context, strahovatel) {
      context.commit('setStrahovatel', strahovatel)
    },
    setShowOneDogovor (context, value) {
      context.commit('setShowOneDogovor', value)
    },
    getDogovors (context) {
      axios
      .get(SERVER_URL + '/api/getdogovors') // TO DO status 200
      .then(response => {
        response.data.forEach(dogovor => {
          dogovor.startdate = dogovor.startdate.substr(0, 10)
          dogovor.enddate = dogovor.enddate.substr(0, 10)
          dogovor.regdate = dogovor.regdate.substr(0, 10)
          dogovor.countdate = dogovor.countdate.substr(0, 10)
        })
        context.commit('getDogovors', response.data)
      })
      .catch(e => { console.log(e) })
    },
    setNewRegDate (context) {
      context.commit('setNewRegDate')
    },
    setNewStartDate (context) {
      context.commit('setNewStartDate')
    },
    setValidCount (context, valid) {
      context.commit('setValidCount', valid)
    },
    searchDogovor (context, iddog) {
      axios
      .get(SERVER_URL + '/api/getdogovor/' + iddog) // TO DO status 200
      .then(response => {
        var dogovor = response.data
        dogovor.startdate = response.data.startdate.substr(0, 10)
        dogovor.enddate = response.data.enddate.substr(0, 10)
        dogovor.countdate = response.data.countdate.substr(0, 10)
        dogovor.regdate = response.data.regdate.substr(0, 10)
        context.commit('setDogovor', dogovor)
      })
      .catch(e => { console.log(e) })
    },
    getStrahovatel (context, strahovatelid) {
      if (strahovatelid) {
        axios
        .get(SERVER_URL + '/api/getstrahovatel/' + strahovatelid)
        .then(response => {
          var strahovatel = response.data
          strahovatel.birth = response.data.birth.substr(0, 10)
          context.commit('setStrahovatel', strahovatel)
        })
        .catch(e => { console.log(e) })
      }
    },
    getStrahovatels (context, fio) {
      var lastname = fio.split('=')[0]
      var firstname = fio.split('=')[1]
      var firstname2 = fio.split('=')[2]
      axios
      .get(SERVER_URL + '/api/getstrahovatels/?lastname=' + lastname + '&firstname=' + firstname + '&firstname2=' + firstname2)
      .then(response => {
        response.data.forEach(strahovatel => {
          strahovatel.birth = strahovatel.birth.substr(0, 10)
        })
        context.commit('getStrahovatels', response.data)
      })
      .catch(e => { console.log(e) })
    }
  },
  mutations: {
    checkDogovor (state, status) {
      state.dogovorExist = status
    },
    setDogovor (state, dogovor) {
      state.dogovor = dogovor
    },
    setShowOneDogovor (state, value) {
      state.showOneDogovor = value
    },
    getDogovors (state, dogovors) {
      state.dogovors = dogovors
    },
    setNewRegDate (state) {
      state.dogovor.regdate = new Date().toISOString().substr(0, 10)
    },
    setNewStartDate (state) {
      state.dogovor.startdate = new Date().toISOString().substr(0, 10)
    },
    setValidCount (state, valid) {
      state.validCount = valid
    },
    setStrahovatel (state, strahovatel) {
      state.strahovatel = strahovatel
    },
    getStrahovatels (state, strahovatels) {
      state.strahovatels = strahovatels
    }
  }
})
