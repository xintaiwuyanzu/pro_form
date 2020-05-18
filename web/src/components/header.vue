<template>
    <section style="height: 100%;display: flex;align-content: center">
        <HeaderLogo style="margin-top: auto;margin-bottom: auto;font-size: 22px;width: 350px; " type="1" size="2" icon="margin-top:3px" />
        <div style="flex: 1"/>
        <section class="section" style="margin-top: 16px;">
            <el-button circle style="margin-right: 15px;height:32px;padding: 4px;float: left"
                       @click="$refs.changeform.showInfo()">
                <icon icon="user" style="width: 20px"/>
            </el-button>
            <theme-picker @change="themeChange" style="margin-right: 10px;float: left;height:32px"/>
            <el-button circle style="margin-right: 15px;height:32px;padding: 4px;float: left"
                       @click="$store.commit('logout')">
                <icon icon="delete" style="width: 20px"/>
            </el-button>
            <change-form ref="changeform"/>
            <changepwd-form ref="changepwdform"/>
        </section>
    </section>
</template>
<script>
    import changeForm from './changeForm'
    import changepwdForm from './changePwdForm'
    import ThemePicker from './ThemePicker'
    import HeaderLogo from './Logo'

    export default {
        components: {changeForm, changepwdForm, ThemePicker, HeaderLogo},
        data() {
            return {
                baseUrl: process.env.BASE_URL + 'imgs/',
                sysId: 'default',
                options: [],
            }
        },
        computed: {
            subSys() {
                let sys = this.options.find(d => d.id == this.sysId)
                return sys ? sys : {}
            }
        },
        methods: {
            download: url => url ? window.open(url) : '',
            loadMenu(id, remain) {
                if (id === 'user') {
                    this.$store.commit('logout')
                } else {
                    let sys = this.subSys
                    if (sys.homeAddress) {
                        window.open(sys.homeAddress)
                    } else {
                        sessionStorage.setItem('sysId', id)
                        if (!remain) {
                            this.$router.push('/main/')
                        }
                        this.$nextTick(() => {
                            this.$store.dispatch('loadMenu', {sysId: id, all: false})
                        })
                    }
                }
            },
            $init() {
                this.$http.post('/subsys/page?page=false')
                    .then(({data}) => {
                        if (data.success) {
                            this.options = data.data.sort((a, b) => a.order - b.order)
                            if (sessionStorage.getItem('sysId')) {
                                this.sysId = sessionStorage.getItem('sysId')
                            } else {
                                this.sysId = 'default'
                            }
                            this.loadMenu(this.sysId, true)
                        }
                    })
            },
            changePwds() {
                this.$refs.changepwdform.editForm()
            },
            themeChange(val) {
                this.$store.dispatch(
                    '', {
                        key: 'theme',
                        value: val
                    }),
                    this.$color.$emit('color', val);
            }
        }
    }
</script>
<style lang="scss">
    .section {
        .el-tag {
            color: #F8F8FF;
        }
    }
</style>
