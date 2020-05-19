import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import Datetime from 'vue-datetime'
import Loading from 'vue-loading-overlay'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import 'vue-datetime/dist/vue-datetime.css'
import 'vue-loading-overlay/dist/vue-loading.css'

Vue.config.productionTip = true
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
Vue.use(Datetime)
Vue.use(Loading)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
