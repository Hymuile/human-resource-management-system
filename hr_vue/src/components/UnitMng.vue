<template>
    <div>
        <!-- 面包屑 -->
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>用户管理</el-breadcrumb-item>
            <el-breadcrumb-item>机构管理</el-breadcrumb-item>
        </el-breadcrumb>

        <!-- 卡片 -->
        <el-card>
            <el-row>
              <el-col :span="10">
                <div class="grid-content bg-purple-dark">
                <el-input placeholder="请输入机构名称" v-model="unitName" @change="searchUnit()">
                  <el-button slot="append" icon="el-icon-search" @click="searchUnit()"></el-button>
                </el-input>
                </div>
              </el-col>
              <el-col :span="5" class="addBtn">
                <el-button type="primary" @click="addUnitDialogVisable = true">添加机构</el-button>
              </el-col>
            </el-row>
            <el-table :data="unitList" border style="width: 100%">
              <el-table-column
                prop="name"
                label="名称"
                width="180">
              </el-table-column>
              <el-table-column
                prop="address"
                label="地址"
                width="180">
              </el-table-column>
              <el-table-column
                prop="phone"
                label="电话">
              </el-table-column>
              <el-table-column
                prop="manager"
                label="主管用户名">
              </el-table-column>
              <el-table-column
                prop=""
                label="操作">
                <template slot-scope="scope">
                  <el-button type="danger" @click="deleteUnit(scope.row)" size="mini">删除</el-button>
                  <el-button type="success" @click="modifyUnit(scope.row)" size="mini">修改</el-button>
                </template>
              </el-table-column>
            </el-table>
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :page-sizes="page_sizes"
              :page-size="page_size"
              layout="total, sizes, prev, pager, next"
              :total="unitListNum">
            </el-pagination>
        </el-card>
        <el-dialog
          title="添加机构" :visible.sync="addUnitDialogVisable" width="35%" @close="resetDialog">
          <el-form ref="addFormRef" :model="addUnitForm" label-width="110px" :rules="addUnitFormRules">
            <el-form-item label="机构名称：" prop="name">
              <el-input v-model="addUnitForm.name"></el-input>
            </el-form-item>
            <el-form-item label="机构地址：" prop="address">
              <el-input v-model="addUnitForm.address"></el-input>
            </el-form-item>
            <el-form-item label="机构电话：" prop="phone">
              <el-input v-model="addUnitForm.phone"></el-input>
            </el-form-item>
            <el-form-item label="主管用户名：" prop="manager">
              <el-input v-model="addUnitForm.manager"></el-input>
            </el-form-item>
            <el-form-item >
              <span class="button_span">
                <el-button type="primary" @click="addUnit">添加</el-button>
                <el-button type="info" @click="resetDialog">重置</el-button>
              </span>
            </el-form-item>
        </el-form>
        </el-dialog>
        <el-dialog
          title="修改机构" :visible.sync="modifyUnitDialogVisable" width="35%" @close="resetModifiDialog">
          <el-form ref="modifyFormRef" :model="modifyUnitForm" label-width="110px" :rules="modifyUnitFormRules">
            <el-form-item label="机构名称：" prop="name">
              <el-input v-model="modifyUnitForm.name"></el-input>
            </el-form-item>
            <el-form-item label="机构地址：" prop="address">
              <el-input v-model="modifyUnitForm.address"></el-input>
            </el-form-item>
            <el-form-item label="机构电话：" prop="phone">
              <el-input v-model="modifyUnitForm.phone"></el-input>
            </el-form-item>
            <el-form-item label="主管用户名：" prop="manager">
              <el-input v-model="modifyUnitForm.manager"></el-input>
            </el-form-item>
            <el-form-item >
              <span class="button_span">
                <el-button type="primary" @click="modifyUnit2" size="small">修改</el-button>
                <el-button type="info" @click="resetDialog" size="small">重置</el-button>
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
      unitName: '',
      unitList: [],
      unitListNum: 0,
      page_sizes: [1, 2, 5, 10],
      page_size: 5,
      current_page: 1,
      addUnitDialogVisable: false,
      addUnitForm: {
        name: '',
        address: '',
        phone: '',
        manager: ''
      },
      addUnitFormRules: {
        name: [
          { required: true, message: '机构名称不能为空', trigger: 'blur' }
        ],
        address: [
          { required: true, message: '地址不能为空', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '电话不能为空', trigger: 'blur' }
        ],
        manager: [
          { required: true, message: '主管用户名不能为空', trigger: 'blur' }
        ]
      },
      modifyUnitDialogVisable: false,
      modifyUnitForm: {
        id: '',
        name: '',
        address: '',
        phone: '',
        manager: ''
      },
      modifyUnitFormRules: {
        name: [
          { required: true, message: '机构名称不能为空', trigger: 'blur' }
        ],
        address: [
          { required: true, message: '地址不能为空', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '电话不能为空', trigger: 'blur' }
        ],
        manager: [
          { required: true, message: '主管用户名不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created () {
    this.searchUnit()
  },
  methods: {
    searchUnit () {
      const currentIndex = (this.current_page - 1) * this.page_size
      this.$http.get('/user/getunitbyname?name=' + this.unitName + '&current_index=' + currentIndex + '&page_size=' + this.page_size)
        .then(res => {
          console.log(res)
          if (res.data.state === 200) {
            this.unitList = res.data.data
            this.unitListNum = res.data.allnum
            // this.$message.success('获取机构列表成功')
          } else {
            this.$message.error(res.data.msg)
          }
        })
        .catch(error => {
          console.log(error)
          sessionStorage.clear()
          this.$router.push('/login')
          this.$message.error('登录已过期，请重新登录')
        })
    },
    // 选取数量
    handleSizeChange (pageSize) {
      this.page_size = pageSize
      this.searchUnit()
    },

    // 当前页
    handleCurrentChange (currentPage) {
      this.current_page = currentPage
      this.searchUnit()
    },
    // 重置表单
    resetDialog () {
      this.$refs.addFormRef.resetFields()
    },
    // 添加机构
    addUnit () {
      // 先进行验证
      this.$refs.addFormRef.validate(valid => {
        if (valid === true) {
          // 表单验证通过，访问后端接口，添加机构
          this.$http.post('/admin/create_unit', this.addUnitForm)
            .then(res => {
              console.log(res)
              if (res.data.state === 200) {
                this.$message.success('添加机构成功')
                this.addUnitDialogVisable = false
                this.searchUnit()
              } else {
                this.$message.error(res.data.msg)
              }
            })
            .catch(error => {
              this.$message.error(error)
            })
        }
      })
    },
    // 删除机构
    deleteUnit (row) {
      // console.log(row)
      this.$confirm('是否确认删除机构？（这可能会导致人员找不到对应的机构）')
        .then(async => {
        // 确认删除
          this.$http.delete('/admin/deleteunit?id=' + row.id)
            .then(res => {
              if (res.data.state === 200) {
                this.$message.success('已删除机构')
                this.searchUnit()
              } else {
                this.$message.error('删除机构失败')
              }
            })
        })
    },
    // 弹出修改机构对话框
    modifyUnit (row) {
      this.modifyUnitDialogVisable = true
      this.modifyUnitForm = row
    },
    // 修改机构
    modifyUnit2 () {
      this.$refs.modifyFormRef.validate(valid => {
        if (valid === true) {
          // 表单验证通过，修改机构
          this.$http.put('/admin/modify_unit', this.modifyUnitForm)
            .then(res => {
              if (res.data.state === 200) {
                this.modifyUnitDialogVisable = false
                this.searchUnit()
                this.$message.success('修改机构成功')
              } else {
                this.$message.error(res.data.msg)
              }
            })
        }
      })
    },
    // 重置修改对话框
    resetModifiDialog () {
      this.$refs.modifyFormRef.resetFields()
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
