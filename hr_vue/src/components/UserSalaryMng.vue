<template>
    <div>
         <!-- 面包屑 -->
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>薪酬管理</el-breadcrumb-item>
            <el-breadcrumb-item>员工薪酬管理</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card>
            <el-row>
              <el-col :span="10">
                <div class="grid-content bg-purple-dark">
                <el-input placeholder="请输入用户名称" v-model="userName" @change="searchUserSalary()">
                  <el-button slot="append" icon="el-icon-search" @click="searchUserSalary()"></el-button>
                </el-input>
                </div>
              </el-col>
            </el-row>
            <el-table :data="userSalaryList" border style="width: 100%">
              <el-table-column
                prop="username"
                label="用户名"
                width="200">
              </el-table-column>
              <el-table-column
                prop="notes"
                label="薪酬名称"
                width="280">
              </el-table-column>
              <el-table-column
                prop="salary"
                label="薪酬">
              </el-table-column>
              <el-table-column
                prop=""
                label="操作" width="180">
                <template slot-scope="scope">
                  <el-button type="success" @click="showModifyDialog(scope.row)" size="mini">修改</el-button>
                </template>
              </el-table-column>
            </el-table>
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :page-sizes="page_sizes"
              :page-size="page_size"
              layout="total, sizes, prev, pager, next"
              :total="userSalaryListNum">
            </el-pagination>
        </el-card>
         <!-- 修改用户薪酬 -->
        <el-dialog
          title="修改用户薪酬" :visible.sync="modifySalaryDialogVisable" width="35%">
          <el-form ref="modifyFormRef" :model="modifySalaryForm" label-width="110px">
            <el-form-item label="用户名：" prop="username">
              <el-input v-model="modifySalaryForm.username" disabled></el-input>
            </el-form-item>
            <el-form-item label="薪酬级别：">
              <el-select v-model="modifySalarySelected" placeholder="请选择">
                <el-option
                  v-for="item in modifySalarySelect"
                  :key="item.id"
                  :label="item.notes"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
              <el-form-item >
                <span class="button_span">
                  <el-button type="primary" @click="modifyUserSalary()" size="small">修改</el-button>
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
      userSalaryList: [],
      page_sizes: [1, 2, 5, 10],
      page_size: 5,
      userSalaryListNum: 0,
      current_page: 1,
      modifySalaryDialogVisable: false,
      modifySalaryForm: {
        username: '',
        salary_id: ''
      },
      modifySalarySelected: '',
      modifySalarySelect: []
    }
  },
  created () {
    this.searchUserSalary()
  },
  methods: {
    // 获取员工薪酬情况
    searchUserSalary () {
      const currentIndex = (this.current_page - 1) * this.page_size
      this.$http.get('/admin/get_alluser_salary?username=' + this.userName + '&current_index=' + currentIndex + '&page_size=' + this.page_size)
        .then(res => {
          if (res.data.state === 200) {
            this.userSalaryList = res.data.data
            this.userSalaryListNum = res.data.total
          } else {
            this.$message.error('获取员工薪酬列表失败')
          }
        })
        .catch(_error => {
          this.$message.error('获取员工薪酬列表失败')
        })
    },
    // 显示修改对话框
    showModifyDialog (row) {
      this.modifySalaryDialogVisable = true
      this.getSalarySelect()
      this.modifySalaryForm.username = row.username
      this.modifySalaryForm.salary_id = row.salary_id
      this.modifySalarySelected = row.salary_id
      console.log(row)
    },
    // 修改page_size
    handleSizeChange (pageSize) {
      this.page_size = pageSize
      this.searchUserSalary()
    },
    // 修改current_page
    handleCurrentChange (currentPage) {
      this.current_page = currentPage
      this.searchUserSalary()
    },
    // 获取所有薪酬
    getSalarySelect () {
      this.$http.get('/admin/get_all_salary?notes=&current_index=0&page_size=100')
        .then(res => {
          if (res.data.state === 200) {
            console.log(res.data.data)
            this.modifySalarySelect = res.data.data
          } else {
            this.$message.error('获取薪酬下拉框失败')
          }
        })
        .catch(_error => {
          this.$message.error('获取薪酬下拉框失败')
        })
    },
    // 修改用户薪酬级别
    modifyUserSalary () {
      this.modifySalaryForm.salary_id = this.modifySalarySelected
      this.$http.put('/admin/modify_user_salary', this.modifySalaryForm)
        .then(res => {
          if (res.data.state === 200) {
            this.modifySalaryDialogVisable = false
            this.searchUserSalary()
            this.$message.success('修改用户薪酬级别成功')
          } else {
            this.$message.error('获取薪酬级别失败')
          }
        })
        .catch(_error => {
          this.$message.error('获取薪酬级别失败')
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
</style>
