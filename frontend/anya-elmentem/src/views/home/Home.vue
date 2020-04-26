<template>
  <div class="home">
    <!--<img alt="Vue logo" src="../assets/logo.png">-->
    <VueCal :events = "toDos"/>
  </div>
</template>

<script>
// @ is an alias to /src
import VueCal from 'vue-cal'
import 'vue-cal/dist/vuecal.css'
import { mapState, mapActions } from 'vuex'

export default {
  name: 'home',
  components: {
    VueCal
  },
  data () {
    return {
      events: [

      ]
    }
  },
  computed: {
    ...mapState('home', ['toDos']),
    ...mapState(['logUser', 'token'])
  },
  methods: {
    ...mapActions('home', ['getToDos'])
  },
  created () {
    this.getToDos({ user: this.logUser, token: this.token })
  },
  mounted () {
  }
}
</script>
