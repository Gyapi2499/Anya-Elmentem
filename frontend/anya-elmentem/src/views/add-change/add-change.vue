<template>
<div class="newTodo">
    <br>
            <select v-model="selectedGroup" :options='groups'>
              <option disabled value="" >Please select one</option>
              <option v-for="i in groups" v-bind:key='i.id' v-bind:value='i'  >{{i.id}}</option>
            </select >
    <br>
        <select v-model="selectedUser" :options='memberList'>
          <option disabled value="" >Please select one</option>
          <option v-for="i in memberList" v-bind:key='i.email' v-bind:value='i'  >{{i.email}}</option>
        </select >
    <br>
        <select v-model="selectedMy" :options='myTodos'>
          <option disabled value="" >Please select one</option>
          <option v-for="i in myTodos" v-bind:key='i.id' v-bind:value='i'  >{{i}}</option>
        </select >
    <br>
        <select v-model="selectedOther" :options='otherTodos'>
          <option disabled value="" >Please select one</option>
          <option v-for="i in otherTodos" v-bind:key='i.id' v-bind:value='i'  >{{i}}</option>
        </select >
    <b-button @click="submit()">Submit</b-button>
</div>
</template>
<script>
import { mapState, mapActions } from 'vuex'
export default {
  name: 'addChange',
  data () {
    return {
      selectedGroup: '',
      selectedUser: '',
      selectedMy: '',
      selectedOther: ''
    }
  },
  created () {
    this.getGroups({ token: this.token })
    this.getMine({ email: this.logUser.email, token: this.token })
  },
  computed: {
    ...mapState(['logUser', 'token']),
    ...mapState('addChange', ['groups', 'memberList', 'myTodos', 'otherTodos'])
  },
  methods: {
    ...mapActions('addChange', ['getGroups', 'getMemberList', 'getMine', 'getOthers', 'save']),
    submit () {
      this.save({ change: { user1: this.logUser, user2: this.selectedUser, todo1: this.selectedMy, todo2: this.selectedOther }, token: this.token })
    }
  },
  watch: {
    selectedGroup () {
      this.getMemberList({ id: this.selectedGroup.id, token: this.token })
    },
    selectedUser () {
      this.getOthers({ email: this.selectedUser.email, token: this.token })
    }
  }
}
</script>
<style>

</style>
