import globalVariable from '@/common/global_variable.js';
//表示这个类别的api
const baseUrl = globalVariable.baseUrl
export default{
  //列举用户
  listUsers: baseUrl + '/userInfo/test2',
}
