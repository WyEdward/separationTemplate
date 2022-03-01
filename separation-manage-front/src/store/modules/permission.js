import user from '@/api/user'
import router, { DynamicRoutes } from '@/router/index'
import { recursionRouter } from '@/common/utils/recursion-router' //调用处理路由的方法
import dynamicRouter from '@/router/dynamic-router'
const state = {
    permissionList: null,      /** 用户能访问的动态路由   后台返回的数据 */
    control_list: [],        /** 完整路由 包含初始路由+动态路由 */
    sidebarMenu: [],           /** 菜单 */
    currentMenu: '',            /** 当前active菜单 */
    isFlesh: 0                     /** 是否刷新  1为刷新 0为未刷新*/
};

// 定义所 需的 mutations
const mutations = {
    SET_PERMISSION(state, routes) {
        state.permissionList = routes
    },
    CLEAR_PERMISSION(state) {
        state.permissionList = null
    },
    SET_MENU(state, menu) {
        state.sidebarMenu = menu
    },
    CLEAR_MENU(state) {
        state.sidebarMenu = []
    },
    SET_CURRENT_MENU(state, currentMenu) {
        state.currentMenu = currentMenu
    },
    SET_CONTROL_LIST(state, list) {
        state.control_list = list
    },
    SET_IS_FLESH(state, val){
        state.isFlesh = val;
    }

};

const actions = {
   /* ,state*/
    async init({ commit}){
        let params = {
            userName: sessionStorage.getItem('userName')
        };
        //console.log("后台权限路由数组:+---------");
        let u = await user.getUser(params);
        let pList = u.permissionList.map((item)=>{
            return item.permissionUrl;
        });
        //console.log(pList);

        /*console.log("总路由+-----------");
        console.log(dynamicRouter);*/

        let routes = recursionRouter(pList, dynamicRouter)//过滤好的路由
       /* console.log("过滤好的路由+-----------");
        console.log(routes);*/
        commit('SET_PERMISSION', routes); //设置过滤好的权限路由

        //console.log("动态路由+-----------");
        let MainContainer = DynamicRoutes.find(item => item.path === '')//获取动态路由 首页路由
        let children = MainContainer.children //获取动态路由首页路由的chridren
        children.push(...routes) //将上面过滤好的后台权限路由添加到动态路由集合
        //console.log(children);

        //console.log("初始路由+------------");
        let initialRoutes = router.options.routes  //获取初始路由
        //console.log(initialRoutes);

        router.addRoutes(DynamicRoutes)  //总路由添加动态路由 包含初始路由和动态路由

        /*console.log("总路由+------------");
        console.log(router);*/

        //console.log("完整路由+----------");
        commit('SET_CONTROL_LIST', [...initialRoutes, ...DynamicRoutes]);
        //console.log(state.control_list);


        //console.log("菜单+-----------");
        commit('SET_MENU', children);
        //console.log(children);
    }
};
export default {
    namespaced:true,//用于在全局引用此文件里的方法时标识这一个的文件名
    state,
    mutations,
    actions
}
