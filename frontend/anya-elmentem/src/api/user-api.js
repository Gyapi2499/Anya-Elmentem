import axios from 'axios'

export default {
  register (userRequest) {
    return axios.post('http://localhost:8080/users/users/register', {
      username: userRequest.name,
      email: userRequest.email,
      password: userRequest.password
    })
  },

  login (loginRequest) {
    const token = btoa(`${loginRequest.email}:${loginRequest.password}`)
    const Http = {
      headers: {
        Authorization: `Basic ${token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.post('https://my-app-no.herokuapp.com/users/login', {}, Http)
  },

  getUsers (usersRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${usersRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.get('http://localhost:8080/users/users', Http)
  },
  getGroup (usersRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${usersRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.get(`http://localhost:8080/users/groups/${usersRequest.id}`, Http)
  }
}
