import globalVariable from '@/common/global_variable.js';
//表示这个类别的api
const baseUrl = globalVariable.baseUrl + '/permission/'

export default {
  //更新或插入项目
  insertOrUpdate: baseUrl + 'insertOrUpdate',
  listByPage: baseUrl + 'listByPage',
  listDtoByPage: baseUrl + 'listDtoByPage',
  remove: baseUrl + 'remove',
  removes: baseUrl + 'removes',
  queryGroupByLike: baseUrl + 'queryGroupByLike',
  lists: baseUrl + 'lists'
}
