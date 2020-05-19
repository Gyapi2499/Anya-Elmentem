import todoApi from '../../../api/todo-api'
import groupApi from '../../../api/group-api'

export default {
  getMemberList ({ commit }, params) {
    console.log(params)
    groupApi.getMemberList(params).then(response => {
      commit('setMemberList', response.data)
    }).catch(error => {
      console.log(error)
    })
  },
  getAGroups ({ commit }, params) {
    console.log(params)
    groupApi.getAGroups(params).then(response => {
      commit('setGroup', response.data)
    }).catch(error => {
      console.log(error)
    })
  },
  addtoTodo (commit, params) {
    return todoApi.addto(params)
  },
  nullGroup: ({ commit }) => {
    commit('setNullGroup')
  }
}
