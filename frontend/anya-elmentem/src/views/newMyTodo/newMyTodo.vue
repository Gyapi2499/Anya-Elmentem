<template>
  <div class="newMyTodo">
    <div >
    <h2>Melyik csoportomnak szerétnék feladatot adni?</h2>
    <br>
            <select v-model="selected" :options='group'>
              <option disabled value="" >Please select one</option>
              <option v-for="i in group" v-bind:key='i' v-bind:value='i'  >{{i}}</option>
            </select >
            <br>
    <br>
    </div>
    <h2>Feladat nekem</h2>
    <form id="nekem" Onsubmit="return false">
        <a>Feladat megnevezése:</a>
        <br>
        <input v-model="feladatn" type= "text " name= "title" placeholder="Add todo..."><br>
        <a> Feladat kezdete:</a>
        <br>
        <datetime v-model="kezdetn" type="datetime"  format="yyyy-MM-dd HH:mm:ss"></datetime>
        <br>
        <a> Feladat vége:</a>
        <datetime type="datetime" v-model="vegn" format="yyyy-MM-dd HH:mm:ss"></datetime>
        <br>
        <button @click="save()" type="submit"> Mentés </button>
    </form>
    </div>
    </template>

<script>
import { mapActions, mapState } from 'vuex'
export default {
  name: 'newMyTodo',
  data () {
    return {
      selected: '',
      select: '',
      feladatn: '',
      kezdetn: '',
      vegn: ''
    }
  },
  methods: {
    ...mapActions('newMyTodo', ['getMemberList', 'addTodo', 'getGroup']),
    save () {
      this.addTodo({ userId: this.logUser.email, chores: this.feladatn, fromDate: this.kezdetn, toDate: this.vegn, groupId: this.selected, ready: false, token: this.token })
    }
  },
  created () {
    this.getGroup({ id: this.logUser.email, token: this.token })
    this.getMemberList({ id: this.selected, token: this.token })
  },
  computed: {
    ...mapState(['logUser', 'token']),
    ...mapState('newMyTodo', ['memberList', 'group'])
  },
  watch: {
    selected () {
      console.log(this.selected)
      this.getMemberList({ id: this.selected, token: this.token })
    }
  }
}

</script>

<style scoped>
#masnak{
    text-align: center;
    padding: 15px
}

</style>
