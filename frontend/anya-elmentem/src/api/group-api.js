import axios from 'axios'

export default {

  newGroup (groupRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${groupRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.post('http://localhost:8080/group/newGroup', { users: groupRequest.memberList }, Http)
  },

  modifyGroup (groupRequest) {
    console.log('Itt vagyok ebben a csodában')
    console.log(groupRequest.memberList)
    console.log(groupRequest.adminList)
    const Http = {
      headers: {
        Authorization: `Basic ${groupRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.post(`http://localhost:8080/group/modifyGroup/${groupRequest.id}`, {
      users: groupRequest.memberList,
      admins: groupRequest.adminList
    }, Http)
  },

  addMember (memberRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${memberRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.post(`http://localhost:8080/group/addMember/${memberRequest.id}`, {
      name: memberRequest.name,
      email: memberRequest.email,
      role: memberRequest.role,
      qroups: memberRequest.groups
    }, Http)
  },

  shareAdmin (adminRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${adminRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.post(`http://localhost:8080/group/shareAdmin/${adminRequest.id}`, {
      user: adminRequest.users
    }, Http)
  },

  takeAdmin (adminRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${adminRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.post(`http://localhost:8080/group/takeAdmin/${adminRequest.id}`, {
      user: adminRequest.users
    }, Http)
  },

  deleteGroup (deleteRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${deleteRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.delete(`http://localhost:8080/group/deleteGroup/${deleteRequest.id}`, Http)
  },

  deleteMember (deleteRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${deleteRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.post(`http://localhost:8080/group/deleteMember/${deleteRequest.id}`, {
      name: deleteRequest.name,
      email: deleteRequest.email,
      role: deleteRequest.role,
      qroups: deleteRequest.groups
    }, Http)
  },

  getMemberList (listRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${listRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.get(`http://localhost:8080/group/getMemberList/${listRequest.id}`, Http)
  },
  getAGroups (usersRequest) {
    const Http = {
      headers: {
        Authorization: `Basic ${usersRequest.token}`,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }
    return axios.get('http://localhost:8080/group/admingroup', Http)
  }
}
