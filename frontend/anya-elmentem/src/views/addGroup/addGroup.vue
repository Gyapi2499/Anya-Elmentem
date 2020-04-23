<template>
    <div class="addGroup">
        <h1>Hozd létre a csoportodat!</h1>
        <br>
        <br>
        <h2>Válaszd ki a csoportod a tagjait!</h2>
            <select v-model="selected" multiple>
              <option disabled value="" >Please select one</option>
              <option v-for="i in users" v-bind:key='i' v-bind:value='i'  >{{i.email}}</option>
            </select>
            <br>
        <form @submit="submit()" onsubmit = "return false">
            <button type="submit">Csoport létrehozása</button>
        </form>
    </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
export default {
  name: 'addGroup',
  computed: {
    ...mapState(['token']),
    ...mapState('addGroup', ['users'])
  },
  methods: {
    submit () {
      this.addGroup({ token: this.token, memberList: this.selected })
    },
    ...mapActions('addGroup', ['addGroup', 'getUsers'])
  },
  data () {
    return {
      selected: []
    }
  },
  created () {
    this.getUsers({ token: this.token })
  }

}
</script>

<style scoped>
h2{
  color:red;
}
h1{
  color:red;
  text-align:left;
}

div{
  background-color: black;
}
</style>
