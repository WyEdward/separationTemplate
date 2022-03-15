import Vue from 'vue'
import Router from 'vue-router'


import Index from '@/views/layout'
import Home from '@/views/common/home'
import Login from '@/views/common/login'

import Error from '@/views/common/error'
import NotFound from '@/views/common/error/404'

Vue.use(Router)

//以下代码解决路由地址重复点击的报错问题
const VueRouterPush = Router.prototype.push
Router.prototype.push = function push (to) {
  return VueRouterPush.call(this, to).catch(err => err)
}


export default new Router({
  mode: 'hash',
  base: '/admin/',
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path:'/error',
      name:'Error',
      component: Error,
      redirect: '/error/404',
      children:[
        {
          path:'/error/404',
          name:'NotFound',
          component: NotFound
        },
      ]
    }
  ]
})

/* 准备动态添加的路由 */
export const DynamicRoutes = [
  {
    path:'',
    name: 'Index',
    component: Index,
    redirect: '/home',
    meta: {
      requiresAuth: true,
      name: '首页'
    },
    children:[
      {
        id: 1,
        path: '/home',
        component: Home ,
        name: '首页',
        meta: {
          name: '首页',
          icon: 'tree'
        }
      }
    ]
  }
]
