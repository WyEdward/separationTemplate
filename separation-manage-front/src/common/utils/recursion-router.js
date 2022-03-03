/**
 *
 * @param  {Array} userRouter 后台返回的用户权限json  数组里面包含权限url
 * @param  {Array} allRouter  前端配置好的所有动态路由的集合   类似于router.index.js格式
 * @return {Array} realRoutes 过滤后的路由  类似于router.index.js格式
 */
export function recursionRouter(userRouter = [], allRouter = []) {
    let realRoutes = allRouter
        .filter(item => userRouter.includes(item.path))
        .map(item => ({
            ...item,
            children: item.children
                ? recursionRouter(userRouter, item.children)
                : null
        }))
    return realRoutes
}

/**
 *
 * @param {Array} routes 用户过滤后的路由
 *
 * 递归为所有有子路由的路由设置第一个children.path为默认路由
 */
export function setDefaultRoute(routes) {
    routes.forEach((v) => {
        if (v.children && v.children.length > 0) {
            v.redirect = { name: v.children[0].path }
            setDefaultRoute(v.children)
        }
    })
}