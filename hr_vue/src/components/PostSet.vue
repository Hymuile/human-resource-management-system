<template>
    <div>
        <!-- 面包屑 -->
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>岗位管理</el-breadcrumb-item>
            <el-breadcrumb-item>岗位设置</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card>
            <el-row>
              <el-col :span="10">
                <div class="grid-content bg-purple-dark">
                <el-input placeholder="请输入岗位名称" v-model="postName" @change="searchPost()">
                  <el-button slot="append" icon="el-icon-search" @click="searchPost()"></el-button>
                </el-input>
                </div>
              </el-col>
              <el-col :span="5" class="addBtn">
                <el-button type="primary" @click="showAddPostDialog()">添加岗位</el-button>
              </el-col>
            </el-row>
            <el-table :data="postList" border style="width: 100%">
              <el-table-column
                prop="name"
                label="岗位名称"
                width="250">
              </el-table-column>
              <el-table-column
                prop="notes"
                label="岗位说明"
                width="350">
              </el-table-column>
              <el-table-column
                prop=""
                label="操作">
                <template slot-scope="scope">
                  <el-button type="danger" @click="deletePost(scope.row)" size="mini">删除</el-button>
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
              :total="postListNum">
            </el-pagination>
        </el-card>
        <!-- 新建岗位-->
        <el-dialog
          title="添加岗位" :visible.sync="addPostDialogVisable" width="35%" @close="resetAddDialog">
          <el-form ref="addFormRef" :model="addPostForm" label-width="110px" :rules="addPostFormRules">
            <el-form-item label="岗位名称：" prop="name">
              <el-input v-model="addPostForm.name"></el-input>
            </el-form-item>
            <el-form-item label="岗位说明：" prop="notes">
              <el-input v-model="addPostForm.notes" ></el-input>
            </el-form-item>
            <el-form-item >
              <span class="button_span">
                <el-button type="primary" @click="addPost()" size="small">添加</el-button>
                <el-button type="info" @click="resetAddDialog ()" size="small">重置</el-button>
              </span>
            </el-form-item>
        </el-form>
        </el-dialog>
        <!-- 修改岗位-->
        <el-dialog
          title="修改岗位" :visible.sync="modifyPostDialogVisable" width="35%" @close="resetModifyDialog">
          <el-form ref="modifyFormRef" :model="modifyPostForm" label-width="110px" :rules="modifyPostFormRules">
            <el-form-item label="岗位名称：" prop="name">
              <el-input v-model="modifyPostForm.name"></el-input>
            </el-form-item>
            <el-form-item label="岗位说明：" prop="notes">
              <el-input v-model="modifyPostForm.notes" ></el-input>
            </el-form-item>
            <el-form-item >
              <span class="button_span">
                <el-button type="primary" @click="modifyPost()" size="small">修改</el-button>
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
      postName: '',
      postList: [],
      page_sizes: [1, 2, 5, 10],
      page_size: 5,
      postListNum: 0,
      current_page: 1,
      addPostDialogVisable: false,
      addPostForm: {
        name: '',
        notes: ''
      },
      addPostFormRules: {
        name: [{ required: true, message: '岗位名称不能为空', trigger: 'blur' }],
        notes: [{ required: true, message: '岗位说明不能为空', trigger: 'blur' }]
      },
      modifyPostDialogVisable: false,
      modifyPostForm: '',
      modifyPostFormRules: {
        name: [{ required: true, message: '岗位名称不能为空', trigger: 'blur' }],
        notes: [{ required: true, message: '岗位说明不能为空', trigger: 'blur' }]
      }
    }
  },
  created () {
    this.searchPost()
  },
  methods: {
    // 查询岗位
    searchPost () {
      const currentIndex = (this.current_page - 1) * this.page_size
      this.$http.get('/user/getpost?name=' + this.postName + '&current_index=' + currentIndex + '&page_size=' + this.page_size)
        .then(res => {
          if (res.data.state === 200) {
            this.postList = res.data.data
            this.postListNum = res.data.total
          } else {
            this.$message.error('获取岗位失败')
          }
        })
        .catch(_error => {
          this.$message.error('获取岗位失败')
        })
    },
    // 删除岗位
    deletePost (row) {
      this.$confirm('确定删除岗位吗？（员工信息中可能找不到该位）')
        .then(res => {
          // 访问后端接口，删除岗位
          this.$http.put('/admin/delte_post?id=' + row.id)
            .then(res => {
              this.searchPost()
              this.$message.success('删除岗位成功')
            })
            .catch(_error => {
              this.$message.error('删除岗位失败')
            })
        })
        .catch(_error => {
          this.$message.info('已取消删除')
        })
    },
    showModifyDialog (row) {
      this.modifyPostDialogVisable = true
      this.modifyPostForm = row
    },
    // 处理修改页大小
    handleSizeChange (pageSize) {
      this.page_size = pageSize
      this.searchPost()
    },
    // 修改当前页
    handleCurrentChange (currentPage) {
      this.current_page = currentPage
      this.searchPost()
    },
    // 重置添加岗位对话框
    resetAddDialog () {
      this.$refs.addFormRef.resetFields()
      this.searchPost()
    },
    // 显示增加岗位对话框
    showAddPostDialog () {
      this.addPostDialogVisable = true
    },
    // 添加岗位
    addPost () {
      // 先进行表单验证
      this.$refs.addFormRef.validate(valid => {
        if (valid === true) {
          // 表单验证通过，访问后端接口，新增岗位
          this.$http.post('/admin/create_post', this.addPostForm)
            .then(res => {
              if (res.data.state === 200) {
                this.addPostDialogVisable = false
                this.searchPost()
                this.$message.success('添加岗位成功')
              } else {
                this.$message.error(res.data.msg)
              }
            })
            .catch(_error => {
              this.$message.error('添加岗位失败')
            })
        }
      })
    },
    // 重置修改岗位对话框
    resetModifyDialog () {
      this.$refs.modifyFormRef.resetFields()
    },
    // 修改岗位
    modifyPost () {
      // 先进行表单验证
      this.$refs.modifyFormRef.validate(valid => {
        if (valid === true) {
          // 表单验证通过，访问后端接口，修改岗位信息
          this.$http.put('/admin/modify_post', this.modifyPostForm)
            .then(res => {
              if (res.data.state === 200) {
                this.modifyPostDialogVisable = false
                this.searchPost()
                this.$message.success('修改岗位信息成功')
              } else {
                this.$message.error('修改岗位信息失败')
              }
            })
            .catch(_error => {
              this.$message.error('修改岗位信息失败')
            })
        }
      })
    }
  }
}
</script>
<style scoped>
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
