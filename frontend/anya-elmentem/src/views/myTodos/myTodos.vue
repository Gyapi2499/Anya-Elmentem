<template>
  <div class="myTodos">
    <h2>Válasszd ki a feladatot amit elvégeztél</h2>
    <select v-model="select"  >
      <option disabled value="">Please select one</option>
      <option v-for="cat in toDos" v-bind:key='cat' v-bind:value='cat' >{{cat.title+' | '+cat.start+'-tól | '+cat.end+'-ig'}}</option>
    </select>
    <br>
    <div v-if="this.select.ready === false">
      <a>Ha elvégezted a feladatot pipáld ki: </a>
      <input type="checkbox"  @click="save()">
    </div>
    <div v-else-if="this.select.ready === true">
      Ezt a feladatot már elvégezted
    </div>
  </div>
</template>

<script>
// @ is an alias to /src

import { mapState, mapActions } from 'vuex'

export default {
  name: 'myTodos',
  data () {
    return {
      select: '',
      events: [

      ]
    }
  },
  computed: {
    ...mapState('myTodos', ['toDos']),
    ...mapState(['logUser', 'token'])
  },
  methods: {
    ...mapActions('myTodos', ['getToDos', 'readyOrNot']),
    save () {
      this.readyOrNot({ id: this.select.todoId, token: this.token })
      this.select.ready = true
    }
  },
  created () {
    this.getToDos({ user: this.logUser, token: this.token })
  },
  mounted () {
  },
  watch: {
    select () {
      console.log('sisijsixjknfjhsbghbrfhvbsbdhvbjhsanvjhbfdjgvbhjdbvhwbhvbjhsdbvhbsdbvjsb')
      console.log(this.select.ready)
    }
  }
}
</script>
