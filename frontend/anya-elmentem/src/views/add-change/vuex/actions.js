import groupApi from '../../../api/group-api'
import todoApi from '../../../api/todo-api'
import changeApi from '../../../api/change-api'
import router from '../../../router'

export default {
  getGroups ({ commit }, params) {
    groupApi.getGroups(params).then(response => {
      console.log(response.data)
      commit('setGroups', response.data)
    }).catch(error => {
      console.log(error)
    })
  },
  getMemberList ({ commit }, params) {
    console.log(params)
    groupApi.getMemberList(params).then(response => {
      commit('setMemberList', response.data)
    }).catch(error => {
      console.log(error)
    })
  },
  getMine ({ commit }, params) {
    todoApi.getAll(params).then(response => {
      commit('setMyTodos', response.data)
    }).catch(error => {
      console.log(error)
    })
  },
  getOthers ({ commit }, params) {
    todoApi.getAll(params).then(response => {
      commit('setOtherTodos', response.data)
    }).catch(error => {
      console.log(error)
    })
  },
  save (commit, params) {
    changeApi.newChange(params).then(response => {
      router.push({ name: 'Home' })
    }).catch(error => {
      console.log(error)
    })
  }
}
