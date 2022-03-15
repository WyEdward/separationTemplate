<template>
    <div>
      {{nickName}},欢迎您登录实验室工时统计系统
      <el-button type="text" @click="resetPassword">修改密码</el-button>
      <el-button type="text" @click="personView">查看个人信息</el-button>
      <el-button type="text" @click="personUpdate">修改个人信息</el-button>
    </div>
</template>

<script>
import {mapState} from "vuex";
  export default {
    name: "home",
    data(){
      return {}
    },
    computed:{
      ...mapState('currentUser',['nickName']),
    },
    methods: {
      resetPassword(){
         this.$router.push({path: '/sys/password/reset'})
      },
      personView(){
        this.$router.push({path: '/sys/person/view'})
      },
      personUpdate(){
        this.$router.push({path: '/sys/person/update'})
      }
    }
  }
</script>

<style scoped>

</style>
