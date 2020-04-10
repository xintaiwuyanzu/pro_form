<template>
    <section>
        <nac-info>
            <el-form inline>
                <el-button type="primary" icon="el-icon-plus" v-show="menuData.length===0" @click="editMenu">添加机构
                </el-button>
                <sub-sys v-model="sysId"/>
                <el-form-item>
                    <el-input placeholder="输入关键字进行过滤" v-model="filterText" clearable>
                        <i slot="prefix" class="el-input__icon el-icon-search"/>
                    </el-input>
                </el-form-item>
            </el-form>
        </nac-info>
        <el-tree class="sysMenuTree"
                 :data="menuData"
                 :props="treeDefaultProps"
                 v-loading="treaLoading"
                 default-expand-all :filter-node-method="filterNode"
                 ref="menuTree">
            <div style="flex: 1;margin: 2px;" slot-scope="{ node, data }">
                <el-tag style="float: left;font-size: 16px" :type="data.data.status==='1'?'':'danger'">
                    {{ node.label }}
                </el-tag>
                <span class="buttons">
                  <el-button v-if="data.level===0" type="primary" size="mini" @click="editMenu">
                    添加同级机构
                  </el-button>
                  <el-button v-if="!data.data.leaf" type="primary" size="mini" @click="editMenu({parentId: data.id})">
                    添加子机构
                  </el-button>
                  <el-button type="primary" size="mini" @click="editMenu(data.data)">
                    编辑
                  </el-button>
                  <el-button :type="data.data.status==='1'?'warning':'primary'" size="mini" @click="toggle(data)">
                    {{data.data.status==='1'?'禁用':'启用'}}
                  </el-button>
                  <el-button type="danger" size="mini" v-if="node.childNodes.length===0" @click="remove(data.id)">
                    删除
                  </el-button>
                </span>
            </div>
        </el-tree>
        <organise-form ref="orgForm"/>
    </section>
</template>
<script>
  import organiseForm from './form'
  import SubSys from './subSys'

  export default {
    components: {organiseForm, SubSys},
    data () {
      return {
        sysId: '',
        filterText: '',
        treaLoading: false,
        menuData: []
      }
    },
    watch: {
      filterText (val) {
        this.$refs.menuTree.filter(val)
      },
      sysId () {
        this.loadMenus()
      }
    },
    methods: {
      filterNode (value, data) {
        if (!value) return true
        return data.label.indexOf(value) !== -1
      },
      loadMenus () {
        this.treaLoading = true
        this.$http.post('/organise/organiseTree', {all: true, sysId: this.sysId})
          .then(({data}) => {
            if (data.success) {
              this.menuData = data.data ? data.data : []
            }
            this.treaLoading = false
          })
      },
      editMenu (menu) {
        let formData = {
          sysId: this.sysId,
          parentId: this.sysId,
          status: '1',
          leaf: false,
          order: 1
        }
        if (menu) {
          formData = Object.assign(formData, menu)
        }
        this.$refs.orgForm.editForm(formData)
      },
      remove (id) {
        this.treaLoading = true
        this.$http.post('/organise/delete', {id})
          .then(({data}) => {
            if (data.success) {
              this.$message.success('删除成功！')
            }
            this.loadMenus()
          })
      },
      toggle (data) {
        this.treaLoading = true
        let status = data.data.status === '1' ? '0' : '1'
        this.$http.post('/organise/update', Object.assign({}, data.data, {status}))
          .then(({data}) => {
            if (data.success) {
              this.$message.success('操作成功！')
            }
            this.loadMenus()
          })
      }
    },
    mounted () {
      this.loadMenus()
    }
  }
</script>
<style lang="scss">
    .sysMenuTree {
        height: 100%;
        overflow: auto;
        padding: 10px;
        .el-tree-node__content {
            height: auto;
            .buttons {
                float: right;
            }
        }
    }
</style>
