import Vue from 'vue'
import Vuex from 'vuex'
import addGroup from '../views/addGroup/addGroup'
import newTodo from '../views/newTodo/newTodo'
import Registration from '../views/registration/registration.js'
import api from '../api/user-api.js'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    logUser: {},
    successfulLogin: false
  },
  mutations: {
    setLogUser (state, user) {
      state.logUser = user
      state.successfulLogin = true
    }
  },
  actions: {
    login: ({ commit }, loginRequest) => {
      api.login(loginRequest).then((response) => {
        commit('setLogUser', response.data)
      }).catch((error) => {
        console.error('Nem sikerült a bejelentkezés', { error, loginRequest })
      })
    }
  },
  modules: {
    addGroup,
    newTodo,
    Registration
  }
})
