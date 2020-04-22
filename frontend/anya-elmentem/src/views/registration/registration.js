import api from '../../api/user-api'

export default {
  namespaced: true,
  actions: {
    signup: ({ commit }, registerRequest) => {
      api.register(registerRequest).then((response) => {
        commit('setRegisteredUser', response.data)
      }).catch((error) => {
        console.error('Nem sikerült a regisztráció', { error, registerRequest })
        commit('setWasError', true)
      })
    }
  },
  mutations: {
    setRegisteredUser (state, registeredUser) {
      state.registeredUser = registeredUser
      state.wasError = false
    },
    setWasError (state, wasError) {
      state.wasError = wasError
    }
  },
  state: {
    registeredUser: Object,
    wasError: false
  }
}
