<template>
    <div class="addGroup">
        <h1>Hozd létre a csoportodat!</h1>
        <br>
        <form @submit="submit()" onsubmit = "return false">
          <h2>Válaszd ki a csoportod a tagjait!</h2>
        <br>
            <select v-model="selected" :options='users' @click="toggle" multiple>
              <option disabled value="" >Please select one</option>
              <option v-for="i in users" v-bind:key='i' v-bind:value='i'  >{{i.email}}</option>
            </select>
        <br>
            <button type="submit">Csoport létrehozása</button>
        </form>
    </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
export default {
  name: 'addGroup',
  computed: { ...mapState(['token']) },
  methods: {
    submit () {
      this.addGroup({ token: this.token })
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
