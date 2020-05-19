<template>
<div>
  <changeHandler v-for="change in changes" :change ="change" :key="change.id" @delete="deleteThis(change)" @accept="acceptThis(change)"/>
</div>
</template>
<script>
import changeHandler from '../../components/change-handler/change-handler'
import { mapActions, mapState } from 'vuex'
export default {
  name: 'handleChange',
  computed: {
    ...mapState(['logUser', 'token']),
    ...mapState('handleChange', ['changes'])
  },
  methods: {
    ...mapActions('handleChange', ['getAll', 'decline', 'accept']),
    deleteThis (change) {
      this.decline({ id: change.id, token: this.token })
    },
    acceptThis (change) {
      this.accept({ id: change.id, token: this.token })
    }
  },
  created () {
    this.getAll({ token: this.token })
  },
  components: {
    changeHandler
  }
}
</script>
<style>

</style>
