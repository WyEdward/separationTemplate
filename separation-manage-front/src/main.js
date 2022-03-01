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
Vue.config.productionTip = false

Vue.use(ElementUI);             //使用ElementUI
Vue.use(api);                //使用axios
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,store,                 //使用route和VueX
  components: { App },
  template: '<App/>'
})
