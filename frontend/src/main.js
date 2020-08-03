import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import VueCookies from 'vue-cookies'
import vuetify from './plugins/vuetify'
import Vuex from 'vuex'
import InfiniteLoading from 'vue-infinite-loading'

Vue.use(vuetify)
Vue.use(VueCookies)
Vue.use(Vuex)
Vue.use(InfiniteLoading)

Vue.config.productionTip = false

new Vue({
  vuetify,
  router,
  store,
  render: h => h(App)
}).$mount('#app')
