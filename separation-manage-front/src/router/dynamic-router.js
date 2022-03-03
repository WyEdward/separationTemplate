import testList from "../views/test/testList";
import testDelete from "../views/test/testDelete";
import testInsert from "../views/test/testInsert";
import testUpdate from "../views/test/testUpdate";
import testManage from "../views/test"
/* 需要权限判断的路由 */
const dynamicRoutes = [
  {
    id: 1,
    path: '/test',
    component: testManage,
    name: '测试管理',
    redirect: '/test/list',
    meta: {
      name: '测试管理',
      icon: 'tree'
    },
    children: [
      {
        path: '/test/list',
        component: testList,
        name: '测试列表',
        meta: {
          name: '测试列表',
          icon: 'tree'
        },
      },
      {
        path: '/test/insert',
        component: testInsert,
        name: '测试插入',
        meta: {
          name: '测试插入',
          icon: 'tree'
        },
      },
      {
        path: '/test/delete',
        component: testDelete,
        name: '测试删除',
        meta: {
          name: '测试删除',
          icon: 'tree'
        },
      },
      {
        path: '/test/update',
        component: testUpdate,
        name: '测试更新',
        meta: {
          name: '测试更新',
          icon: 'tree'
        },
      },
    ]
  }
]
export default dynamicRoutes
