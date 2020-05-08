<template>
    <section class="leftMenu">

        <div v-if="color=='#409EFF'" style="  background-color: #409EFF;" class="navBar" @click="toggleMenu()">
            <icon icon="menu" style="color: #d7dbf0;background: linear-gradient(180deg, #409EFF, #409EFF);"
                  :class="menuCollapse?'collapse':'nocollapse'"/>
        </div>

        <div v-else-if="color=='#303133'" style="  background-color: #303133;" class="navBar" @click="toggleMenu()">
            <icon icon="menu" style="color: #d7dbf0;background: linear-gradient(180deg, #303133, #303133);"
                  :class="menuCollapse?'collapse':'nocollapse'"/>
        </div>

        <div v-else class="navBar" style=" background-color: #008080;" @click="toggleMenu()">
            <icon icon="menu" style="color: #d7dbf0; background: linear-gradient(180deg, #008080, #008B8B);"
                  :class="menuCollapse?'collapse':'nocollapse'"/>
        </div>

        <el-menu :collapse="menuCollapse"
                 class="menu"
                 unique-opened
                 text-color="#fff"
                 active-text-color="white"
                 :background-color="backColor"
                 v-loading="menuLoading">
            <tree-menu :menu-data="menus"/>
        </el-menu>
    </section>
</template>
<script>
    import {mapState} from 'vuex'

    export default {
        computed: {
            ...mapState(['menuLoading'])
        },
        data() {
            return {
                color: "",
                backColor: '#008B8B'
            }
        },
        mounted() {
            this.$color.$on('color', (arg) => {
                this.color = arg;
                if (arg == '#409EFF') {
                    this.backColor = '#1e89db';
                } else if (arg =='#303133'){
                    this.backColor = '#303133';
                }else {
                    this.backColor = '#008B8B'
                }
            })


        }
    }
</script>
<style type="scss">
    .leftMenu {
        height: 100%;
        display: flex;
        flex-direction: column;

        .el-menu--collapse {
            .stitle {
                width: 0;
                height: 0;
                display: none;
                margin-left: 0px;
            }

            .el-submenu__icon-arrow {
                display: none;
            }
        }
    }

    .navBar {
        height: 29px;
        justify-content: center;
        display: flex;
        padding: 5px 0px;

        .collapse {
            transition: all .20s ease-out;
            transform: rotate(180deg);
        }

        .nocollapse {
            transition: all .20s ease-out;
            transform: rotate(90deg);
        }
    }

    .menu {
        overflow-x: hidden;
        overflow-y: auto;
        flex: 1;

        .el-submenu__title i {
            color: white;
        }

        .stitle {
            margin-left: 4px;
        }
    }
</style>
