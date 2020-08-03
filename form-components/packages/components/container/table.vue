<template>
    <el-table-column
            :label="label"
            :min-width="labelWidth">
        <template>
            <slot/>
        </template>
    </el-table-column>
</template>
<script>
    import base from "../base";

    export default {
        extends: base
    }
</script>