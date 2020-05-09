<template>
    <el-input
            :type="type"
            :value="data"
            @input="v=>parent[name]=v"
            :placeholder="placeholder"
            clearable
    />
</template>

<script>
    import base from "../../base";

    export default {
        extends: base,
        computed: {
            /**
             * 显示类型，从schema format中提取
             * @returns {string}
             */
            type() {
                return this.schema.format == 'textarea' ? 'textarea' : 'text'
            }
        },
        mounted() {
            //默认值处理
            if (this.schema.default && !this.data) {
                this.parent[this.name] = this.schema.default
            }
        }
    }
</script>
