<template>
  <div>
    <el-button>默认按钮</el-button>
    <el-button type="primary">主要按钮</el-button>
    <el-button type="success">成功按钮</el-button>
    <el-button type="info">信息按钮</el-button>
    <el-button type="warning">警告按钮</el-button>
    <el-button type="danger">危险按钮</el-button>
    <input @click="listUsers" type="button" value="查询">
  </div>

</template>

<script>
export default {
  name: "test",
  data(){
    return {
      userList: [],
    }
  },
  methods:{
    async listUsers(){
      this.userList = await this.$api.test.listUsers();
      console.log(this.userList);
    },
  }
}
</script>

<style scoped>

</style>
