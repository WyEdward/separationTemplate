<template>
  <div>
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" label-width="120px" @keyup.enter.native="validAndSave('dataForm')">
      <el-row>
        <el-col :span="12">
          <el-form-item label="权限前台路由" prop="permissionUrl">
            <el-input v-model="dataForm.permissionUrl" placeholder="权限前台路由"></el-input>
          </el-form-item>
          <el-form-item label="权限后台名称" prop="permissionName">
            <el-input v-model="dataForm.permissionName" placeholder="权限后台名称"></el-input>
          </el-form-item>
          <el-form-item label="权限描述" prop="permissionDescription">
            <el-input v-model="dataForm.permissionDescription" placeholder="权限描述"></el-input>
          </el-form-item>
          <el-form-item label="权限所在父id" prop="permissionFid">
            <el-input v-model="dataForm.permissionFid" placeholder="权限所在父id"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item>
        <el-button type="primary" @click="validAndSave('dataForm')">保存</el-button>
        <el-button @click="reset()">重置</el-button>
        <el-button @click="printTreePermission()">在控制台输出树形权限</el-button>
      </el-form-item>

    </el-form>
  </div>
</template>

<script>
import {changeListToTreeOne, changeListToTreeTwo} from '@/common/utils/tree.js'
import {recursionTree} from '@/common/utils/recursion-permission.js'
export default {
  name: "permission_create",
  data () {
    return {
      //上传到服务器的对象
      dataForm: {
        permissionId: '',
        permissionUrl: '',
        permissionName: '',
        permissionDescription: '',
        permissionFid: ''
      },
      dataRule: {
        permissionName: [{ required: true, message: '权限后台名称不能为空', trigger: 'blur' }],
        permissionDescription: [{ required: true, message: '权限描述不能为空', trigger: 'blur' }],
      }
    }
  },
  created(){
    this.dataForm = this.$route.query;
  },
  methods: {
    /*表单校验和保存*/
    validAndSave(dataForm) {
      this.$refs[dataForm].validate((resolve) => {
        if (resolve) {
          this.save();
        }else{
          this.$message.error("请按规范填写");
        }
      })
    },
    //保存标签
    async save(){
      let params = this.dataForm;
      console.log("增加")
      let response = await this.$api.permission.insertOrUpdate(params);
      if(response && response.data.code === 200){
        this.$message.success(response.data.msg);
        this.$router.push('/sys/permission/list');
      }else{
        this.$message.error(response.data.msg);
      }
    },
    reset(){
      this.dataForm.permissionUrl = '';
      this.dataForm.permissionName = '';
      this.dataForm.permissionDescription = '';
      this.dataForm.permissionFid= ''
    },
    async printTreePermission(){
      let lists = await this.$api.permission.lists();
      console.log("未处理--------------------")
      console.log(lists.data.lists);
      console.log("处理1啦--------------------")
      console.log(changeListToTreeOne(lists.data.lists, 'permissionId', 'permissionFid'));
      console.log("处理2啦--------------------")
      let treeList = changeListToTreeTwo(lists.data.lists, 'permissionId', 'permissionFid')
      console.log(treeList);
      console.log("过滤了")
      console.log(recursionTree(treeList,'permissionId', 'permissionDescription', 'permissionName'));
    }
  }
}
</script>

<style scoped>

</style>
