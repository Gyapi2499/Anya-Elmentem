import Vue from 'vue'
import Vuex from 'vuex'
import newTodo from '../views/newTodo/newTodo'
import Registration from '../views/registration/registration.js'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    newTodo,
    Registration
  }
})
