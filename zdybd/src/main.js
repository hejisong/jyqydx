import Vue from 'vue'
import apiCore from '@centit/api-core'

import iView from 'view-design'
import 'view-design/dist/styles/iview.css'

import Layout from '@centit/ui-layout'
import '@centit/ui-layout/dist/ui-layout.css'

import Form from '@centit/ui-form'
import '@centit/ui-form/dist/ui-form.css'

import Admin from '@centit/ui-admin'
import '@centit/ui-admin/dist/ui-admin.css'

import Module from '@centit/module-core'

import App from './App.vue'
import router from './router'
import store from './store'

apiCore.setBaseURL('/api')
Vue.config.productionTip = false
Vue.use(iView)
Vue.use(Layout)
Vue.use(Form)
Vue.use(Admin)
Vue.use(Module)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
