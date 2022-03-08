import testList from "../views/test/testList";
import testDelete from "../views/test/testDelete";
import testInsert from "../views/test/testInsert";
import testUpdate from "../views/test/testUpdate";
import testManage from "../views/test"

import hourManage from '../views/hourManage'
import JobManage from '../views/hourManage/jobManage'
import JobCreate from '../views/hourManage/jobManage/job_create'
import JobList from '../views/hourManage/jobManage/job_list'

import DepartmentManage from '../views/hourManage/departmentManage'
import DepartmentCreate from '../views/hourManage/departmentManage/department_create'
import DepartmentList from '../views/hourManage/departmentManage/department_list'

import SysManage from '../views/sysManage'
import UserManage from '../views/sysManage/userManage'
import UserList from '../views/sysManage/userManage/user_list'
import UserCreate from '../views/sysManage/userManage/user_create'

import PermissionManage from '../views/sysManage/permissionManage'
import PermissionList from '../views/sysManage/permissionManage/permission_list'
import PermissionCreate from '../views/sysManage/permissionManage/permission_create'

import RoleManage from '../views/sysManage/roleManage'
import RoleList from '../views/sysManage/roleManage/role_list'
import RoleCreate from '../views/sysManage/roleManage/role_create'
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
      {
        path: '/hour/department',
        component: DepartmentManage,
        redirect: '/hour/department/list',
        name: '部门管理',
        meta: {
          name: '部门管理',
          icon: 'tree'
        },
        children:[
          {
            path: '/hour/department/list',
            component: DepartmentList,
            name: '部门列表',
            meta: {
              name: '部门列表',
              icon: 'tree'
            }
          },
          {
            path: '/hour/department/create',
            component: DepartmentCreate,
            name: '部门创建',
            meta: {
              name: '部门创建',
              icon: 'tree'
            }
          }
        ]
      },
    ]
  },
  {
    id: 4,
    path: '/sys',
    component: SysManage,
    redirect: '/sys/user',
    name: '系统管理',
    meta: {
      name: '系统管理',
      icon: 'tree'
    },
    children: [
      {
        path: '/sys/user',
        component: UserManage,
        redirect: '/sys/user/list',
        name: '用户管理',
        meta: {
          name: '用户管理',
          icon: 'tree'
        },
        children: [
          {
            path: '/sys/user/list',
            component: UserList,
            name: '用户列表',
            meta: {
              name: '用户列表',
              icon: 'tree'
            },
            children:[]
          },
          {
            path: '/sys/user/create',
            component: UserCreate,
            name: '用户创建',
            meta: {
              name: '用户创建',
              icon: 'tree'
            },
            children:[]
          }
        ]
      },
      {
        path: '/sys/role',
        component: RoleManage,
        redirect: '/sys/role/list',
        name: '角色管理',
        meta: {
          name: '角色管理',
          icon: 'tree'
        },
        children: [
          {
            path: '/sys/role/list',
            component: RoleList,
            name: '角色列表',
            meta: {
              name: '角色列表',
              icon: 'tree'
            },
            children:[]
          },
          {
            path: '/sys/role/create',
            component: RoleCreate,
            name: '角色创建',
            meta: {
              name: '角色创建',
              icon: 'tree'
            },
            children:[]
          }
        ]
      },
      {
        path: '/sys/permission',
        component: PermissionManage,
        redirect: '/sys/permission/list',
        name: '权限管理',
        meta: {
          name: '权限管理',
          icon: 'tree'
        },
        children: [
          {
            path: '/sys/permission/list',
            component: PermissionList,
            name: '权限列表',
            meta: {
              name: '权限列表',
              icon: 'tree'
            },
            children:[]
          },
          {
            path: '/sys/permission/create',
            component: PermissionCreate,
            name: '权限创建',
            meta: {
              name: '权限创建',
              icon: 'tree'
            },
            children:[]
          }
        ]
      }
    ]
  }
]
export default dynamicRoutes
