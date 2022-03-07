import globalVariable from '@/common/global_variable.js';
//表示这个类别的api
const baseUrl = globalVariable.baseUrl

export default {
  //登录
  login: baseUrl + '/login',
  //获取用户信息
  getUser: baseUrl + '/getUser',
}
