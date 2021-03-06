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
    return axios.post(`https://my-app-no.herokuapp.com/chores/newChore/${choreRequest.id}`, {}, Http)
  },

  allChores (choresRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${choresRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.get('https://my-app-no.herokuapp.com/chores/allChores', Http)
  },

  deleteChore (deleteRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${deleteRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.delete(`https://my-app-no.herokuapp.com/chores/deleteChore/${deleteRequest.id}`, Http)
  }
}
