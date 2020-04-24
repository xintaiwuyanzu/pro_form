<template>
    <div>
        <el-tree :data="configs" @node-click="nodeClick"/>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from "vue-property-decorator";
    import {configs} from '../../components'

    @Component
    export default class EditorLeft extends Vue {
        configs = configs

        nodeClick(data: ComponentConfig) {
            this.$emit('currentChange', data)
        }
    }
</script>
<style scoped>

</style>
