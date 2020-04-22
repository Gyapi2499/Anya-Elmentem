import axios from 'axios'

export default {
  register (userRequest) {
    return axios.post('http://localhost:8080/users/users/register',{
        username: userRequest.name,
        email: userRequest.email,
        password: userRequest.password
    })
  },

  login (loginRequest) {
    console.log('Ez a név:')
    console.log(loginRequest.name)
    console.log('Ez a jelszó:')
    console.log(loginRequest.password)
    const token = btoa(`${loginRequest.name}:${loginRequest.password}`)
    console.log(token)
    const Http = {
      headers: {
        Authorization: `Basic ${token}`,
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
