import axios from 'axios'

export default {
  register (userRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${userRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.post('http://localhost:8080/users/users/register',{
        name: userRequest.name,
        email: userRequest.email,
        password: userRequest.password,
        role: userRequest.role,
        qroups: userRequest.groups
    }, Http)
  },

  login (loginRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${loginRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.post('http://localhost:8080/users/login',{}, Http)
  },

   getUsers (usersRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${usersRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.get('http://localhost:8080/users/login', Http)
  },
  getGroup (usersRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${usersRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.get('http://localhost:8080/users/groups', Http)
  }
}
