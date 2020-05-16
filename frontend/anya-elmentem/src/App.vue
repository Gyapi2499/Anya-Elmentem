<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>|
      <router-link to="/addGroup">Add Group</router-link>|
      <router-link to="/modGroup">Modify a Group</router-link>|
      <router-link to="/newTodo">NewTodo</router-link>|
      <router-link to="/signup">Registration</router-link>|
      <router-link to="/login">Login</router-link>|
      <router-link to="/ujCsoport">ujCsoport</router-link>
      <b-button variant="secondary" @click="doLogout()"><b-icon-box-arrow-in-right></b-icon-box-arrow-in-right></b-button>
    </div>
    <router-view/>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import router from './router/index.js'

export default {
  methods: {
    ...mapActions(['logout']),
    doLogout () {
      this.logout()
      router.push({ path: '/' })
    }
  }
}
</script>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
