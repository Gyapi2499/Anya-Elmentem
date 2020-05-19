import axios from 'axios'

export default {
  register (userRequest) {
    return axios.post('https://my-app-no.herokuapp.com/users/users/register', {
      username: userRequest.name,
      email: userRequest.email,
      password: userRequest.password
    })
  },

  login (loginRequest) {
    const token = btoa(`${loginRequest.email}:${loginRequest.password}`)
    console.log(token)
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
    return axios.get('https://my-app-no.herokuapp.com/users/users', Http)
  },
  getGroup (usersRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${usersRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.get(`https://my-app-no.herokuapp.com/users/groups/${usersRequest.id}`, Http)
  }
}
