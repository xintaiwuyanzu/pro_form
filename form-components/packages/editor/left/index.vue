<template>
    <div>
        <el-tree :data="configs" @node-click="nodeClick"/>
    </div>
</template>

<script>
    import {Component, Vue} from "vue-property-decorator";
    import {configs} from '../../components/index'

    @Component
    export default class EditorLeft extends Vue {
        configs = configs

        nodeClick(data) {
            this.$emit('currentChange', data)
        }
    }
</script>
<style scoped>

</style>
