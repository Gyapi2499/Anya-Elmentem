<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Naptáram</router-link> |
      <router-link to="/groupTodos">Csoport naptára</router-link>|
      <router-link to="/myTodos">Feladataim</router-link>|
      <router-link to="/addGroup">Csoport hozzáadása</router-link>|
      <router-link to="/modGroup">Csoport módosítása</router-link>|
      <router-link to="/newTodo">Új feladat másnak</router-link>|
      <router-link to="/newMyTodo">Új feladat nekem</router-link>|
      <router-link to="/signup">Regisztráció</router-link>|
      <router-link to="/login">Login</router-link>
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
