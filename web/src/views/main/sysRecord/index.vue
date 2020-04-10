<template>
    <section v-loading="loading">
        <nac-info >
        </nac-info>
        <div class="index_main" >
            <el-form :model="searchForm" ref="searchForm" inline class="searchForm">
                <el-form-item label="操作人：">
                    <el-input v-model="searchForm.userId"  clearable placeholder="请输入操作人"/>
                </el-form-item>
                <el-form-item label="操作时间" prop="timevalue">
                    <el-date-picker v-model="timevalue"
                                    type="daterange"
                                    align="right"
                                    clearable
                                    style="max-width: 240px"
                                    value-format="yyyyMMdd"
                                    range-separator="至"
                                    start-placeholder="开始日期"
                                    end-placeholder="结束日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="loadData(1)" size="mini">搜 索</el-button>
                    <el-button @click="$refs.searchForm.resetFields()" size="mini">重 置</el-button>
                </el-form-item>
            </el-form>
            <div class="table-container">
                <el-table :data="data" border height="100%" >
                    <el-table-column label="排序" type="index" align="center">
                        <template slot-scope="scope">
                            {{(page.index-1)*page.size+scope.$index+1}}
                        </template>
                    </el-table-column>
                    <el-table-column prop="log0" label="日志编号" align="center" width="100" >
                    </el-table-column>
                    <el-table-column prop="loga1" label="日期" width="100" align="center" header-align="center"
                                     show-overflow-tooltip/>
                    <el-table-column prop="loga2" label="时间" width="90" align="center" header-align="center"
                                     show-overflow-tooltip/>
                    <el-table-column prop="logb" label="datno" width="130" align="center" header-align="center"
                                     show-overflow-tooltip/>
                    <el-table-column label="操作类型" width="100" align="center" header-align="center"
                                     show-overflow-tooltip>
                        <template slot-scope="scope" >
                            {{scope.row.logc|dict('recode.type')}}
                        </template>
                    </el-table-column>
                    <el-table-column prop="logd" label="man" width="130" align="center" header-align="center"
                                     show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column prop="loge" label="操作人" width="130" align="center" header-align="center"
                                     show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column prop="logf" label="dbno" width="90" align="center" header-align="center"
                                     show-overflow-tooltip/>
                    <el-table-column prop="logg" label="data1" width="120" align="center" header-align="center" show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column prop="logh" label="data2" width="120" align="center" header-align="center" show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column prop="logi" label="data3" width="160" align="center" header-align="center" show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column label="藏书馆"  align="center" header-align="center" show-overflow-tooltip>
                        <template slot-scope="scope" >
                            {{scope.row.logj|dict('organise')}}
                        </template>
                    </el-table-column>
                </el-table>
            </div>
            <el-pagination
                    @current-change="index=>loadData(index)"
                    :current-page.sync="page.index"
                    :page-size="page.size"
                    layout="total, prev, pager, next"
                    :total="page.total">
            </el-pagination>
        </div>
    </section>
</template>
<script>
    export default {
        data() {
            return {
                data: [],
                page: {
                    size: 15,
                    index: 0,
                    total: 0
                },
                loading:false,
                searchForm:{ },
                timevalue : [],
                dict: ['organise','recode.type']
            }
        },
        methods: {
            $init(){
                this.loadData(1)
            },
            loadData(pageIndex){
                this.loading=true
                let userid = this.searchForm.userId

                let startTime = ''
                let endTime = ''
                if(this.timevalue!=null){
                    if(this.timevalue.length>0){
                        startTime = this.timevalue[0]
                        endTime = this.timevalue[1]
                    }
                }
                this.$http.post('ilas/operationLog',{pageIndex:pageIndex,pageSize:this.page.size,userid:userid,startTime:startTime,endTime:endTime}).then(({data}) => {
                    if (data && data.success) {
                        this.data = data.data.data
                        this.page.index = data.data.start / data.data.size + 1
                        this.page.size = data.data.size
                        this.page.total = data.data.total
                    }
                    this.loading=false
                })
            }
        }
    }
</script>
