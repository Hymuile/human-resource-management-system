<template>

    <el-container class="home-container">
        <!-- 头部区域 -->
        <el-header>
            <div>
                <img src="../assets/logo.png">
                <span @click="backHome">人事管理系统</span>
            </div>
            <el-button type="info" @click="logout">退出</el-button></el-header>
        <el-container>
            <!-- 侧边栏 -->
            <el-aside :width="isCollapse? '64px':'200px'">
                <div class="toggle-button" @click="toggleCollapse">|||</div>
                <el-menu
                    :unique-opened="true" :router="true" :collapse="isCollapse" :collapse-transition="false"
                    class="el-menu-vertical-demo" :default-active="activePath"
                    background-color="#333744"
                    text-color="#fff"
                    active-text-color="#409eff">
                    <!-- 一级菜单 -->
                    <el-submenu v-for="item in menuList" :key="item.id" :index="item.id" >
                        <template slot="title">
                        <i :class="item.icon"></i>
                        <span>{{item.name}}</span>
                        </template>
                        <el-menu-item v-for="subitem in item.children" :key="subitem.id" :index="subitem.path"
                        @click="saveNavState(subitem.path)">
                            <template slot="title">
                            <i :class="subitem.icon"></i>
                            <span>{{subitem.name}}</span>
                            </template>
                        </el-menu-item>
                    </el-submenu>
                </el-menu>
            </el-aside>
            <!-- 主体区域 -->
            <el-main>
                <router-view></router-view>
            </el-main>
        </el-container>
    </el-container>
</template>
<script>
export default {
  data () {
    return {
      menuList: [],
      isCollapse: false,
      activePath: ''
    }
  },
  created () {
    this.getMenu()
    this.activePath = window.sessionStorage.getItem('activePath')
  },
  methods: {
    // 获取菜单
    getMenu () {
      this.$http.get('/user/getmenu')
        .then(res => {
          const data = res.data.data
          // console.log(data)
          this.menuList = data
        })
        .catch(error => {
          console.log(error)
        })
    },
    // 退出
    logout () {
      sessionStorage.clear()
      this.$router.push('/login')
    },
    // 点击按钮切换菜单的折叠与展开
    toggleCollapse () {
      this.isCollapse = !this.isCollapse
    },
    // 返回主页而
    backHome () {
      this.$router.push('/home')
    },
    // 保存链接的激活状态
    saveNavState (activePath) {
      window.sessionStorage.setItem('activePath', activePath)
    }
  }
}
</script>
<style lang="less" scoped>
.home-container{
    height: 100%;
}
.el-header{
    background-color: #373d41;
    display: flex;
    justify-content: space-between;
    padding-left: 0;
    align-items: center;
    color:#fff;
    font-size: 25px;
    >div{
        display: flex;
        align-items: center;
        span{
            margin-left: 10px;
        }
    }
}
.el-aside{
    background-color: #333744;
    .el-menu{
        border-right: none;
    }
}
.el-main{
    background-color: #eaedf1;
}
.toggle-button{
    background-color: #4A5064;
    color: #fff;
    font-size: 10px;
    line-height: 24px;
    text-align: center;
    letter-spacing: 0.2em;
    cursor: pointer;
}
</style>
