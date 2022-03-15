<template>
  <div class="layout">
<!-- 侧边栏 -->
    <aside>
      <div class="ko" style="height: 60px"></div>
      <div class="nav">
        <el-menu default-active="this.$route.path"
                 router=""
                 class="el-menu-vertical"
                 @open="handleOpen"
                 @close="handleClose"
                 :collapse="isSidebarNavCollapse"
                 background-color="#EBF1F6"
                 text-color="black"
                 active-text-color="#4dbcff">
          <DynamicMenu :menuList="sidebarMenu"></DynamicMenu>
        </el-menu>
      </div>
    </aside>
<!--主要内容-->
    <div class="main">
      <!-- 导航-->
      <header>
        <!-- logo-->
        <div class="logo">
          <h1>宁波萨瑞通讯</h1>
        </div>
        <span class="icon iconfont iconnavicon toggleNavCollapse"></span>
        <div class="breadcrumb">
          <el-breadcrumb  separator="/">
            <transition-group name="breadcrumb">
              <!-- 防止面包屑导航出现 首页/首页， v-if="route.name!='home'" -->
              <template v-for="(route,i) in crumbList">
                <el-breadcrumb-item
                  :key="route.name"
                  :to="{name:route.name}"
                  v-if="route.name!='home' && route.meta.name!='首页'"
                  :class="{'is-last-link':i==crumbList.length-1}"
                >
                  {{route.meta.name}}
                </el-breadcrumb-item>
              </template>
            </transition-group>
          </el-breadcrumb>
        </div>
        <div class="info">
            <div class="user_info">
               欢迎您, {{ nickName }}
            </div>
            <div class="system_logout" @click="systemLogout" >
              注销
            </div>
        </div>
      </header>
      <!-- 主要内容区块-->
      <section>
        <div class="tabs">
            <Content></Content>
        </div>
      </section>
    </div>
  </div>
</template>
<script>
  import DynamicMenu from './component/dynamic-menu'
  import Content from './component/content'
  import { mapState } from 'vuex'
  import {clearLoginInfo} from '@/common/utils'
  export default {
    name: "sidebar-nav",
    data(){
      return {

      };
    },
    computed:{
      ...mapState(['isSidebarNavCollapse', 'crumbList']),  //导入全局属性state中的菜单栏折叠
      ...mapState('permission', ['sidebarMenu','currentMenu']),
      ...mapState('currentUser',['nickName']),
    },
    methods: {
      handleOpen() {

      },
      handleClose() {

      },
      /*登出注销*/
      systemLogout(){
        clearLoginInfo();
        this.$router.push({path: '/login'})
        location.reload();
        sessionStorage.removeItem('tabViews')
        sessionStorage.removeItem('activeIndex')

      }
    },
    components: {
      DynamicMenu,
      Content,
    }
  }
</script>

<style lang="scss" scoped>
 @import 'src/assets/css/layout.scss';
</style>

