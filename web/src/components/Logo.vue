<template>
    <section class="logo">
        <img src="../assets/logo.png"/>
        <span :style="textStyle">{{text}}</span>
    </section>
</template>
<script>
    export default {
        props: {
            /**
             * logo类型，总共1  2  3三种图标类型
             */
            type: {
                type: Number,
                default: 1
            },
            /**
             * 可以自定义图标
             */
            icon: {},
            /**
             * 文字名称，没有的话取html title
             */
            title: {
                type: String
            },
            /**
             * 大小，1-6  6种大小
             */
            size: {
                type: Number,
                default: 3
            },
            /**
             * 字体颜色
             */
            color: {
                type: String,
                default: 'white'
            }
        },
        computed: {
            text() {
                return this.title || document.title
            },
            textStyle() {
                return `color:${this.color};`
            }
        }
    }
</script>

<style scoped lang="scss">
    .logo {
        align-items: center;
        display: table;

        > img, > span {
            display: table-cell;
            vertical-align: middle
        }
    }

</style>
