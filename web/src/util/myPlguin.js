import axios from 'axios'
import {mapActions, mapGetters, mapMutations, mapState} from 'vuex'
import qs from 'qs'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import AsyncValidator from 'async-validator'
import navInfo from '../components/navInfo'
import icon from '../components/icon'
import iconSelect from '../components/iconSelect'
import TreeMenu from '../components/TreeMenu'
import selectDict from '../components/selectDict'
import selectPerson from '../components/selectPerson'
import selectConfig from '../components/selectConfig'
import {Message} from 'element-ui'
import moment from 'moment'
import '../styles/normal.css'

// 重定向login页面
function routeLogin(options) {
    if (options.router) {
        options.router.replace({path: 'login', query: {redirect: options.router.currentRoute.fullPath}})
    }
}

function varType(n) {
    var typeStr = Object.prototype.toString.call(n)
    //var typeOfName = (typeof n);
    var typeName = ''
    switch (typeStr) {
        case '[object String]':
            typeName = 'string'
            break
        case '[object Number]':
            typeName = 'number'
            break
        case '[object Boolean]':
            typeName = 'boolean'
            break
        case '[object Undefined]':
            typeName = 'undefined'
            break
        case '[object Object]':
            typeName = 'object'
            break
        case '[object Array]':
            typeName = 'array'
            break
        case '[object Null]':
            typeName = 'null'
            break
        case '[object RegExp]':
            typeName = 'RegExp'
            break
        case '[object Symbol]':
            typeName = 'symbol'
            break
        case '[object JSON]':
            typeName = 'json'
            break
        case '[object Math]':
            typeName = 'math'
            break
        default:
            typeName = 'object'
    }
    return typeName
}

function httpInstance(options) {
    let httpInstance = axios.create({
        baseURL: options.api,
        timeout: 20000,
        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        transformRequest(data) {
            NProgress.start()
            return qs.stringify(data)
        },
        transformResponse(data) {
            NProgress.done()
            try {
                data = JSON.parse(data)
            } catch (e) {
                data = {success: false, message: '数据格式不正确'}
            }
            return data
        }
    })

    httpInstance.interceptors.request.use(config => {
        let header = config.headers
        header['$token'] = sessionStorage.getItem('$token')
        return config;
    }, err => {
        return Promise.reject(err);
    })

    httpInstance.interceptors.response.use(
        response => {
            if (response.data.message === 'needLogin') {
                routeLogin(options)
            } else {
                return response
            }
        },
        err => {
            //routeLogin(options)
            let message = `服务器错误【${err}】`
            if (err && err.message.startsWith('timeout')) {
                message = `网络请求超时，请稍候重试！`
            }
            Message.error(message)
            return Promise.resolve({data: {success: false, message}})
        })
    return httpInstance
}

export default {
    /**
     *
     * @param Vue vue全局对象
     * @param options 参数对象
     * @returns {boolean}
     */
    install(Vue, options = {api: '/api/', router: null}) {
        //  处理默认值
        if (!options.api) {
            options.api = '/api/'
        }
        if (options.router) {
            options.router.beforeEach((route, redirect, next) => {
                NProgress.start()
                next()
            })
            options.router.afterEach(() => {
                NProgress.done()
            })
        }
        Vue.prototype.$http = httpInstance(options)

        //添加登录相关的方法
        if (options.store) {
            options.store.$router = options.router
            options.store.$http = Vue.prototype.$http
        }
        // 全局对象注入默认方法等信息
        Vue.mixin({
            computed: {
                ...mapState(['loginState', 'menuCollapse', 'treeDefaultProps', 'trueFalseOptions', 'statusOptions', 'menus']),
                ...mapGetters(['loginLoading']),
                menu() {
                    let menu = this.$store.state.menuMap.get(this.$route.path)
                    if (!menu && this.$route.params.$url) {
                        menu = this.$store.state.menuMap.get(this.$route.params.$url)
                    }
                    if (menu) {
                        sessionStorage.setItem('menu', menu.label)
                    }
                    if (!menu) {
                        menu = {}
                        menu.label = sessionStorage.getItem('menu')
                    }
                    return menu ? menu : {}
                }
            },
            methods: {
                ...mapMutations(['toggleMenu']),
                ...mapActions(['doLogin']),
                apiPath() {
                    let path = this.path
                    if (!path) {
                        let routePath = this.$route.path.split('/')
                        path = routePath[routePath.length - 1]
                    }
                    return path
                },
                $loadDict(type) {
                    return new Promise((resolve, reject) => {
                        if (options.store.state.dicts[type]) {
                            resolve(options.store.state.dicts[type])
                        } else {
                            resolve(this.$http.post('/sysDict/dict', {type}).then(({data}) => {
                                    if (data.success) {
                                        let dictObj = {}
                                        dictObj[type] = data.data
                                        if (type === 'organise') {
                                            dictObj[type] = data.data.filter(d => d.id !== '1')
                                        }
                                        options.store.commit('dictLoaded', dictObj)
                                        return dictObj[type]
                                    } else {
                                        return []
                                    }
                                }).catch(e => reject(e))
                            )
                        }
                    })
                }
            },
            mounted() {
                if (this.dict) {
                    let promises = this.dict.map(d => this.$loadDict(d))
                    Promise.all(promises).then(() => {
                        if (this.$init) {
                            this.$init()
                        }
                    })
                } else {
                    if (this.$init) {
                        this.$init()
                    }
                }
            }
        })
        // 注册自定义组件
        Vue.component('icon', icon)
        Vue.component('iconSelect', iconSelect)
        Vue.component('TreeMenu', TreeMenu)
        Vue.component('nacInfo', navInfo)
        Vue.component('selectDict', selectDict)
        Vue.component('selectPerson', selectPerson)
        Vue.component('selectConfig', selectConfig)
        // 处理validate的中文显示
        let oldMessage = AsyncValidator.prototype.messages
        AsyncValidator.prototype.messages = function (message) {
            let returnMessage = oldMessage.apply(this, message)
            if (returnMessage.required) {
                returnMessage.required = '%s 不能为空！'
            }
            return returnMessage
        }
        Vue.filter('dict', (v, dict) => {
            if (varType(dict) !== 'object') {
                dict = options.store.state.dicts[dict]
            }
            if (dict) {
                if (Array.isArray(dict)) {
                    let obj = dict.find(d => d.id == v)
                    if (obj) {
                        return obj.label
                    }
                } else {
                    if (dict[v]) {
                        return dict[v]
                    }
                }
            }
            return v
        })

        function fmtDate(v, fmt) {
            if (!v) {
                return v
            }
            try {
                return moment(v).format(fmt)
            } catch (e) {
                return v
            }
        }

        Vue.filter('null', (v) => (v && v !== 'null') ? v : '')
        Vue.filter('date', (v, fmt) => fmtDate(v, fmt ? fmt : 'YYYY-MM-DD'))
        Vue.filter('datetime', (v, fmt) => fmtDate(v, fmt ? fmt : 'YYYY-M-D H:m:s'))
        Vue.directive('focus', {
            inserted: function (el) {
                el.childNodes.forEach(e => {
                    if (e.tagName === 'INPUT' || e.tagName === 'input') {
                        e.focus()
                    }
                })
            }
        })
    }
}
