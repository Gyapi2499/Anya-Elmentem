<template>
  <div class="addGroup-container">
    <div class="row justify-content-center">
      <b-jumbotron class="col-lg-4 col-md-6 col-sm-10">

        <div class="col-12 title">
          <h1>Csoport módosítás</h1>
          <hr class="my-4">
        </div>

        <form class="row justify-content-center">
            <div>
              <h3>Melyik csoportodat szeretnéd módosítani?</h3>

              <multiselect
                v-model="value"
                deselect-label="Can't remove this value"
                track-by="id"
                label="id"
                placeholder="Válassz csoportot"
                :options="group"
                :searchable="false"
                :allow-empty="false">
              </multiselect>
            </div>

            <div v-if="value">
              <h3>Csoport tagok:</h3>
                <multiselect
                v-model="value.users"
                :options="users"
                :multiple="true"
                :close-on-select="false"
                :clear-on-select="false"
                :preserve-search="true"
                placeholder="Válaszd ki a csoport tagjait"
                label="name"
                track-by="name"
                :preselect-first="true">
              </multiselect>
            </div>
        </form>

      </b-jumbotron>
    </div>
  </div>
</template>

<script>
import Multiselect from 'vue-multiselect'
import { mapActions, mapState } from 'vuex'

export default {
  components: {
    Multiselect
  },
  data () {
    return {
      value: '',
      groupMembers: []
    }
  },
  created () {
    this.getAGroups({ id: this.logUser.email, token: this.token })
    this.getUsers({ token: this.token })
  },
  computed: {
    ...mapState(['logUser', 'token']),
    ...mapState('newTodo', ['group']),
    ...mapState('addGroup', ['users'])
  },
  methods: {
    ...mapActions('newTodo', ['getAGroups']),
    ...mapActions('addGroup', ['getUsers'])
  }
}
</script>

<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>

<style scoped>
.btn{
  width: 100%;
  margin-top: 15px;
}
</style>
