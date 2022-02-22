<template>
    <input @click="listUsers" type="button" value="查询">
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
