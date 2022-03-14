<template>
  <div>
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" label-width="120px" @keyup.enter.native="validAndSave('dataForm')">
      <el-row>
        <el-col :span="12">
          <el-form-item label="用户名称" prop="userName">
            <el-input v-model="dataForm.userName" placeholder="用户名"></el-input>
          </el-form-item>
          <el-form-item label="用户昵称" prop="userNick">
            <el-input v-model="dataForm.userNick" placeholder="用户昵称"></el-input>
          </el-form-item>
          <el-form-item label="用户角色" prop="userRoles">
            <el-select v-model="roleDefaultSelections" multiple placeholder="请选择角色">
              <el-option
                v-for="item in roleList"
                :key="item.roleId"
                :label="item.roleDescription"
                :value="item.roleId+''">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="用户邮箱" prop="userEmail">
            <el-input v-model="dataForm.userEmail" placeholder="用户邮箱"></el-input>
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
export default {
  name: "user_create",
  data () {
    return {
      //上传到服务器的对象
      dataForm: {
        userId: '',
        userName: '',
        userNick: '',
        userEmail: '',
        userRoles: []
      },
      roleList:[],            //角色集
      roleDefaultSelections:[],     //默认选中的角色


      dataRule: {
        userName: [{ required: true, message: '用户名不能为空', trigger: 'blur' }],
        userNick: [{ required: true, message: '用户昵称不能为空', trigger: 'blur' }],
        userRoles: [{  required: true, message: '角色不能为空', trigger: 'blur' }],
      }
    }
  },
  created(){
    this.dataForm = this.$route.query;
    this.loadRoles();
    this.loadDepartments();
    if(this.$route.query.userId != null){
      this.roleDefaultSelections = ['1']  //this.dataForm.userRoles;
      this.departmentDefaultSelections = this.dataForm.userDepartment; //修改为number 因为后台id数据是number
     //this.departmentDefaultSelections = this.departmentDefaultSelections.toString();
    }
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
      this.dataForm.userRoles = this.roleDefaultSelections;
      let params = this.dataForm;
      let response = await this.$api.user.insertOrUpdate(params);
      if(response && response.data.code === 200){
        this.$message.success(response.data.msg);
        this.$router.push('/sys/user/list');
      }else{
        this.$message.error(response.data.msg);
      }
    },
    reset(){
      this.dataForm.userName = '';
      this.dataForm.userDescription = '';
    },
    //角色多选项数据加载
    async loadRoles(){
      let res = await this.$api.role.lists();
      this.roleList = res.data.lists;
    },
  }
}
</script>

<style scoped>

</style>
