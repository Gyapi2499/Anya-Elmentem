<template>
  <div class="login-container">
    <div class="row justify-content-center">
      <b-jumbotron class="col-lg-4 col-md-6 col-sm-10">

        <div class="row justify-content-between">
          <router-link to="/"><b-button class="col back-button" variant="secondary">Vissza</b-button></router-link>
        </div>

        <div class="col-12 title">
          <h1>Regisztráció</h1>
          <hr class="my-4">
        </div>

        <div v-if="wasError" class="col-12 title">
          <ErrorMessage :message='errMessage' />
        </div>

        <div v-if="!wasError && tried" class="col-12">
          <h1>Sikeres regisztráció</h1>
          <router-link to="/login"><b-button variant="success">Bejelentkezés</b-button></router-link>
        </div>

        <div class="row justify-content-center">
          <b-form-group id="input-group-2" label-for="input-3" class="col-10">
            <b-form-input
              v-model="signupRequest.name"
              required
              placeholder="Felhasználónév"
              autofocus
            ></b-form-input>

            <b-form-input
              v-model="signupRequest.email"
              type="email"
              required
              placeholder="E-mail cím"
            ></b-form-input>

            <b-form-input
              v-model="signupRequest.password"
              type="password"
              required
              placeholder="Jelszó"
              @keyup.enter="logup()"
            ></b-form-input>

          </b-form-group>
          <b-button @click="logup()" variant="success">Regisztrálok</b-button>
        </div>

      </b-jumbotron>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
import router from '../../router/index.js'
import ErrorMessage from '../../components/error-message/error-message.vue'

export default {
  name: 'Signup',
  components: {
    ErrorMessage
  },
  data () {
    return {
      signupRequest: {
        name: '',
        email: '',
        password: ''
      },
      errMessage: 'Sikertelen Regisztráció',
      tried: false
    }
  },
  methods: {
    ...mapActions('Registration', ['signup']),
    async logup () {
      await this.signup(this.signupRequest)
      this.tried = true
    }
  },
  watch: {
    wasError () {
      if (!this.wasError) {
        router.push({ name: 'login' })
      }
    }
  },
  computed: {
    ...mapState('Registration', ['wasError'])
  }
}
</script>

<style scoped>
.form-control{
    margin-top: 15px;
}

.jumbotron{
  margin-top: 40px;
  padding-top: 10px;
}

.back-button{
  margin-bottom: 20px;
}
</style>
