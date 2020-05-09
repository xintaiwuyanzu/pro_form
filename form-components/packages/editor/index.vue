<template>
    <div class="form-editor">
        <left class="left" @currentChange="addComponent"/>
        <middle class="middle" :data="componentsConfig"/>
        <right class="right"/>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from "vue-property-decorator";
    import Left from "./left/index.vue";
    import Right from './right/index.vue';
    import Middle from "./middle/index.vue";
    import {createField} from "../utils";
    import {store} from './store'

    @Component({
        components: {Middle, Left, Right}
    })
    export default class FormEditor extends Vue {
        /**
         *字段配置数组
         */
        componentsConfig: Array<Field> = []

        created() {
            this.$store.registerModule(this.$form.pre, store)
        }

        destroyed() {
            this.$store.unregisterModule(this.$form.pre)
        }

        /**
         * 左侧点击事件触发中间添加控件
         * @param config
         */
        addComponent(config: ComponentConfig) {
            //根据config自动生成字段基本信息
            const fieldInfo = createField(config)

            this.componentsConfig.push(fieldInfo)
            this.$store.commit('Form/current', fieldInfo)
        }
    }
</script>
<style lang="scss">
    .form-editor {
        display: flex;

        > div {
            border-radius: 8px;
            box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.1);
        }

        .left, .right {
            min-width: 200px;
        }

        .middle {
            flex: 1;
        }
    }
</style>
