export default {
  setModGroup (state, modGroup) {
    state.modGroup = modGroup
  },
  setUsers (state, userList) {
    state.users = userList
  },
  setGroup (state, groupList) {
    state.groups = groupList
  },
  // itt most mindegyiknél member list kell, mert abból válogat, vagy a végeredményt kell nézni?
  addMembers (state, memberList) {
    state.members = memberList
  },
  delMembers (state, memberList) {
    state.members = memberList
  },
  addAdmin (state, adminList) {
    state.admins = adminList
  },
  delAdmin (state, adminList) {
    state.admins = adminList
  }
}
