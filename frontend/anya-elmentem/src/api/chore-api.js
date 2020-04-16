import axios from 'axios'

export default {

   newChore(choreRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${choreRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.post(`http://localhost:8080/chores/newChore/${choreRequest.id}`, {}, Http)
  },

  allChores (choresRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${choresRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.get('http://localhost:8080/chores/allChores', Http)
  },

  deleteChore (deleteRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${deleteRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.delete(`http://localhost:8080/chores/deleteChore/${deleteRequest.id}`, Http)
  }
}
