<template>
  <div>
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" label-width="120px" @keyup.enter.native="validAndSave('dataForm')">
      <el-row>
        <el-col :span="12">
          <el-form-item label="用户名" prop="passwordName">
            <el-input v-model="userName" :disabled="true" placeholder="用户名"></el-input>
          </el-form-item>
          <el-form-item label="旧密码" prop="oldPassword">
            <el-input v-model="dataForm.oldPassword" type="password"  placeholder="旧密码"></el-input>
          </el-form-item>
          <el-form-item label="新密码" prop="newPassword">
            <el-input v-model="dataForm.newPassword" type="password" placeholder="新密码"></el-input>
          </el-form-item>
          <el-form-item label="重复新密码" prop="repeatNewPassword">
            <el-input v-model="dataForm.repeatNewPassword" type="password" placeholder="重复新密码"></el-input>
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
import {mapState} from 'vuex'
import {clearLoginInfo} from '@/common/utils'
export default {
  name: "resetPassword",
  data () {
    const validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.dataForm.repeatNewPassword !== "") {
          this.$refs.dataForm.validateField("repeatNewPassword");
        }
        callback();
      }
    };
    const validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.dataForm.newPassword) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      //上传到服务器的对象
      dataForm: {
        userName: '',
        oldPassword: '',
        newPassword: '',
        repeatNewPassword: '',
      },
      dataRule: {
        oldPassword: [{ required: true, message: '新密码内容不能为空', trigger: 'blur' }],
        newPassword: [
          { required: true, message: '旧密码内容不能为空', trigger: 'blur' },
          {
            min: 6,
            max: 16,
            message: "长度在 6 到 16 个字符",
            trigger: "blur",
          },
          { validator: validatePass, trigger: "blur" },
        ],
        repeatNewPassword: [
          { required: true, message: '新密码内容不能为空', trigger: 'blur' },
          {
            min: 6,
            max: 16,
            message: "长度在 6 到 16 个字符",
            trigger: "blur",
          },
          { validator: validatePass2, trigger: "blur" },
        ],
      }
    }
  },
  created(){

  },
  computed:{
    ...mapState('currentUser',['userName', 'userId'])
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
      this.dataForm.userName = this.userName;
      let params = this.dataForm;
      let response = await this.$api.user.resetPassword(params);
      if(response && response.data.code === 200){
        this.$message.success(response.data.msg);
        clearLoginInfo();
        this.$router.push('/login');
        location.reload();
      }else{
        this.$message.error(response.data.msg);
      }
    },
    reset(){
      this.dataForm.newPassword = '';
      this.dataForm.oldPassword = '';
      this.dataForm.repeatNewPassword = '';
    }
  }
}
</script>

<style scoped>

</style>
