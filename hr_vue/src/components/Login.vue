<template>
    <div class="login_container">
        <div class="login_bigbox">

            <span class="title_span">人事管理系统</span>
            <div class="login_box">
                <el-form ref="loginFormRef" :model="loginForm"  label-width="" class="login_form" :rules="loginFormRules">
                    <el-form-item label="" prop="username">
                        <el-input v-model="loginForm.username" prefix-icon="el-icon-user" ></el-input>
                    </el-form-item>
                    <el-form-item label="" prop="password">
                        <el-input v-model="loginForm.password" prefix-icon="el-icon-lock" type="password"></el-input>
                    </el-form-item>
                    <el-form-item class="btns">
                        <el-button type="primary" @click="login">登录</el-button>
                        <el-button type="info" @click="reset">重置</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>

    </div>
</template>
<script>
import Qs from 'qs'
export default {
  data () {
    return {
      loginForm: {
        username: 'admin',
        password: '123'
      },
      loginFormRules: {
        username: [
          { required: true, message: '用户名不能为空', trigger: 'blur' },
          { min: 3, max: 20, message: '用户名必须在3-20个字符之间', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' },
          { min: 3, max: 20, message: '密码必须在3-20个字符之间', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    login () {
    // 先进行表单预验证
      this.$refs.loginFormRef.validate(async valid => {
        if (valid === true) {
        // 表单预验证通过，向后台发送请求
          this.$http.post('login', Qs.stringify(this.loginForm))
            .then(res => {
              console.log(res.data)
              const token = res.data.token
              this.$message.success('登录成功')
              // 保存token
              sessionStorage.setItem('token', token)
              this.$router.push('/home')
            })
            .catch(error => {
              const error2 = error + ''
              if (error2.indexOf('401') !== -1) { this.$message.error('用户名或密码错误') } else this.$message.error('外星人把我们的服务器劫走了，刷新一下也许能抢回来哦！')
            })
        }
      })
    },
    reset () {
      this.$refs.loginFormRef.resetFields()
      this.$message.success('已重置登录框')
    }
  }

}
</script>
<style lang="less" scoped>
.login_container{
    background-color: #2b4b6b;
    //background-color: #555;
    height: 100%;
}
.login_bigbox{
    width: 350px;
    height: 300px;
    //background-color: #eee;
    border-radius: 3px;
    position: absolute;
    text-align: center;
    left: 50%;
    top: 50%;
    transform: translate(-50%,-50%);
}
.title_span{
    font-size: 35px;
    width: 350px;
    font-family: "Arial","Microsoft YaHei","黑体","宋体",sans-serif;
    position: absolute;
    left: 50%;
    transform: translate(-50%,-150%);
}
.login_box{
    width: 350px;
    height: 250px;
    background-color: #fff;
    border-radius: 3px;
}
.btns{
    display:flex;
    justify-content: flex-end;
}
.login_form{
    position: absolute;
    bottom: 15%;
    width: 100%;
    padding: 10px 20px;
    box-sizing: border-box;
}
</style>
