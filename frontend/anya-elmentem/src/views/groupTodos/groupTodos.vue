<template>
  <div class="groupTodos">
    <h2>Melyik csoportomnak feladatit szeretném látni?</h2>
    <br>
    <select v-model="selected" :options='group'>
      <option disabled value="" >Please select one</option>
      <option v-for="i in group" v-bind:key='i' v-bind:value='i'  >{{i}}</option>
    </select >
    <br>
    <a> Kinek a naptárját szeretném látni?:</a>
    <br>
    <select v-model="select"  >
      <option disabled value="">Please select one</option>
      <option v-for="cat in memberList" v-bind:key='cat.email' v-bind:value='cat'>{{cat.name}}</option>
    </select>
    <br>
    <VueCal :events = "toDos"/>
    </div>
</template>

<script>
// @ is an alias to /src
import 'vue-cal/dist/vuecal.css'
import VueCal from 'vue-cal'
import { mapState, mapActions } from 'vuex'

export default {
  name: 'groupTodos',
  components: {
    VueCal
  },
  data () {
    return {
      selected: '',
      select: '',
      events: [

      ]
    }
  },
  computed: {
    ...mapState('groupTodos', ['toDos', 'memberList', 'group']),
    ...mapState(['logUser', 'token'])
  },
  methods: {
    ...mapActions('groupTodos', ['getToDos', 'getMemberList', 'getGroup'])
  },
  mounted () {
  },
  created () {
    this.getGroup({ id: this.logUser.email, token: this.token })
    this.getMemberList({ id: this.selected, token: this.token })
    this.getToDos({ user: this.select, token: this.token })
  },
  watch: {
    selected () {
      console.log(this.selected)
      this.getMemberList({ id: this.selected, token: this.token })
    },
    select () {
      this.getToDos({ user: this.select, token: this.token })
    }
  }
}
</script>
