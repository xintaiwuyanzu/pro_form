<template>
    <el-select
            :value="data"
            @input="v=>parent[name]=v"
            :placeholder="placeholder"
            clearable>
        <el-option v-for="e in enums" :key="e.index"
                   :label="e.label"
                   :value="e.value"/>
    </el-select>
</template>

<script>
    import selectBase from "../selectBase";

    export default {
        extends: selectBase,
        computed: {
            /**
             * 动态计算出来选项
             * @returns {*}
             */
            enums() {
                const enums = this.schema.enum
                const enumNames = this.schema.enumNames
                return enums.map((e, i) => {
                    return {
                        index: i,
                        label: enumNames[i] || '',
                        value: enums[i]
                    }
                })
            }
        }
    }
</script>
