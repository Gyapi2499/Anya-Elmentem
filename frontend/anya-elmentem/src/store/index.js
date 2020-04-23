import Vue from 'vue'
import Vuex from 'vuex'
import addGroup from '../views/addGroup/addGroup'
import newTodo from '../views/newTodo/newTodo'
import Registration from '../views/registration/registration.js'
import api from '../api/user-api.js'
import home from '../views/home/home'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    logUser: {},
    successfulLogin: false,
    token: ''
  },
  mutations: {
    setLogUser (state, user) {
      state.logUser = user
      state.successfulLogin = true
    },
    setLogout (state) {
      state.logUser = {}
      state.token = ''
      state.successfulLogin = false
    },
    setToken (state, token) {
      state.token = token
    }
  },
  actions: {
    login: ({ commit }, loginRequest) => {
      api.login(loginRequest).then((response) => {
        commit('setToken', btoa(`${loginRequest.email}:${loginRequest.password}`))
        commit('setLogUser', response.data)
      }).catch((error) => {
        console.error('Nem sikerült a bejelentkezés', { error, loginRequest })
      })
    },
    logout: ({ commit }) => {
      commit('setLogout')
    }
  },
  modules: {
    addGroup,
    newTodo,
    Registration,
    home
  }
})
