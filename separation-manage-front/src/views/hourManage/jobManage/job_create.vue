<template>
  <div>
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" label-width="120px" @keyup.enter.native="validAndSave('dataForm')">
      <el-row>
        <el-col :span="12">
          <el-form-item label="项目名称" prop="jobName">
            <el-input v-model="dataForm.jobName" placeholder="项目名称"></el-input>
          </el-form-item>
          <el-form-item label="项目描述" prop="jobDescription">
            <el-input v-model="dataForm.jobDescription" placeholder="项目描述"></el-input>
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
  name: "job_create",
  data () {
    return {
      //上传到服务器的对象
      dataForm: {
        jobId: '',
        jobName: '',
        jobDescription: '',
      },
      dataRule: {
        jobName: [{ required: true, message: '项目内容不能为空', trigger: 'blur' }],
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
      let response = await this.$api.job.insertOrUpdate(params);
      if(response && response.data.code === 200){
        this.$message.success(response.data.msg);
        this.$router.push('/hour/job/list');
      }else{
        this.$message.error(response.data.msg);
      }
    },
    reset(){
      this.dataForm.jobName = '';
      this.dataForm.jobDescription = '';
    }
  }
}
</script>

<style scoped>

</style>
