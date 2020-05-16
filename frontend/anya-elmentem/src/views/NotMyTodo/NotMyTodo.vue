<template>
  <div class="NotMyTodo">
    <div >
    <h2>Melyik csoportomnak szerétnék feladatot adni?</h2>
    <br>
    <select v-model="selected" :options='group'>
        <option disabled value="" >Please select one</option>
        <option v-for="i in group" v-bind:key='i' v-bind:value='i'  >{{i}}</option>
    </select >
    <br>
    <form id="masnak"  Onsubmit="return false">
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
        <button @click="save2()" type="submit"> Mentés </button>
    </form>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
export default {
  name: 'NotMyTodo',
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
    ...mapActions('NotMyTodo', ['getMemberList', 'addtoTodo', ' getAGroups']),
    save () {
      this.addtoTodo({ userId: this.select.email, chores: this.feladat, fromDate: this.kezdet, toDate: this.veg, groupId: this.selected, ready: false, token: this.token })
    }
  },
  created () {
    this.getAGroups({ id: this.logUser.email, token: this.token })
    this.getMemberList({ id: this.selected, token: this.token })
  },
  computed: {
    ...mapState(['logUser', 'token']),
    ...mapState('NotMyTodo', ['memberList', 'group'])
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

</style>
