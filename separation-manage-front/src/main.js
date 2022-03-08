// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'      //路由
import api from './api/install'    //axios申请接口
import store from './store'  //VueX统一状态管理e
import ElementUI from 'element-ui';  //ElementUI
import 'element-ui/lib/theme-chalk/index.css';
import './assets/icons/iconfont.css'
import { isAuth } from './common/utils'
import globalVariable from '@/common/global_variable.js'

Vue.config.productionTip = false
Vue.prototype.isAuth = isAuth // 权限方法
Vue.prototype.GLOBAL= globalVariable;       //全局变量
Vue.use(ElementUI);             //使用ElementUI
Vue.use(api);                //使用axios


Vue.filter('dateformat', function(dataStr) {
  let a = new Date(dataStr).getTime();
  const date = new Date(a);
  if(date.getFullYear() === 1970){
    return "暂未更新";
  }
  const Y = date.getFullYear() + '-';
  const M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
  const D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate()) + '  ';
  const h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':';
  const m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':';
  const s =(date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()); // 秒
  const dateString = Y + M + D + h + m + s;
  return dateString;
});

router.beforeEach((to, from, next) => {
  if (!store.state.currentUser.token) {
    if ((to.matched.length > 0 && !to.matched.some(record => record.meta.requiresAuth) || to.path === '/login')) {
      next()
    } else {
      next({ path: '/login' })
    }
  } else {
    if(!store.state.permission.isFlesh){
      store.dispatch('permission/init').then(()=>{});
      store.commit('permission/SET_IS_FLESH', 1);
    }
    next();
  }
})

router.afterEach((to) => {
  var routerList = to.matched
  store.commit('setCrumbList', routerList)
  store.commit('permission/SET_CURRENT_MENU', to.path)
})


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,store,                 //使用route和VueX
  components: { App },
  template: '<App/>'
})
