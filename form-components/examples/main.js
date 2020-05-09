import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui'
import 'element-ui/packages/theme-chalk/src/index.scss'
import form from '../packages'
import Vuex from 'vuex'

Vue.use(Vuex)
Vue.use(form)
Vue.config.productionTip = false
Vue.use(ElementUI, {size: "small"})
new Vue({
    render: h => h(App)
    , store: new Vuex.Store({})
}).$mount('#app')
