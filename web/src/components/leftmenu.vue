<template>
    <section class="leftMenu">
        <div class="navBar" @click="toggleMenu()">
            <icon icon="menu" style="color: #d7dbf0" :class="menuCollapse?'collapse':'nocollapse'"/>
        </div>
        <el-menu :collapse="menuCollapse"
                 class="menu"
                 unique-opened
                 text-color="#fff"
                 active-text-color="white"
                 background-color="#008B8B"
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
        background-color: #008080;

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
        background: linear-gradient(180deg, #008080, #008B8B);
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

    .el-menu-item.is-active {
        background-color: teal !important;
    }
</style>
