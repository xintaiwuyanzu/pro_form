<template>
    <el-date-picker
            :type="type"
            :value="data"
            @input="v=>parent[name]=v"
            :placeholder="placeholder">
    </el-date-picker>
</template>

<script>
    import selectBase from "../selectBase";

    export default {
        extends: selectBase,
        computed: {
            /**
             * 显示类型，从schema format中提取
             * @returns {string}
             */
            type() {
                return this.schema.format == 'date' ? 'date' : 'datetime'
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
