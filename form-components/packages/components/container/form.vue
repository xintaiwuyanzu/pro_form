<template>
    <el-form-item
            :label="label"
            :label-width="labelWidth"
            :rules="rules"
            :prop="name"
            style="text-align: left;">
        <slot/>
    </el-form-item>
</template>

<script>
    import base from "../base";

    export default {
        extends: base
    }
</script>
