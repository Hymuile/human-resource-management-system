<template>
    <div>
        <!-- 面包屑 -->
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>设置中心</el-breadcrumb-item>
            <el-breadcrumb-item>修改密码</el-breadcrumb-item>
        </el-breadcrumb>
        <el-row>
              <el-col :span="10">
                <el-form ref="changePasswordFormRef" :model="changePasswordForm" label-width="110px" :rules="changePasswordFormRules">
                    <el-form-item label="旧密码：" prop="oldPassword">
                        <el-input v-model="changePasswordForm.oldPassword" type="password"></el-input>
                    </el-form-item>
                    <el-form-item label="新密码：" prop="newPassword">
                        <el-input v-model="changePasswordForm.newPassword" type="password"></el-input>
                    </el-form-item>
                    <el-form-item label="重复新密码：" prop="repeadNewPassword">
                        <el-input v-model="changePasswordForm.repeadNewPassword" type="password"></el-input>
                    </el-form-item>
                    <el-form-item >
                    <span class="button_span">
                        <el-button type="primary" @click="changePassword()" size="small">提交</el-button>
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
      changePasswordForm: {
        oldPassword: '',
        newPassword: '',
        repeadNewPassword: ''
      },
      changePasswordFormRules: {
        oldPassword: [{ required: true, message: '旧密码不能为空', trigger: 'blur' }],
        newPassword: [{ required: true, message: '新密码不能为空', trigger: 'blur' }],
        repeadNewPassword: [{ required: true, message: '重复新密码不能为空', trigger: 'blur' }]
      }
    }
  },
  created () {

  },
  methods: {
    // 访问后端接口，修改密码
    changePassword () {
      // 先检查新密码是否一致
      if (this.changePasswordForm.newPassword === this.changePasswordForm.repeadNewPassword) {
        // 先进行表单验证
        this.$refs.changePasswordFormRef.validate(valid => {
          if (valid === true) {
            // 验证通过，访问后端接口)
            this.$http.put('/user/modify_user_password?oldPassword=' + this.changePasswordForm.oldPassword + '&newPassword=' + this.changePasswordForm.newPassword)
              .then(res => {
                if (res.data.state === 200) {
                  sessionStorage.clear()
                  this.$router.replace('/login')
                  this.$message.success('修改密码成功，请使用新密码登录')
                } else {
                  this.$message.error('原密码不正确')
                }
              })
          }
        })
      } else {
        this.$message.error('两次新密码不一致')
      }
    }
  }
}
</script>
