import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import myPlguin from './util/myPlguin'


Vue.config.productionTip = false
Vue.use(myPlguin, {router, store})
router.beforeEach((to, from, next) => {
    if (to.path != '/login') {
        if (sessionStorage.getItem('uv')) {
            if (!store.state.user.id) {
                store.commit('login', JSON.parse(sessionStorage.getItem('uv')))
            }
            next()
        } else {
            next({path: "/login", query: {p: to.fullPath}})
        }
    } else {
        next()
    }
})
new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
Vue.prototype.$color = new Vue();
