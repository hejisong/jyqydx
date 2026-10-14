import Vue from "vue"
import Vuex from "vuex"

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    companyId: '',
    userInfo: {},
    searchInfo: {},
    companyList: [],
    resultObj: {},
    searchid: '',
    category: ''
  },
  mutations: {
    saveCompanyId(state, id) {
      state.companyId = id
    },
    searchInfo(state, info) {
      state.searchInfo = info
    },
    userInfo(state, data) {
      state.userInfo = data
    },
    saveCompanyList(state, list) {
      state.companyList = list
    },
    saveResultObj(state, obj) {
      state.resultObj = obj
    },
    SaveSearchId(state, id) {
      state.searchid = id
    },
    SaveCategory(state, category) {
      state.category = category
    }
  }
})
