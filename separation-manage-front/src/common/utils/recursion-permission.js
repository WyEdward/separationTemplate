//后端处理好啦传过来树形结构  然后递归处理权限中的细节字段
export function recursionPermission(rolePermission = []) {
    let realPermission =
        rolePermission.map(item => ({
            permissionDescription: item.permissionDescription,   ///...item表达的话会把item默认的属性加上
            children: item.children
                ? recursionPermission(item.children)
                : null
        }));
    return realPermission;
}


/**
 * 处理树结构的字段 使用...args 剩余参数去表示 后面的参数
 * @param treeList  树形数组
 * @param args  过滤的字段
 * @returns {any}
 * wyedward 2022.3.9
 */
export function recursionTree(treeList = [], ...args) {
  let args0 = args[0];
  let args1 = args[1];
  let args2 = args[2];
  let treeListByFilter =
    treeList.map(item => ({
      [args0]: item[args0],  ///...item表达的话会把item默认的属性加上
      [args1]: item[args1],
      [args2]: item[args2],
      children: item.children
        ? recursionTree(item.children, ...args)
        : null
    }));
  return treeListByFilter;
}
