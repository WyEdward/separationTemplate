import Vue from 'vue'
import router from '@/router'
import store from '@/store'
/**
 * 获取uuid
 * @returns {string}
 */
export function getUUID () {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, c => {
        return (c === 'x' ? (Math.random() * 16 | 0) : ('r&0x3' | '0x8')).toString(16)
    })
}


/**
 * @Description: 清除登录信息
 * @author Bobbi
 * @date 18-9-28
 */
export function clearLoginInfo () {
  store.state.isSidebarNavCollapse = false;
  store.state.crumbList = [];
  store.dispatch('currentUser/set_default')
  store.dispatch('permission/set_default')
  store.dispatch('menu/set_default')
}

export function isAuth (key) {
    return sessionStorage.getItem('permissionList').indexOf(key) !== -1 || false
}
