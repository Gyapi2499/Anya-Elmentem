import apiGroup from '../../../api/group-api'
import router from '../../../router/index'
import apiUser from '../../../api/user-api'

export default {
  addGroup ({ commit }, params) {
    apiGroup.newGroup(params).then(response => {
      router.push({
        name: 'group',
        params: {
          id: response.data.id
        }
      })
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
