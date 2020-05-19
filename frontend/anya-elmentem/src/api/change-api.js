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
    return axios.post('http://localhost:8080/change/add', changeRequest.change, Http)
  },
  getChange (changeRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${changeRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.get('http://localhost:8080/change/getAll', Http)
  },
  accept (changeRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${changeRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.post(`http://localhost:8080/change/accept/${changeRequest.id}`, {}, Http)
  },
  decline (changeRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${changeRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.post(`http://localhost:8080/change/decline/${changeRequest.id}`, {}, Http)
  }
}
