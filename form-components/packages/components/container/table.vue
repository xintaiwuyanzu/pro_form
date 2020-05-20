<template>
    <el-table :data="tableData" style="width: 100%">
        <slot/>
    </el-table>
</template>

<script>
    import base from "../base";

    export default {
        extends: base
    }
</script>

<style scoped>

</style>