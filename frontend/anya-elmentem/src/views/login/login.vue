<template>
  <div class="home-container">
    <div class="row justify-content-center">
      <b-jumbotron class="col-lg-4 col-md-6 col-sm-10">

        <div class="col-12 title">
          <h1>Bejelentkezés</h1>
          <hr class="my-4">
        </div>

        <div v-if="showError" class="col-12 title">
          <ErrorMessage :message='errMessage' />
        </div>

        <div v-if="successfulLogin" class="col-12">
          <h1>Sikeres bejelentkezés</h1>
        </div>

        <b-form v-if="!successfulLogin" class="row justify-content-center vld-parent" ref="formContainer">
          <b-form-group id="input-group-1" label-for="input-1" class="col-10">

            <b-form-input
              id="input-1"
              v-model="loginRequest.email"
              required
              placeholder="Adja meg az e-mail címét"
              autofocus
            ></b-form-input>

            <b-form-input
              id="input-2"
              v-model="loginRequest.password"
              type="password"
              required
              placeholder="Adja meg a jelszavát"
              @keyup.enter="signin()"
            ></b-form-input>

          </b-form-group>
          <div class="col-md-5 col-sm-8">
            <b-button @click="signin()" variant="success">Bejelentkezés</b-button>
          </div>

          <div class="col-md-5 col-sm-8">
            <router-link to="/signup"><b-button variant="secondary">Regisztráció</b-button></router-link>
          </div>

        </b-form>
      </b-jumbotron>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
import ErrorMessage from '../../components/error-message/error-message.vue'

export default {
  name: 'Login',
  components: {
    ErrorMessage
  },
  data () {
    return {
      loginRequest: {
        email: '',
        password: ''
      },
      showError: false,
      errMessage: 'Sikertelen bejelentkezés',
      fullPage: false
    }
  },
  methods: {
    ...mapActions(['login']),
    signin () {
      const loader = this.$loading.show({
        container: this.fullPage ? null : this.$refs.formContainer,
        canCancel: true,
        onCancel: this.onCancel
      })
      this.login(this.loginRequest)
      this.showError = false
      setTimeout(() => {
        loader.hide()
        if (!this.successfulLogin) {
          this.showError = true
        }
      }, 5000)
    },
    onCancel () {
      console.log('User cancelled the loader.')
      this.showError = true
    }
  },
  computed: {
    ...mapState(['logUser', 'successfulLogin'])
  }
}
</script>

<style scoped>
.btn{
  width: 100%;
  margin-top: 15px;
}

.form-control{
  margin-top: 15px;
}

.jumbotron{
  margin-top: 40px;
}
</style>
