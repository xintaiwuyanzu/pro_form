<template>
    <section>
        <img src="../assets/logo.png"
             height="50px"
             style="margin-top: 10px"/>
        <section class="section" style="float: right;margin-top: 20px;">

            <el-tag effect="dark" style="margin-right: 15px;height:32px;padding: 2px;float: left;">
                {{$store.state.user.userName}}
            </el-tag>
            <el-button circle style="margin-right: 15px;height:32px;padding: 4px;float: left"
                       @click="getChange($store.state.user)">
                <icon icon="user" style="width: 20px"/>
            </el-button>
            <theme-picker @change="themeChange" style="margin-right: 10px;float: left;height:32px"></theme-picker>
            <el-button circle style="margin-right: 15px;height:32px;padding: 4px;float: left"
                       @click="logout()">
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

    export default {
        components: {changeForm, changepwdForm, ThemePicker},
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
            logout() {
                this.$store.commit('logout');
            },
            getChange(row) {
                this.$refs.changeform.editForm(row)
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
<style type="scss">
    .section {
        .el-tag {
            color: #F8F8FF;
        }
    }
</style>