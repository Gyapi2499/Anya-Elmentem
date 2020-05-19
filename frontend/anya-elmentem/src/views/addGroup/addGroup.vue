<template>
  <div class="addGroup-container">
    <div class="row justify-content-center">
      <b-jumbotron class="col-lg-4 col-md-6 col-sm-10">

        <div class="col-12 title">
          <h1>Új csoport</h1>
          <hr class="my-4">
        </div>

        <div v-if="successfulNewGroup && !tried" class="col-12">
          <h1>Csoport létrehozás sikeres</h1>
        </div>

        <form v-if ="!successfulNewGroup || tried" class="row justify-content-center">
          <multiselect
          v-model="value"
          :options="options"
          :multiple="true"
          :close-on-select="false"
          :clear-on-select="false"
          :preserve-search="true"
          placeholder="Válaszd ki a csoport tagjait"
          label="name"
          track-by="name"
          :preselect-first="true">
          </multiselect>

          <div class="col-md-5 col-sm-8 ">
            <b-button variant="success" @click="submitGroup()">Létrehozás</b-button>
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
      options: [],
      value: [],
      tried: true
    }
  },
  created () {
    this.getUsers({ token: this.token })
  },
  watch: {
    users () {
      this.options = this.users
    }
  },
  computed: {
    ...mapState(['token']),
    ...mapState('addGroup', ['users', 'successfulNewGroup'])
  },
  methods: {
    ...mapActions('addGroup', ['addGroupm', 'addGroup', 'getUsers']),
    submitGroup () {
      var emails = []
      var i
      for (i = 0; i < this.value.length; i++) {
        emails.push(this.value[i].email)
      }
      this.addGroupm({ token: this.token, memberList: emails })
      this.tried = false
    }
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
