import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)


import Index from '@/views/layout'


export default new Router({
  routes: [
    {
      path: '/index',
      name: 'Index',
      component: Index,
      meta: {
        requiresAuth: true,
        name: '主页'
      },
      children: []
    },

  ]
})
