import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

let children = require.context('@/views/', true, /index\.vue$/, 'lazy')
    .keys()
    .map(k => {
        let trueKey = k.replace('.', '').replace('/index.vue', '')
        return {
            path: trueKey,
            component: () => import(/* webpackChunkName: "chunk-common" */`@/views${trueKey}`)
        }
    })

const routes = [
    {path: '/login', name: '登录', component: () => import('@/views/login')},
    {
        path: '/main', name: '主页面', component: () => import('@/views/main'),
        children: [
            {path: '/main/frame', name: 'frame', component: () => import('@/views/frame')},
            ...children
        ]
    },
    {path: '*', redirect: '/login'}
]

const router = new VueRouter({
    base: process.env.BASE_URL,
    routes
})

export default router
export const paths = children.map(c => c.path)
