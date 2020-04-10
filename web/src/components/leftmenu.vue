<template>
    <section class="leftMenu">
        <div class="navBar" @click="toggleMenu()">
            <icon icon="menu" style="color: white" :class="menuCollapse?'collapse':'nocollapse'"/>
        </div>
        <el-menu :collapse="menuCollapse"
                 class="menu"
                 unique-opened
                 text-color="#fff"
                 active-text-color="white"
                 background-color="#1e89db"
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
        }
    }
</script>
<style type="scss">
    .leftMenu {
        height: 100%;
        display: flex;
        flex-direction: column;

        .navBar {
            height: 29px;
            justify-content: center;
            display: flex;
            padding: 5px 0px;
            background-color: #1973c3;

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
            background: linear-gradient(180deg, #1e89db, #1973c3);
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
    .el-menu-item.is-active {
        background-color:#186EAF !important;
    }
</style>
