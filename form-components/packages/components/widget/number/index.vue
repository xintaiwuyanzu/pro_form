<template>
    <el-input-number
            :type="type"
            :value="data"
            @input="v=>parent[name]=v"
            :min="1"
            :max="10"
            label="描述文字">
    </el-input-number>

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
                return this.schema.format == 'number' ? 'number' : 'integer'
            }

        },
        mounted() {
            //默认值处理
            if (this.schema.default && !this.data) {
                this.parent[this.name] = this.schema.default
            }
        },
        methods: {}
    }
</script>
