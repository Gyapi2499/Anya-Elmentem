import apiGroup from '../../../api/group-api'
import apiUser from '../../../api/user-api'

export default {
  getGroup ({ commit }, params) {
    console.log(params)
    apiGroup.getGroup(params).then(response => {
      commit('setGroup', response.data)
    }).catch(error => {
      console.log(error)
    })
  },
  getUsers ({ commit }, params) {
    apiUser.getUsers(params).then(response => {
      commit('setUsers', response.data)
    }).catch(error => {
      console.log(error)
    })
  }
}
