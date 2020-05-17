import toDoApi from '../../../api/todo-api'
import groupApi from '../../../api/group-api'
import userApi from '../../../api/user-api'
export default {
  getToDos ({ commit }, params) {
    toDoApi.getAll({ token: params.token, email: params.user.email }).then(response => {
      var events = []
      response.data.forEach(item => {
        console.log(item)
        var startDate = item.fromDate.substring(0, 10) + ' ' + item.fromDate.substring(11, 16)
        var endDate = item.toDate.substring(0, 10) + ' ' + item.toDate.substring(11, 16)
        events.push({ start: startDate, end: endDate, title: item.chores })
      })
      commit('setToDos', events)
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
  getGroup ({ commit }, params) {
    console.log(params)
    userApi.getGroup(params).then(response => {
      commit('setGroup', response.data)
    }).catch(error => {
      console.log(error)
    })
  }
}
