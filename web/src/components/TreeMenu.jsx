export default {
    props: {
        menuData: {type: Array, default: () => []}
    },
    render() {
        return (
            <section>
                {this.menuData.map(m => {
                    if (m.children) {
                        return (
                            <el-submenu index={m.id}>
                                <template slot="title">
                                    <icon icon={m.data.icon} style="color: white"/>
                                    <span slot="title" class="stitle">{m.label}</span>
                                </template>
                                <TreeMenu menuData={m.children}/>
                            </el-submenu>
                        )
                    } else {
                        return (
                            <el-menu-item index={m.id} onClick={() => this.$store.commit('menuChange', m)}>
                                <icon icon={m.data.icon} style="color: white"/>
                                <span slot="title" className="stitle">{m.label}</span>
                            </el-menu-item>
                        )
                    }
                })
                }
            </section>
        )
    }
}
