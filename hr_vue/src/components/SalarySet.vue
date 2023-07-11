<template>
    <div>
        <!-- 面包屑 -->
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>薪酬管理</el-breadcrumb-item>
            <el-breadcrumb-item>薪酬级别设置</el-breadcrumb-item>
        </el-breadcrumb>
        <!-- 卡片 -->
        <el-card>
            <el-row>
              <el-col :span="10">
                <div class="grid-content bg-purple-dark">
                <el-input placeholder="请输入薪酬名称" v-model="salaryName" @change="searchSalary()">
                  <el-button slot="append" icon="el-icon-search" @click="searchSalary()"></el-button>
                </el-input>
                </div>
              </el-col>
              <el-col :span="5" class="addBtn">
                <el-button type="primary" @click="showAddSalaryDialog()">添加薪酬级别</el-button>
              </el-col>
            </el-row>
            <el-table :data="salaryList" border style="width: 100%">
              <el-table-column
                prop="notes"
                label="薪酬名"
                width="300">
              </el-table-column>
              <el-table-column
                prop="salary"
                label="薪酬"
                width="280">
              </el-table-column>
              <el-table-column
                prop=""
                label="操作">
                <template slot-scope="scope">
                  <el-button type="danger" @click="deleteSalary(scope.row)" size="mini">删除</el-button>
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
              :total="SalaryListNum">
            </el-pagination>
        </el-card>
         <!-- 添加薪酬级别-->
        <el-dialog
          title="添加薪酬级别" :visible.sync="addSalaryDialogVisable" width="35%" @close="resetDialog">
          <el-form ref="addFormRef" :model="addSalaryForm" label-width="110px" :rules="addSalaryFormRules">
            <el-form-item label="薪酬名：" prop="notes">
              <el-input v-model="addSalaryForm.notes"></el-input>
            </el-form-item>
            <el-form-item label="薪酬：" prop="salary">
              <el-input v-model="addSalaryForm.salary"></el-input>
            </el-form-item>
            <el-form-item >
              <span class="button_span">
                <el-button type="primary" @click="addSalary()" size="small">添加</el-button>
                <el-button type="info" @click="resetDialog ()" size="small">重置</el-button>
              </span>
            </el-form-item>
        </el-form>
        </el-dialog>
         <!-- 修改薪酬级别-->
        <el-dialog
          title="修改薪酬级别" :visible.sync="modifySalaryDialogVisable" width="35%" @close="resetModifyDialog">
          <el-form ref="modifyFormRef" :model="modifySalaryForm" label-width="110px" :rules="modifySalaryFormRules">
            <el-form-item label="薪酬名：" prop="notes">
              <el-input v-model="modifySalaryForm.notes"></el-input>
            </el-form-item>
            <el-form-item label="薪酬：" prop="salary">
              <el-input v-model="modifySalaryForm.salary"></el-input>
            </el-form-item>
            <el-form-item >
              <span class="button_span">
                <el-button type="primary" @click="modifySalary()" size="small">修改</el-button>
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
      salaryName: '',
      salaryList: [],
      page_sizes: [1, 2, 5, 10],
      page_size: 5,
      current_page: 1,
      SalaryListNum: 0,
      addSalaryDialogVisable: false,
      addSalaryForm: {
        notes: '',
        salary: 0
      },
      addSalaryFormRules: {
        notes: [{ required: true, message: '薪酬名称不能为空', trigger: 'blur' }],
        salary: [{ required: true, message: '薪酬不能为空', trigger: 'blur' }]
      },
      modifySalaryDialogVisable: false,
      modifySalaryForm: {
        notes: '',
        salary: 0
      },
      modifySalaryFormRules: {
        notes: [{ required: true, message: '薪酬名称不能为空', trigger: 'blur' }],
        salary: [{ required: true, message: '薪酬不能为空', trigger: 'blur' }]
      }
    }
  },
  created () {
    this.searchSalary()
  },
  methods: {
    // 查询薪酬
    searchSalary () {
      const currentIndex = (this.current_page - 1) * this.page_size
      this.$http.get('/admin/get_all_salary?notes=' + this.salaryName + '&current_index=' + currentIndex + '&page_size=' + this.page_size)
        .then(res => {
          if (res.data.state === 200) {
            console.log(res.data)
            this.salaryList = res.data.data
            this.SalaryListNum = res.data.total
          } else {
            this.$message.error('获取薪酬级别失败')
          }
        })
        .catch(_error => {
          this.$message.error('获取薪酬级别失败' + _error)
        })
    },
    // 修改page_size
    handleSizeChange (pageSize) {
      this.page_size = pageSize
      this.searchSalary()
    },
    // 修改page
    handleCurrentChange (currentPage) {
      this.current_page = currentPage
      this.searchSalary()
    },
    // 删除薪酬
    deleteSalary (row) {
      // 先弹框提醒
      this.$confirm('是否确认删除薪酬级别？')
        .then(res => {
          this.$http.delete('/admin/delete_salary?id=' + row.id)
            .then(res => {
              if (res.data.state === 200) {
                this.searchSalary()
                this.$message.success('删除薪酬级别成功')
              } else {
                this.$http.error('删除薪酬级别失败')
              }
            })
            .catch(_error => {
              this.$http.error('删除薪酬级别失败')
            })
        })
        .catch(_error => {
          this.$message.info('已取消删除')
        })
    },
    // 显示修改薪酬对话框
    showModifyDialog (row) {
      this.modifySalaryDialogVisable = true
      this.modifySalaryForm = row
    },
    // 显示添加薪酬对话框
    showAddSalaryDialog () {
      this.addSalaryDialogVisable = true
    },
    // 重置添加薪酬级别对话框
    resetDialog () {
      this.$refs.addFormRef.resetFields()
    },
    // 添加薪酬级别
    addSalary () {
      // 先进行表单验证
      this.$refs.addFormRef.validate(valid => {
        if (valid === true) {
          // 验证通过，访问后端接口
          this.$http.post('/admin/create_salary', this.addSalaryForm)
            .then(res => {
              if (res.data.state === 200) {
                this.addSalaryDialogVisable = false
                this.searchSalary()
                this.$message.success('添加薪酬级别成功')
              } else {
                this.$message.error('添加薪酬级别失败')
              }
            })
            .catch(_error => {
              this.$message.error('添加薪酬级别失败')
            })
        }
      })
    },
    // 重置修改薪酬对话框
    resetModifyDialog () {
      this.$refs.modifyFormRef.resetFields()
    },
    // 修改薪酬
    modifySalary () {
      this.$refs.modifyFormRef.validate(valid => {
        if (valid === true) {
          // 验证通过，访问后端接口
          this.$http.post('/admin/modify_salary', this.modifySalaryForm)
            .then(res => {
              if (res.data.state === 200) {
                this.modifySalaryDialogVisable = false
                this.searchSalary()
                this.$message.success('修改薪酬级别成功')
              } else {
                this.$message.error('修改薪酬级别失败')
              }
            })
            .catch(_error => {
              this.$message.error('修改薪酬级别失败')
            })
        }
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
