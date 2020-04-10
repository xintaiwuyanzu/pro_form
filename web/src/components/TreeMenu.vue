<!--<template>
    <section>
        <template v-for="menu in menuData">
            <el-submenu :index="menu.id" v-if="menu.children">
                <template slot="title">
                    <icon :icon="menu.data.icon" style="color: white"/>
                    <span slot="title" class="stitle">{{menu.label}}</span>
                </template>
                <TreeMenu :menuData="menu.children"></TreeMenu>
            </el-submenu>
            <el-menu-item :index="menu.id" v-else @click="$store.commit('menuChange', menu)">
                <icon :icon="menu.data.icon" style="color: white"/>
                <span slot="title" class="stitle">{{menu.label}}</span>
            </el-menu-item>
        </template>
    </section>
</template>-->
<script>
    export default {
        name: 'TreeMenu',
        props: {
            menuData: Array
        }, render(h) {
            const label = (m) => {
                return [
                    h('icon', {
                        props: {
                            icon: m.data.icon
                        },
                        style: {
                            color: 'white'
                        }
                    }),
                    h('span', {
                        props: {
                            icon: m.data.icon
                        },
                        style: {
                            color: 'white'
                        },
                        slot: 'title',
                        'class': 'stitle',
                        domProps: {
                            innerHTML: m.label
                        }
                    })
                ]
            }

            return h('section', this.menuData
                .map(m => {
                    if (m.children) {
                        return h('el-submenu', {
                            props: {
                                index: m.id
                            }
                        }, [
                            h('template', {
                                slot: 'title'
                            }, label(m)),
                            h('TreeMenu', {props: {menuData: m.children}})
                        ])
                    } else {
                        return h('el-menu-item', {
                            props: {
                                index: m.id
                            },
                            on: {
                                click() {
                                    this.$store.commit('menuChange', m)
                                }
                            }
                        }, label(m))
                    }
                })
            )
        }
    }
</script>
