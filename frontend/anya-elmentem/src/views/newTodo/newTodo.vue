<template>
  <div class="newTodo">
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
    <form id="nekem" >
        <a>Feladat megnevezése:</a>
        <br>
        <input v-model="feladat" type= "text " name= "title" placeholder="Add todo..."><br>
        <a> Feladat kezdete:</a>
        <br>
        <datetime v-model="kezdet" type="datetime"  format="yyyy-MM-dd HH:mm:ss"></datetime>
        <br>
        <a> Feladat vége:</a>
        <datetime type="datetime" v-model="veg" format="yyyy-MM-dd HH:mm:ss"></datetime>
        <br>
        <button @click="save()"> Mentés </button>
        </form>
    <div >
      <form id="masnak"  >
        <h2>Feladat másnak:</h2>
        <a>Feladat megnevezése:</a>
        <br>
        <input type= "text "  v-model="feladat" name= "title" placeholder="Add todo..."> <br>
        <a> Feladat kezdete:</a>
        <br>
        <datetime type="datetime" v-model="kezdet" format="yyyy-MM-dd HH:mm:ss"></datetime>
        <br>
        <a> Feladat vége:</a>
        <datetime type="datetime" v-model="veg" format="yyyy-MM-dd HH:mm:ss"></datetime>
        <br>
        <a> Kit választok:</a>
        <br>
        <select v-model="select"  >
          <option disabled value="">Please select one</option>
          <option v-for="cat in memberList" v-bind:key='cat' v-bind:value='cat'>{{cat.name}}</option>
        </select>
        <br>
        <button @click="save2()"> Mentés </button>
    </form>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
export default {
  name: 'newTodo',
  data () {
    return {
      selected: '',
      select: '',
      feladat: '',
      kezdet: '',
      veg: ''
    }
  },
  methods: {
    ...mapActions('newTodo', ['getMemberList', 'addTodo', 'getGroup']),
    save () {
      this.addTodo({ userId: this.logUser.email, chores: this.feladat, fromDate: this.kezdet, toDate: this.veg, groupId: this.selected, token: this.token })
    },
    save2 () {
      this.addTodo({ userId: this.select.email, chores: this.feladat, fromDate: this.kezdet, toDate: this.veg, groupId: this.selected, token: this.token })
    }
  },
  created () {
    this.getGroup({ id: this.logUser.email, token: this.token })
    this.getMemberList({ id: this.selected, token: this.token })
  },
  computed: {
    ...mapState(['logUser', 'token']),
    ...mapState('newTodo', ['memberList', 'group'])
  },
  watch: {
    selected () {
      console.log('sisijsixjknfjhsbghbrfhvbsbdhvbjhsanvjhbfdjgvbhjdbvhwbhvbjhsdbvhbsdbvjsb')
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
h2{
  color:red;
}
h1{
  color:red;
  text-align:left;
}
a{
  color:white;
}
div{
  background-color: black;
}
</style>
