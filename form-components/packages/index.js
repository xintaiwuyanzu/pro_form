import FormEditor from './editor'
import FormRender from './render'

/**
 * 插件钩子，用来全局注册两个组件
 * @param vue
 * @param option
 */
export default function install(vue, option) {
    vue.component('FormEditor', FormEditor)
    vue.component('FormRender', FormRender)
    vue.prototype.$form = option
}
if (typeof window !== 'undefined' && window.Vue) {
    window.Vue.use(install)
}
