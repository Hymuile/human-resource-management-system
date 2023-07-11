<template>
    <div>
        <!-- 面包屑 -->
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>证书管理</el-breadcrumb-item>
            <el-breadcrumb-item>全行证书管理</el-breadcrumb-item>
        </el-breadcrumb>
        <!-- 卡片 -->
        <el-card>
            <el-row>
              <el-col :span="10">
                <div class="grid-content bg-purple-dark">
                <el-input placeholder="请输入用户名称" v-model="userName" @change="searchUserCert()">
                  <el-button slot="append" icon="el-icon-search" @click="searchUserCert()"></el-button>
                </el-input>
                </div>
              </el-col>
            </el-row>
            <el-table :data="userCertList" border style="width: 100%">
              <el-table-column
                prop="user"
                label="用户名"
                width="100">
              </el-table-column>
              <el-table-column
                prop="cert_no"
                label="证书编号"
                width="180">
              </el-table-column>
              <el-table-column
                prop="cert_name"
                width="250"
                label="证书名称">
              </el-table-column>
              <el-table-column
                prop="cert_date"
                width="180"
                label="获取日期">
              </el-table-column>
              <el-table-column
                prop=""
                label="操作">
                <template slot-scope="scope">
                  <el-button type="primary" @click="showModifyDialog(scope.row)" size="mini">修改</el-button>
                </template>
              </el-table-column>
            </el-table>
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :page-sizes="page_sizes"
              :page-size="page_size"
              layout="total, sizes, prev, pager, next"
              :total="total">
            </el-pagination>
        </el-card>
        <!-- 修改证书 -->
        <el-dialog
          title="修改证书" :visible.sync="modifyCertDialogVisable" width="35%" @close="resetModifyDialog">
          <el-form ref="modifyFormRef" :model="modifyCertForm" label-width="110px" :rules="modifyCertFormRules">
            <el-form-item label="用户名：" >
              <el-input v-model="modifyCertForm.user" disabled></el-input>
            </el-form-item>
            <el-form-item label="证书编号：" prop="cert_no">
              <el-input v-model="modifyCertForm.cert_no"></el-input>
            </el-form-item>
            <el-form-item label="证书名称：" prop="cert_name">
              <el-input v-model="modifyCertForm.cert_name"></el-input>
            </el-form-item>
            <el-form-item label="取得时间：" prop="cert_name">
              <el-date-picker value-format="yyyy-MM-dd"
                    v-model="modifyCertForm.cert_date"  type="date" placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
            <el-form-item >
              <span class="button_span">
                <el-button type="primary" @click="modifyCert()" size="small">修改</el-button>
                <el-button type="info" @click="resetModifyDialog ()" size="small">重置</el-button>
              </span>
            </el-form-item>
        </el-form>
        </el-dialog>
    </div>
</template>
<script>
export default {
  data () {
    return {
      userName: '',
      userCertList: [],
      page_sizes: [1, 2, 5, 10],
      page_size: 5,
      current_page: 1,
      total: 0,
      modifyCertDialogVisable: false,
      modifyCertForm: {
        user: '',
        cert_no: '',
        cert_name: '',
        cert_date: ''
      },
      modifyCertFormRules: {
        cert_no: [{ required: true, message: '证书编号不能为空', trigger: true }],
        cert_name: [{ required: true, message: '证书名称不能为空', trigger: true }]
      }
    }
  },
  created () {
    this.searchUserCert()
  },
  methods: {
    // 搜索所有用户证书
    searchUserCert () {
      const currentIndex = (this.current_page - 1) * this.page_size
      this.$http.get('/admin/get_all_cert?username=' + this.userName + '&cert_name=&current_index=' + currentIndex + '&page_size=' + this.page_size)
        .then(res => {
          if (res.data.state === 200) {
            this.total = res.data.total
            this.userCertList = res.data.data
          } else {
            this.$message.error('获取所有人证书失败')
          }
        })
        .catch(_error => {
          this.$message.error('获取所有人证书失败')
        })
    },
    // 修改page_size
    handleSizeChange (pageSize) {
      this.page_size = pageSize
      this.searchUserCert()
    },
    // 修改current_page
    handleCurrentChange (currentPage) {
      this.current_page = currentPage
      this.searchUserCert()
    },
    // 显示修改对话框
    showModifyDialog (row) {
      this.modifyCertDialogVisable = true
      this.modifyCertForm = row
      console.log(row)
    },
    // 重置修改对话框
    resetModifyDialog () {
      this.$refs.modifyFormRef.resetFields()
    },
    // 修改
    modifyCert () {
      this.$http.post('/admin/modify_cert', this.modifyCertForm)
        .then(res => {
          if (res.data.state === 200) {
            this.modifyCertDialogVisable = false
            this.searchUserCert()
            this.$message.success('修改证书成功')
          } else {
            this.$message.error('修改证书失败')
          }
        })
        .catch(_error => {
          this.$message.error('修改证书失败')
        })
    }
  }
}
</script>
<style scoped>
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
