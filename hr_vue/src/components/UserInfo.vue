<template>
    <div>
        <!-- 面包屑 -->
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>设置中心</el-breadcrumb-item>
            <el-breadcrumb-item>个人基本信息</el-breadcrumb-item>
        </el-breadcrumb>
        <el-row>
              <el-col :span="10">
                <el-form ref="modifyFormRef" :model="userForm" label-width="110px" :rules="userFormRules">
                    <el-form-item label="用户名：">
                    <el-input v-model="userForm.username" width="100px"  disabled></el-input>
                    </el-form-item>
                    <el-form-item label="地址：" prop="address">
                        <el-input v-model="userForm.address"></el-input>
                    </el-form-item>
                    <el-form-item label="电话：" prop="phone">
                        <el-input v-model="userForm.phone" ></el-input>
                    </el-form-item>
                    <el-form-item label="邮箱：" prop="email">
                        <el-input v-model="userForm.email" ></el-input>
                    </el-form-item>
                    <el-form-item label="所在机构：">
                        <el-select v-model="userForm.unit" placeholder="请选择" disabled>
                            <el-option
                            v-for="item in unitSelect"
                            :key="item.id"
                            :label="item.name"
                            :value="item.id">
                            </el-option>
                        </el-select>
                        </el-form-item>
                        <el-form-item label="岗位：">
                        <el-select v-model="userForm.position" placeholder="请选择" disabled>
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
                        <el-button type="primary" @click="modifyUser()" size="small">提交</el-button>
                    </span>
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
      userForm: {
        username: '',
        address: '',
        phone: '',
        email: '',
        unit: '',
        position: ''
      },
      unitSelect: [],
      postSelect: [],
      userFormRules: {
        address: [{ required: true, message: '地址不能为空', trigger: 'blur' }],
        phone: [{ required: true, message: '电话不能为空', trigger: 'blur' }],
        email: [{ required: true, message: '邮箱不能为空', trigger: 'blur' }]
      }
    }
  },
  created () {
    this.getUnitSelect()
    this.getPostSelect()
    this.getUserInfo()
  },
  methods: {
    // 访问后端接口，获取机构下拉框
    getUnitSelect () {
      this.$http.get('/user/getunitbyname?name=&current_index=0&page_size=1000')
        .then(res => {
          // console.log(res.data)
          if (res.data.state === 200) {
            this.unitSelect = res.data.data
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
    getPostSelect () {
      this.$http.get('/user/getpost?name=&current_index=0&page_size=1000')
        .then(res => {
          if (res.data.state === 200) {
            // console.log(res.data.data)
            this.postSelect = res.data.data
          } else {
            this.$message.error('获取岗位失败')
          }
        })
        .catch(_error => {
          this.$message.error('获取岗位失败')
        })
    },
    // 获取用户信息
    getUserInfo () {
      // 获取用户信息
      this.$http.get('/user/get_userinfo')
        .then(res => {
          console.log(res.data)
          if (res.data.state === 200) {
            this.userForm = res.data.data
          }
        })
        .catch(_error => {
          this.$message.error('获取用户信息失败' + _error)
        })
    },
    // 修改本人信息
    modifyUser () {
      this.$refs.modifyFormRef.validate(valid => {
        if (valid === true) {
          // 表单验证通过，访问后端接口，修改用户信息
          this.$http.put('/user/modify_userinfo', this.userForm)
            .then(res => {
              if (res.data.state === 200) {
                this.getUserInfo()
                this.$message.success('修改个人信息成功')
              } else {
                this.$message.error('修改个人信息失败')
              }
            })
            .catch(_error => {
              this.$message.error('修改个人信息失败')
            })
        } else {
          // this.getUserInfo()
        }
      })
    }
  }
}
</script>
<style lang="less" scoped>

</style>
