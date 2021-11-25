<template>
  <table-index path="formDefinition" :fields="fields" :default-search-form="{versionAll:true}">
    <template slot-scope="form" slot="search">
      <el-form-item label="所有版本">
        <el-switch v-model="form.versionAll"/>
      </el-form-item>
    </template>
  </table-index>
</template>
<script>
/**
 * 表单定义
 */
export default {
  data() {
    return {
      fields: {
        formName: {label: '表单名称', search: true, required: true},
        formCode: {label: '编码', width: 150, search: true, required: true},
        formType: {
          label: '类型',
          width: 80,
          fieldType: 'select',
          url: '/formDefinition/formType',
          labelKey: 'description',
          valueKey: 'type',
          required: true
        },
        description: {label: '表单描述'},
        version: {label: '版本号', width: 80, edit: false},
        default: {
          label: '默认版本',
          width: 80,
          edit: false,
          mapper: {true: '是', false: '否'}
        }
      }
    }
  }
}
</script>