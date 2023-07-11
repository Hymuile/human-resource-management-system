import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './assets/css/global.css'
import Axios from 'axios'

Vue.config.productionTip = false
Vue.prototype.$http = Axios

Axios.defaults.baseURL = 'http://localhost:8081/'
Axios.defaults.withCredentials = true

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
