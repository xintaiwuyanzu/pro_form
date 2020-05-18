<template>
    <section class="logo">
        <img v-bind:src="imgUrl" :style="iconStyle"/>
        <span v-if="type!=1" style="width: 1px;height: 60px;background-color: #dcdcdc;"/>
        <span v-if="type!=1" style="padding-left: 20px">{{text}}</span>
        <span v-else :style="textStyle">{{text}}</span>
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
                return `color:${this.color};` + 'font-weight: normal;' + 'font-stretch: normal;' + 'letter-spacing: 2px;'
            },
            iconStyle() {
                return this.icon
            },
            imgUrl() {
                var type = "";
                var size = "";
                if (this.type == 1) {
                    type = "icon1";
                } else if (this.type == 2) {
                    type = "icon2";
                } else {
                    type = "icon3";
                }
                if (this.size == 1) {
                    size = 'icon1.png'
                } else if (this.size == 2) {
                    size = 'icon2.png'
                } else if (this.size == 3) {
                    size = 'icon3.png'
                } else if (this.size == 4) {
                    size = 'icon4.png'
                } else if (this.size == 5) {
                    size = 'icon5.png'
                } else {
                    size = 'icon6.png'
                }
                return require("../assets/logo/" + type + "/" + size);
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
