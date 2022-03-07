import testList from "../views/test/testList";
import testDelete from "../views/test/testDelete";
import testInsert from "../views/test/testInsert";
import testUpdate from "../views/test/testUpdate";
import testManage from "../views/test"

import hourManage from '../views/hourManage'
import JobManage from '../views/hourManage/jobManage'
import JobCreate from '../views/hourManage/jobManage/job_create'
import JobList from '../views/hourManage/jobManage/job_list'
/* 需要权限判断的路由 */
const dynamicRoutes = [
  {
    id: 2,
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
  },
  {
    id: 3,
    path: '/hour',
    component: hourManage,
    name: '工时系统',
    redirect: '/hour/job',
    meta: {
      name: '工时管理',
      icon: 'tree'
    },
    children: [
      {
        path: '/hour/job',
        component: JobManage,
        redirect: '/hour/job/list',
        name: '项目管理',
        meta: {
          name: '项目管理',
          icon: 'tree'
        },
        children:[
          {
            path: '/hour/job/list',
            component: JobList,
            name: '项目列表',
            meta: {
              name: '项目列表',
              icon: 'tree'
            }
          },
          {
            path: '/hour/job/create',
            component: JobCreate,
            name: '项目创建',
            meta: {
              name: '项目创建',
              icon: 'tree'
            }
          }
        ]
      },
    ]
  }
]
export default dynamicRoutes
