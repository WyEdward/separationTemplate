// install.js将apiList中的模块的api导入到全局 然后再main.js引用一下就行
// 导入模块
import apiList from './apiList'

const install = function (Vue) {
    if (install.installed) return
    install.installed = true
    Object.defineProperties(Vue.prototype, {
        $api: {
            get () {
                return apiList
            },
            enumerable: false, // 不可枚举
            configurable: false // 不可重写
        }
    })
}

export default {
    install
}
