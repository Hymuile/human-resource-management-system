<template>
    <div>
        <!-- 面包屑 -->
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>证书管理</el-breadcrumb-item>
            <el-breadcrumb-item>证书待审批流程</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card>
            <el-row>
              <el-col :span="10">
                <div class="grid-content bg-purple-dark">
                <el-col :span="5" class="addBtn">
                    <el-button type="info" @click="searchCertFlow()">刷新</el-button>
                </el-col>
                </div>
              </el-col>
            </el-row>
            <el-table :data="certFlowList" border style="width: 100%">
              <el-table-column
                prop="user_name"
                label="申请人"
                width="100">
              </el-table-column>
              <el-table-column
                prop="apply_date"
                label="申请时间"
                width="100">
              </el-table-column>
              <el-table-column
                prop="type"
                label="申请类型"
                width="100">
                <template slot-scope="scope">
                    <el-select v-model="scope.row.type" placeholder="请选择" disabled>
                        <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                        </el-option>
                    </el-select>
                </template>
              </el-table-column>
              <el-table-column
                prop="cert_no"
                label="证书编号">
              </el-table-column>
              <el-table-column
                prop="cert_name"
                label="证书名称">
              </el-table-column>
              <el-table-column
                prop="cert_date"
                label="取得日期">
              </el-table-column>
              <el-table-column
                prop=""
                label="操作" width="180">
                <template slot-scope="scope">
                  <el-button type="success" @click="agree(scope.row)" size="mini">同意</el-button>
                  <el-button type="info" @click="disagree(scope.row)" size="mini">不同意</el-button>
                </template>
              </el-table-column>
            </el-table>
        </el-card>
    </div>
</template>
<script>
export default {
  data () {
    return {
      certFlowList: [],
      value: 0,
      options: [
        {
          value: 0,
          label: '新增'
        },
        {
          value: 1,
          label: '修改'
        }
      ]
    }
  },
  created () {
    this.searchCertFlow()
  },
  methods: {
    // 查询待审批的流程
    searchCertFlow () {
      this.$http.get('/admin/get_proving_certflow?usernaem=&cert_name=&current_index=0&page_size=100')
        .then(res => {
          if (res.data.state === 200) {
            console.log(res.data.data)
            this.certFlowList = res.data.data
          } else {
            this.$message.error('获取待审批的流程失败')
          }
        })
        .catch(_error => {
          this.$message.error('获取等审批的流程失败')
        })
    },
    // 同意
    agree (row) {
      this.$http.put('/admin/deal_cert_flow?id=' + row.id + '&type=0')
        .then(res => {
          if (res.data.state === 200) {
            this.searchCertFlow()
            this.$message.success('处理证书流程成功')
          } else {
            this.$message.error('处理证书流程失败')
          }
        })
        .catch(_error => {
          this.$message.error('处理证书流程失败' + _error)
        })
    },
    // 不同意
    disagree (row) {
      this.$http.put('/admin/deal_cert_flow?id=' + row.id + '&type=1')
        .then(res => {
          if (res.data.state === 200) {
            this.searchCertFlow()
            this.$message.success('处理证书流程成功')
          } else {
            this.$message.error('处理证书流程失败')
          }
        })
        .catch(_error => {
          this.$message.error('处理证书流程失败' + _error)
        })
    }
  }
}
</script>
<style lang="less" scoped>
.el-table{
  margin-top: 10px;
}
.el-pagination{
  margin-top: 10px;
}
.addBtn{
  margin-left: 10px;
}
</style>
