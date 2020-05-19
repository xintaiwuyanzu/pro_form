<template>
    <section class="logo">
        <img v-bind:src="imgUrl" :style="imgStyle"/>
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
            icon: {
                type: String,
                default: ""
            },
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
            },
            /**
             * 字体样式
             */
            fontStyle: {
                type: String,
                default: ''
            },
            img: {}
        },
        computed: {
            imgStyle() {
                return this.img
            },
            text() {
                return this.title || document.title
            },
            textStyle() {
                return `color:${this.color};font-weight: normal;font-stretch: normal;letter-spacing: 5px;` + this.fontStyle
            },
            imgUrl() {
                if (this.icon != '') {
                    return require("../" + this.icon);
                } else {
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
                        size = 'icon1.png';
                        this.fontStyle = "\tfont-family: NotoSansHans-Regular;\n" +
                            "\tfont-size: 22px;"
                    } else if (this.size == 2) {
                        size = 'icon2.png';
                        this.fontStyle =
                            "\tfont-family: NotoSansHans-Regular;\n" +
                            "\tfont-size: 25px;"
                    } else if (this.size == 3) {
                        size = 'icon3.png';
                        this.fontStyle =
                            "\tfont-family: NotoSansHans-Regular;\n" +
                            "\tfont-size: 25px;"
                    } else if (this.size == 4) {
                        size = 'icon4.png';
                        this.fontStyle =
                            "\tfont-family: NotoSansHans-Regular;\n" +
                            "\tfont-size: 27px;"
                    } else if (this.size == 5) {
                        size = 'icon5.png';
                        this.fontStyle =
                            "\tfont-family: NotoSansHans-Regular;\n" +
                            "\tfont-size: 29px;"
                    } else {
                        size = 'icon6.png';
                        this.fontStyle =
                            "\tfont-family: NotoSansHans-Regular;\n" +
                            "\tfont-size: 31px;"
                    }
                    return require("../assets/logo/" + type + "/" + size);
                }

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
