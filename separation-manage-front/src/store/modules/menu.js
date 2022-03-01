// 应用初始状态
const state = {
    options: [             //表示主内容的导航栏 小页面
        {
            name: '首页',
            route:'/home'
        }
    ],
    activeIndex: '/home'   //当前打开的页面路由
};

// 定义所需的 mutations
const mutations = {
    // 添加tabs
    add_tabs (state, data) {
        state.options.push(data);
    },
    // 删除tabs
    delete_tabs (state, route) {
        let index = 0;
        for (let option of state.options) {
            if (option.route === route) {
                break;
            }
            index++;
        }
        state.options.splice(index, 1);
    },
    // 设置当前激活的tab
    set_active_index (state, index) {
        state.activeIndex = index;
    }
}

export default {
    namespaced:true,//用于在全局引用此文件里的方法时标识这一个的文件名
    state,
    mutations,
}
