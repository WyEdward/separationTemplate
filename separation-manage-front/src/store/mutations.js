export default{
  /*反转导航栏是否折叠*/
  toggleNavCollapse(state){
    state.isSidebarNavCollapse = !state.isSidebarNavCollapse
  },
  /*设置面包屑导航栏*/
  setCrumbList(state, list){
    state.crumbList = list
  }
}
