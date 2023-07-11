<template>
    <div>
        <!-- 面包屑 -->
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>用户管理</el-breadcrumb-item>
            <el-breadcrumb-item>员工基本信息管理</el-breadcrumb-item>
        </el-breadcrumb>
        <!-- 卡片 -->
        <el-card>
            <el-row>
              <el-col :span="10">
                <div class="grid-content bg-purple-dark">
                <el-input placeholder="请输入用户名称" v-model="userName" @change="searchUser()">
                  <el-button slot="append" icon="el-icon-search" @click="searchUser()"></el-button>
                </el-input>
                </div>
              </el-col>
              <el-col :span="5" class="addBtn">
                <el-button type="primary" @click="showAddUserDialog()">添加用户</el-button>
              </el-col>
            </el-row>
            <el-table :data="userList" border style="width: 100%">
              <el-table-column
                prop="username"
                label="用户名"
                width="100">
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
                prop="email"
                label="邮箱">
              </el-table-column>
              <el-table-column
                prop="unit"
                label="所在机构">
              </el-table-column>
              <el-table-column
                prop="position"
                label="岗位">
              </el-table-column>
              <el-table-column
                prop=""
                label="操作" width="180">
                <template slot-scope="scope">
                  <el-button type="danger" @click="deleteUser(scope.row)" size="mini">删除</el-button>
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
              :total="unitListNum">
            </el-pagination>
        </el-card>
        <!-- 添加用户 -->
        <el-dialog
          title="添加用户" :visible.sync="addUserDialogVisable" width="35%" @close="resetDialog">
          <el-form ref="addFormRef" :model="addUserForm" label-width="110px" :rules="addUserFormRules">
            <el-form-item label="用户名：" prop="username">
              <el-input v-model="addUserForm.username"></el-input>
            </el-form-item>
            <el-form-item label="地址：" prop="address">
              <el-input v-model="addUserForm.address"></el-input>
            </el-form-item>
            <el-form-item label="电话：" prop="phone">
              <el-input v-model="addUserForm.phone"></el-input>
            </el-form-item>
            <el-form-item label="邮箱：" prop="email">
              <el-input v-model="addUserForm.email"></el-input>
            </el-form-item>
            <el-form-item label="所在机构：">
              <el-select v-model="unitSelected" placeholder="请选择">
                <el-option
                  v-for="item in unitSelect"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="岗位：">
              <el-select v-model="postSelected" placeholder="请选择">
                <el-option
                  v-for="item in postSelect"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item >
              <span class="button_span">
                <el-button type="primary" @click="addUser()" size="small">添加</el-button>
                <el-button type="info" @click="resetDialog ()" size="small">重置</el-button>
              </span>
            </el-form-item>
        </el-form>
        </el-dialog>
        <!-- 修改用户 -->
        <el-dialog
          title="修改用户" :visible.sync="modifyUserDialogVisable" width="35%" @close="resetModifyDialog">
          <el-form ref="modifyFormRef" :model="modifyUserForm" label-width="110px" :rules="modifyUserFormRules">
            <el-form-item label="用户名：" prop="username">
              <el-input v-model="modifyUserForm.username" disabled></el-input>
            </el-form-item>
            <el-form-item label="地址：" prop="address">
              <el-input v-model="modifyUserForm.address"></el-input>
            </el-form-item>
            <el-form-item label="电话：" prop="phone">
              <el-input v-model="modifyUserForm.phone"></el-input>
            </el-form-item>
            <el-form-item label="邮箱：" prop="email">
              <el-input v-model="modifyUserForm.email"></el-input>
            </el-form-item>
            <el-form-item label="所在机构：">
              <el-select v-model="modifyUnitSelected" placeholder="请选择">
                <el-option
                  v-for="item in modifyUnitSelect"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="岗位：">
              <el-select v-model="modifyPostSelected" placeholder="请选择">
                <el-option
                  v-for="item in modifyPostSelect"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
                </el-option>
              </el-select>
              </el-form-item>
              <el-form-item >
                <span class="button_span">
                  <el-button type="primary" @click="modifyUser()" size="small">修改</el-button>
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
      userList: [],
      page_sizes: [1, 2, 5, 10],
      page_size: 5,
      current_page: 1,
      unitListNum: 0,
      addUserDialogVisable: false,
      addUserForm: {
        username: '',
        address: '',
        phone: '',
        email: '',
        unit: '',
        position: ''
      },
      addUserFormRules: {
        username: [{ required: true, message: '用户名不能为空', trigger: 'blur' }],
        address: [{ required: true, message: '地址不能为空', trigger: 'blur' }],
        phone: [{ required: true, message: '电话不能为空', trigger: 'blur' }],
        email: [{ required: true, message: '邮箱不能为空', trigger: 'blur' }],
        unit: [{ required: true, message: '机构不能为空', trigger: 'blur' }],
        position: [{ required: true, message: '岗位不能为空', trigger: 'blur' }]
      },
      unitSelect: [],
      unitSelected: '',
      postSelect: [],
      postSelected: '',
      modifyUserDialogVisable: false,
      modifyUserForm: {
        username: '',
        address: '',
        phone: '',
        email: '',
        unit: '',
        position: ''
      },
      modifyUserFormRules: {
        username: [{ required: true, message: '用户名不能为空', trigger: 'blur' }],
        address: [{ required: true, message: '地址不能为空', trigger: 'blur' }],
        phone: [{ required: true, message: '电话不能为空', trigger: 'blur' }],
        email: [{ required: true, message: '邮箱不能为空', trigger: 'blur' }],
        unit: [{ required: true, message: '机构不能为空', trigger: 'blur' }],
        position: [{ required: true, message: '岗位不能为空', trigger: 'blur' }]
      },
      modifyUnitSelect: [],
      modifyUnitSelected: '',
      modifyPostSelect: [],
      modifyPostSelected: ''
    }
  },
  created () {
    this.searchUser()
  },
  methods: {
    searchUser () {
      const currentIndex = (this.current_page - 1) * this.page_size
      this.$http.get('/admin/getusersbyusername?username=' + this.userName + '&current_index=' + currentIndex + '&page_size=' + this.page_size)
        .then(res => {
          if (res.data.state === 200) {
            // console.log(res.data.data)
            this.userList = res.data.data
            this.unitListNum = res.data.allnum
          }
        })
    },
    // 修改page_size
    handleSizeChange (pageSize) {
      this.page_size = pageSize
      this.searchUser()
    },
    // 修改页
    handleCurrentChange (currentPage) {
      this.current_page = currentPage
      this.searchUser()
    },
    // 显示新增用户对话框
    showAddUserDialog () {
      this.addUserDialogVisable = true
      // 访问后端接口，获取机构下拉框
      this.getUnitSelect(0)
      // 访问后端接口，获取岗位下拉框
      this.getPostSelect(0)
    },
    // 访问后端接口，获取机构下拉框
    getUnitSelect (flag) {
      this.$http.get('/user/getunitbyname?name=&current_index=0&page_size=1000')
        .then(res => {
          // console.log(res.data)
          if (res.data.state === 200) {
            if (flag === 0) {
              this.unitSelect = res.data.data
            } else if (flag === 1) {
              this.modifyUnitSelect = res.data.data
            }
          } else {
            this.$message.error('获取机构失败')
          }
        })
        .catch(_error => {
          // console.log(error)
          this.$message.error('获取机构失败')
        })
    },
    // 访问后端，获取岗位下拉
    getPostSelect (flag) {
      this.$http.get('/user/getpost?name=&current_index=0&page_size=1000')
        .then(res => {
          if (res.data.state === 200) {
            // console.log(res.data.data)
            if (flag === 0) {
              this.postSelect = res.data.data
            } else if (flag === 1) {
              this.modifyPostSelect = res.data.data
            }
          } else {
            this.$message.error('获取岗位失败')
          }
        })
        .catch(_error => {
          this.$message.error('获取岗位失败')
        })
    },
    // 删除用户
    deleteUser (row) {
      this.$confirm('是否删除该用户？')
        .then(res => {
          // console.log(row)
          this.$http.put('/admin/delete_user?username=' + row.username)
            .then(res => {
              if (res.data.state === 200) {
                // 删除用户成功
                this.searchUser()
                this.$message.success('删除用户成功')
              } else {
                this.$message.error('删除用户失败')
              }
            })
            .catch(_error => {
              this.$message.error('删除用户失败')
            })
        })
        .catch(_error => {
          this.$message.info('已取消')
        })
    },
    // 重置对话框
    resetDialog () {
      this.$refs.addFormRef.resetFields()
    },
    // 新增用户
    addUser () {
      // 先进行表单验证
      this.$refs.addFormRef.validate(valid => {
        if (valid === true) {
          // 验证通过，访问后台，新增用户
          this.addUserForm.unit = this.unitSelected
          this.addUserForm.position = this.postSelected
          // console.log(this.addUserForm)
          this.$http.post('/admin/create_user', this.addUserForm)
            .then(res => {
              if (res.data.state === 200) {
              // 添加用户成功
                this.searchUser()
                this.addUserDialogVisable = false
                this.$message.success('添加用户成功')
              } else {
                // 添加用户失败
                this.$message.error(res.data.msg)
              }
            })
            .catch(_error => {
              this.$message.error('添加用户失败' + _error)
            })
        }
      })
    },
    // 显示对话框修改用户
    showModifyDialog (row) {
      console.log(row)
      this.modifyUserDialogVisable = true
      this.modifyUserForm.username = row.username
      this.modifyUserForm.phone = row.phone
      this.modifyUserForm.address = row.address
      this.modifyUserForm.email = row.email
      this.getModifyPostId(row.position)
      this.getModifyUnitId(row.unit)
      // 访问后端接口，获取机构下拉框
      this.getUnitSelect(1)
      // 访问后端接口，获取岗位下拉框
      this.getPostSelect(1)
    },
    // 重置修改对话框
    resetModifyDialog () {
      this.$refs.modifyFormRef.resetFields()
      this.modifyPostSelected = ''
      this.modifyUnitSelected = ''
    },
    // 访问后端接口，获取岗位的id
    getModifyPostId (name) {
      this.$http.get('/user/getpost?name=' + name + '&current_index=0&page_size=1')
        .then(res => {
          if (res.data.data[0]) {
            this.modifyPostSelected = res.data.data[0].id
          }
        })
    },
    // 访问后端接口，获取机构id
    getModifyUnitId (name) {
      this.$http.get('/user/getunitbyname?name=' + name + '&current_index=0&page_size=1')
        .then(res => {
          if (res.data.data[0]) {
            this.modifyUnitSelected = res.data.data[0].id
          }
        })
    },
    // 修改用户信息
    modifyUser () {
      this.$refs.modifyFormRef.validate(valid => {
        if (valid === true) {
          // 表单验证通过，访问后台接口
          this.modifyUserForm.unit = this.modifyUnitSelected
          this.modifyUserForm.position = this.modifyPostSelected
          // 访问后端接口
          this.$http.put('/user/modify_user', this.modifyUserForm)
            .then(res => {
              console.log(res.data)
              this.modifyUserDialogVisable = false
              this.searchUser()
              this.$message.success('修改用户成功')
            })
            .catch(error => {
              console.log(error)
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
