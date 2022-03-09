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
          <el-form-item label="权限所在父菜单(不选则新建菜单)" prop="permissionFid">
<!--            <el-input v-model="dataForm.permissionFid" placeholder="权限所在父id"></el-input>-->
            <el-tree
              :data="treeMenu"
              show-checkbox
              check-strictly
              node-key="permissionId"
              ref="tree"
              highlight-current
              :default-checked-keys="defaultCheckedKey"
              :props="treeMenuProps"
              @check-change="handleTree"
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
  name: "permission_create",
  data () {
    return {
      i:0,    //树形菜单单选需要
      defaultCheckedKey: [],
      treeMenu:[],    //树形结构菜单
      treeMenuProps: {
        children: 'children',
        label: 'permissionDescription'
      },
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
    if(this.dataForm.permissionFid){
      //console.log(this.dataForm.permissionFid);
      this.defaultCheckedKey.push(this.dataForm.permissionFid);
      this.i++;
      //console.log(this.defaultCheckedKey);
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
    //保存
    async save(){
      //选择啦多个 严谨起见 多加了一层判断 其实不用加这层判断 因为已经设置啦单选
      if(this.$refs.tree.getCheckedNodes().length > 1){
        this.$message.error('归属菜单只能选一个或不选 不选重新建立一个菜单分支');
        return;
      }else if(this.$refs.tree.getCheckedNodes().length === 0){   //当不选的时候 重新建立新菜单
        this.dataForm.permissionFid = 0;
      }else{      //将将要生成的菜单fid 变成选择菜单的id
        this.dataForm.permissionFid = this.$refs.tree.getCheckedNodes()[0].permissionId;
      }
      let params = this.dataForm;
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
    async listTree(){
      let lists = await this.$api.permission.lists();
      if(lists.data.lists != null){
        //将list转为tree
        let treeList = changeListToTreeTwo(lists.data.lists, 'permissionId', 'permissionFid');
        //将tree的字段进行过滤
        this.treeMenu = recursionTree(treeList,'permissionId', 'permissionDescription', 'permissionName');
        console.log(recursionTree(treeList,'permissionId', 'permissionDescription', 'permissionName'))
      }
    },
    //点击树形控件
    handleTree(data, checked) {
      this.i++;
      if(this.i % 2 === 0){
        if(checked){
          this.$refs.tree.setCheckedNodes([data]);
          //交叉点击节点
        }else{
          this.$refs.tree.setCheckedNodes([]);
          //点击已经选中的节点，置空
        }
      }
    },
  }
}
</script>

<style scoped>

</style>
