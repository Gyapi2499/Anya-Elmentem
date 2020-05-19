import apiGroup from '../../../api/group-api'
import apiUser from '../../../api/user-api'

export default {
  modifyGroup: ({ commit }, groupRequest) => {
    apiGroup.modifyGroup(groupRequest).then((response) => {
      commit('setModGroup', response.data)
    }).catch((error) => {
      console.error('Nem sikerült a módosítás', { error })
    })
  },
  getGroup ({ commit }, params) {
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
  },
  addMembers ({ commit }, params) {
    apiGroup.addMembers(params).then(response => {
      commit('addMembers', response.data)
    }).catch(error => {
      console.log(error)
    })
  },
  delMembers ({ commit }, params) {
    apiGroup.delMembers(params).then(response => {
      commit('delMembers', response.data)
    }).catch(error => {
      console.log(error)
    })
  },
  addAdmin ({ commit }, params) {
    apiGroup.addAdmin(params).then(response => {
      commit('addAdmin', response.data)
    }).catch(error => {
      console.log(error)
    })
  },
  delAdmin ({ commit }, params) {
    apiGroup.delAdmin(params).then(response => {
      commit('delAdmin', response.data)
    }).catch(error => {
      console.log(error)
    })
  }
}
