import axios from 'axios'

export default {
  newChange (changeRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${changeRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.post('https://my-app-no.herokuapp.com/change/add', changeRequest.change, Http)
  },
  getChange (changeRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${changeRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.get('https://my-app-no.herokuapp.com/change/getAll', Http)
  },
  accept (changeRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${changeRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.post(`https://my-app-no.herokuapp.com/change/accept/${changeRequest.id}`, {}, Http)
  },
  decline (changeRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${changeRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.post(`https://my-app-no.herokuapp.com/change/decline/${changeRequest.id}`, {}, Http)
  }
}
