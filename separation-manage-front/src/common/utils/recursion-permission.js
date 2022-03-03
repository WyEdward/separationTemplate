//递归处理权限
export function recursionPermission(rolePermission = []) {
    let realPermission =
        rolePermission.map(item => ({
            permissionDescription: item.permissionDescription,
            children: item.children
                ? recursionPermission(item.children)
                : null
        }));
    return realPermission;
}
