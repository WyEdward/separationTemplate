// 应用初始状态
const state = {
    nickName: sessionStorage.getItem('nickName')  ? sessionStorage.getItem('nickName') : '',
    userId: sessionStorage.getItem('userId')  ? sessionStorage.getItem('userName') : '',
    userName: sessionStorage.getItem('userName') ? sessionStorage.getItem('userName') : '',
    token: sessionStorage.getItem('token') ? sessionStorage.getItem('token') : '',
};

// 定义所需的 mutations
const mutations = {
    //设置token
    setToken(state, val){
        state.token= val;
        sessionStorage.setItem('token', val);
    },
    //获取token
    getToken(state){
        return state.token
    },

    //设置用户名
    setUserName(state, userName){
        sessionStorage.setItem('userName', userName);
        state.userName = userName;
    },
    //设置用户id
    setUserId(state, userId){
        sessionStorage.setItem('userId', userId);
        state.userId = userId;
    },
    //设置用户id
    setNickName(state, nickName){
        sessionStorage.setItem('nickName', nickName);
        state.nickName = nickName;
    }
}

const actions = {
  //重置default
  set_default(){
    sessionStorage.removeItem('nickName');
    sessionStorage.removeItem('permissionList');
    sessionStorage.removeItem('userName');
    sessionStorage.removeItem('userId');
    sessionStorage.removeItem('token')
  }
}
export default {
    namespaced: true,//用于在全局引用此文件里的方法时标识这一个的文件名
    state,
    mutations,
    actions
}
