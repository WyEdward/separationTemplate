<template>
  <div>
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" label-width="120px" @keyup.enter.native="validAndSave('dataForm')">
      <el-row>
        <el-col :span="12">
          <el-form-item label="角色名称" prop="roleName">
            <el-input v-model="dataForm.roleName" placeholder="角色名称"></el-input>
          </el-form-item>
          <el-form-item label="角色描述" prop="roleDescription">
            <el-input v-model="dataForm.roleDescription" placeholder="角色描述"></el-input>
          </el-form-item>
          <el-form-item label="角色选择权限" prop="treeList">
            <el-tree
              :data="treeMenu"
              show-checkbox
              node-key="permissionId"
              ref="tree"
              highlight-current
              :default-checked-keys="defaultCheckedKey"
              :props="treeMenuProps"
            >
            </el-tree>
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item>
        <el-button type="primary" @click="validAndSave('dataForm')">保存</el-button>
        <el-button @click="reset()">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {changeListToTreeOne, changeListToTreeTwo} from '@/common/utils/tree.js'
import {recursionTree} from '@/common/utils/recursion-permission.js'
export default {
  name: "role_create",
  data () {
    return {
      //上传到服务器的对象
      dataForm: {
        roleId: '',
        roleName: '',
        roleDescription: '',
        treeList: [],
      },
      defaultCheckedKey: [],  //默认选中的权限
      treeMenu:[],    //树形结构菜单 所有权限
      treeMenuProps: {
        children: 'children',
        label: 'permissionDescription'
      },
      dataRule: {
        roleName: [{ required: true, message: '角色内容不能为空', trigger: 'blur' }],
      }
    }
  },
  created(){
    this.dataForm = this.$route.query;
    if(this.dataForm.roleId){
      //console.log("开始加载默认")
      this.listPermissionByRoleId(this.dataForm.roleId);
    }
    this.listTree();
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
      this.getCheckedKeys();  //获取选中的权限
      let params = this.dataForm;
      let response = await this.$api.role.insertOrUpdate(params);
      if(response && response.data.code === 200){
        this.$message.success(response.data.msg);
        this.$router.push('/sys/role/list');
      }else{
        this.$message.error(response.data.msg);
      }
    },
    async listTree(){
      let lists = await this.$api.permission.lists();
      if(lists.data.lists != null){
        //将list转为tree
        let treeList = changeListToTreeTwo(lists.data.lists, 'permissionId', 'permissionFid');
        //将tree的字段进行过滤
        this.treeMenu = recursionTree(treeList,'permissionId', 'permissionDescription', 'permissionName');
        // console.log(recursionTree(treeList,'permissionId', 'permissionDescription', 'permissionName'))
      }
    },
    //获取选中的权限 id值
    getCheckedKeys(){
      this.dataForm.treeList = [];
      for(let j = 0; j < this.$refs.tree.getCheckedKeys().length; j++){
        this.dataForm.treeList.push(this.$refs.tree.getCheckedKeys()[j]);  //全选中的结点
      }
      for(let j = 0; j < this.$refs.tree.getHalfCheckedKeys().length; j++){
        this.dataForm.treeList.push(this.$refs.tree.getHalfCheckedKeys()[j]);  //半选中的结点
      }
    },
    //获取用户的权限列表数组
    async listPermissionByRoleId(roleId){
      let params ={
        roleId: roleId
      };
      let lists = await this.$api.role.listPermissionByRoleId(params);
      this.defaultCheckedKey = lists.data.lists;
      console.log(lists);
    },
    reset(){
      this.dataForm.roleName = '';
      this.dataForm.roleDescription = '';
    }
  }
}
</script>

<style scoped>

</style>
