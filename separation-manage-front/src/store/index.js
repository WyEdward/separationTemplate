import Vue from 'vue'
import Vuex from 'vuex'

import state from './state'
import getters from './getters'
import modules from './modules'
import actions from './actions'
import mutations from './mutations'

Vue.use(Vuex);

export default new Vuex.Store({
  state,          //全局的属性
  getters,        //全局属性的获取方法
  mutations,      //全局属性的方法
  actions,        //全局属性的方法
  modules          //模块属性、方法
})
