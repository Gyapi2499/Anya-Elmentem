import toDoApi from '../../../api/todo-api'
export default {
  getToDos ({ commit }, params) {
    toDoApi.getAll({ token: params.token, email: params.user.email }).then(response => {
      var events = []
      response.data.forEach(item => {
        console.log(item)
        var startDate = item.fromDate.substring(0, 10) + ' ' + item.fromDate.substring(11, 16)
        var endDate = item.toDate.substring(0, 10) + ' ' + item.toDate.substring(11, 16)
        events.push({ start: startDate, end: endDate, title: item.chores, todoId: item.id, ready: item.ready })
      })
      commit('setToDos', events)
    }).catch(error => {
      console.log(error)
    })
  },
  readyOrNot (commit, params) {
    return toDoApi.readyOrNot(params)
  }
}
