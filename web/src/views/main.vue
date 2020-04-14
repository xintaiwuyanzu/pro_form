<template>
    <el-container style="height: 100%">
        <el-header v-if="color=='#409EFF'" class="headerBlue">
            <header-top></header-top>
        </el-header>
        <el-header v-else class="header">
            <header-top></header-top>
        </el-header>

        <el-container style="overflow: auto;">
            <el-aside width="auto" style="padding: 0px">
                <leftmenu></leftmenu>
            </el-aside>
            <el-main style="padding: 0px;">
                <transition name="fade-transform" mode="out-in">
                    <router-view class="main-container"></router-view>
                </transition>
            </el-main>
        </el-container>
    </el-container>
</template>
<script>
    import headerTop from '../components/header'
    import Leftmenu from '../components/leftmenu'

    export default {
        components: {Leftmenu, headerTop},
        data() {
            return {
                color: "",
            }
        },
        mounted() {
            this.$color.$on('color', (arg) => {
                this.color = arg;
            })


        }
    }
</script>

<style type="scss">
    .header {
        background: linear-gradient(180deg, #008080, #008B8B, #20B2AA);
        color: #000000;
        vertical-align: middle;
        border-bottom: 1px solid #008080;
    }

    .headerBlue {
        background: linear-gradient(180deg, #013674, #074488, #2377cd);
        color: #fff;
        vertical-align: middle;
        border-bottom: 1px solid #00397f;
    }

    .breadcrumb-container {
        display: inline-block;
        background: #eef1f7;
        border-top-right-radius: 4px;
        border-top-left-radius: 4px;
        border-bottom: 1px solid #dadfea;
        width: 100%;
        position: fixed;
        z-index: 50;

        strong {
            z-index: 51;
        }

        section {
            z-index: 99;

            button {
                z-index: 999;
            }

        }
    }


    .slot {
        float: left;
        padding: 4px 6px;
    }

    .el-button {
        padding: 8px 15px;
    }

    .el-form-item {
        margin-bottom: 0px;
        margin-right: 0px;
    }


    .title {
        padding: 6px;
        color: #475669;
        float: left;
        font-family: 宋体;
        font-size: 23px;
    }

    .main-container {
        width: 100%;
        height: 100%;
        overflow: auto;
        display: flex;
        flex-direction: column;
    }


    .searchForm {
        padding: 5px;

        .el-form-item {
            margin-bottom: 5px;
        }
    }

    .index_main {
        display: flex;
        flex-direction: column;
        flex: 1;
        padding: 40px 5px 5px 5px;
    }

    .table-container {
        flex: 1;
        overflow: auto;
    }

    .el-pagination {
        text-align: right;
    }


    .el-form-item {
        margin-bottom: 4px;
    }


    .el-card {
        margin: 5px 2px;
    }

    .el-form {
        .el-form-item {
            width: auto;
            margin-bottom: 10px;
        }
    }

    .el-card__header, .el-card__body {
        padding: 10px;
        clear: both;
    }


</style>