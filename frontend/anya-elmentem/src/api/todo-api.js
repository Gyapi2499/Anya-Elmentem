import axios from 'axios'

export default {

  add (todoRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${todoRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.post('https://my-app-no.herokuapp.com/todo/add', {
      userId: todoRequest.userId,
      chores: todoRequest.chores,
      fromDate: todoRequest.fromDate,
      toDate: todoRequest.toDate,
      groupId: todoRequest.groupId,
      ready: false
    }, Http)
  },
  addto (todoRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${todoRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.post('https://my-app-no.herokuapp.com/todo/addto', {
      userId: todoRequest.userId,
      chores: todoRequest.chores,
      fromDate: todoRequest.fromDate,
      toDate: todoRequest.toDate,
      groupId: todoRequest.groupId,
      ready: false
    }, Http)
  },

  getWeek (weekRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${weekRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.get(`https://my-app-no.herokuapp.com/todo/getWeek/${weekRequest.userId}`, Http)
  },

  readyOrNot (readyOrNotRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${readyOrNotRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.post(`https://my-app-no.herokuapp.com/todo/readyOrNot/${readyOrNotRequest.id}`, {}, Http)
  },

  deleteTodo (deleteRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${deleteRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.delete(`https://my-app-no.herokuapp.com/todo/delete/${deleteRequest.id}`, Http)
  },

  getAll (request) {
    const Http = {
      headers: {
        Authorization: `Basic ${request.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.get(`https://my-app-no.herokuapp.com/todo/toDos/${request.email}`, Http)
  }

}
