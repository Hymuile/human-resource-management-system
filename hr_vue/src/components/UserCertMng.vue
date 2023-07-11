<template>
    <div>
        <!-- 面包屑 -->
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>证书管理</el-breadcrumb-item>
            <el-breadcrumb-item>个人证书管理</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card>
            <el-row>
              <el-col :span="10">
                <div class="grid-content bg-purple-dark">
                <el-col :span="10" class="addBtn">
                    <el-button type="primary" @click="showAddCertDialog()">发起新增证书流程</el-button>
                </el-col>
                <el-col :span="5" class="addBtn">
                    <el-button type="info" @click="searchCert()">刷新</el-button>
                </el-col>
                </div>
              </el-col>
            </el-row>
            <el-table :data="certList" border style="width: 100%">
              <el-table-column
                prop="cert_no"
                label="证书编号"
                width="200">
              </el-table-column>
              <el-table-column
                prop="cert_name"
                label="证书名称"
                width="280">
              </el-table-column>
              <el-table-column
                prop="cert_date"
                label="取得日期">
              </el-table-column>
              <el-table-column
                prop=""
                label="操作" width="180">
                <template slot-scope="scope">
                  <el-button type="success" @click="showModifyDialog(scope.row)" size="mini">发起修改流程</el-button>
                </template>
              </el-table-column>
            </el-table>
        </el-card>
        <!-- 新增证书 -->
        <el-dialog
          title="新增证书" :visible.sync="addCertDialogVisable" width="35%" @close="resetDialog">
          <el-form ref="addFormRef" :model="addCertForm" label-width="110px" :rules="addCertFormRules">
            <el-form-item label="证书编号：" prop="cert_no">
              <el-input v-model="addCertForm.cert_no"></el-input>
            </el-form-item>
            <el-form-item label="证书名称：" prop="cert_name">
              <el-input v-model="addCertForm.cert_name"></el-input>
            </el-form-item>
            <el-form-item label="取得时间：" prop="cert_name">
              <el-date-picker value-format="yyyy-MM-dd"
                    v-model="addCertForm.cert_date"  type="date" placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
            <el-form-item >
              <span class="button_span">
                <el-button type="primary" @click="addCert()" size="small">添加</el-button>
                <el-button type="info" @click="resetDialog ()" size="small">重置</el-button>
              </span>
            </el-form-item>
        </el-form>
        </el-dialog>
        <!-- 修改证书 -->
        <el-dialog
          title="修改证书" :visible.sync="modifyCertDialogVisable" width="35%" @close="resetModifyDialog">
          <el-form ref="modifyFormRef" :model="modifyCertForm" label-width="110px" :rules="modifyCertFormRules">
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
      certList: [],
      addCertDialogVisable: false,
      addCertForm: {
        cert_no: '',
        cert_name: '',
        cert_date: ''
      },
      addCertFormRules: {
        cert_no: [{ required: true, message: '证书编号不能为空', trigger: 'blur' }],
        cert_name: [{ required: true, message: '证书名称不能为空', trigger: 'blur' }],
        cert_date: [{ required: true, message: '证书获取时间不能为空', trigger: 'blur' }]
      },
      modifyCertDialogVisable: false,
      modifyCertForm: {
        pre_id: '',
        cert_no: '',
        cert_name: '',
        cert_date: ''
      },
      modifyCertFormRules: {
        cert_no: [{ required: true, message: '证书编号不能为空', trigger: 'blur' }],
        cert_name: [{ required: true, message: '证书名称不能为空', trigger: 'blur' }],
        cert_date: [{ required: true, message: '证书获取时间不能为空', trigger: 'blur' }]
      }
    }
  },
  created () {
    this.searchCert()
  },
  methods: {
    // 查询个人证书
    searchCert () {
      this.$http.get('/user/get_user_cert')
        .then(res => {
          if (res.data.state === 200) {
            this.certList = res.data.data
          } else {
            this.$message.error('请获取本人证书失败')
          }
        })
        .catch(_error => {
          this.$message.error('获取证书失败')
        })
    },
    // 显示发起证书流程对话框
    showAddCertDialog () {
      this.addCertDialogVisable = true
    },
    // 重置发起证书流程对话框
    resetDialog () {
      this.$refs.addFormRef.resetFields()
    },
    // 发起证书流程
    addCert () {
      // 先进行表单验证
      this.$refs.addFormRef.validate(valid => {
        console.log(this.addCertForm)
        if (valid === true) {
          // 访问后端接口，发起证书流程
          this.$http.post('/user/add_cert', this.addCertForm)
            .then(res => {
              if (res.data.state === 200) {
                this.addCertDialogVisable = false
                this.$message.success('发起新增证书流程成功')
              } else {
                this.$message.error('发起新增证书流程失败')
              }
            })
            .catch(_error => {
              this.$message.error('发起新增证书流程失败' + _error)
            })
        }
      })
    },
    // 显示修改证书对话框
    showModifyDialog (row) {
      console.log(row)
      this.modifyCertDialogVisable = true
      this.modifyCertForm.pre_id = row.id
      this.modifyCertForm.cert_no = row.cert_no
      this.modifyCertForm.cert_name = row.cert_name
      this.modifyCertForm.cert_date = row.cert_date
      // console.log(row)
    },
    // 重置修改证书对话框
    resetModifyDialog () {
      this.$refs.modifyFormRef.resetFields()
      this.searchCert()
    },
    // 修改证书
    modifyCert () {
      // 先进行表单验证
      this.$refs.modifyFormRef.validate(valid => {
        if (valid === true) {
          this.$http.post('/user/modify_user_cert', this.modifyCertForm)
            .then(res => {
              if (res.data.state === 200) {
                this.modifyCertDialogVisable = false
                this.$message.success('发起修改证书流程成功')
              } else {
                this.$message.error('发起修改证书流程失败')
              }
            })
            .catch(_error => {
              this.$message.error('发起修改证书流程失败')
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
</style>
