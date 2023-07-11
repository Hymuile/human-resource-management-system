<template>
    <div>
        <!-- 面包屑 -->
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>薪酬管理</el-breadcrumb-item>
            <el-breadcrumb-item>个人薪酬查询</el-breadcrumb-item>
        </el-breadcrumb>
        <el-row>
              <el-col :span="10">
                <el-form :model="SalaryForm" label-width="110px">
                    <el-form-item label="用户名：">
                    <el-input v-model="SalaryForm.username" width="100px"  disabled></el-input>
                    </el-form-item>
                    <el-form-item label="薪酬级别" prop="address">
                        <el-input v-model="SalaryForm.notes" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="薪酬" prop="phone">
                        <el-input v-model="SalaryForm.salary" disabled></el-input>
                    </el-form-item>
                </el-form>
              </el-col>
        </el-row>
    </div>
</template>
<script>
export default {
  data () {
    return {
      SalaryForm: {
        username: '',
        notes: '',
        salary: 0
      }
    }
  },
  created () {
    this.getUserSalary()
  },
  methods: {
    getUserSalary () {
      this.$http.get('/user/get_user_salary')
        .then(res => {
          if (res.data.state === 200) {
            this.SalaryForm = res.data.data
          } else {
            this.$message.error('获取员工薪酬信息失败')
          }
        })
        .catch(_error => {
          this.$message.error('获取员工薪酬信息失败')
        })
    }
  }
}
</script>
