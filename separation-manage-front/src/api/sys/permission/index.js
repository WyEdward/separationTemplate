//表示这个类别的api
import api from '../../index'
import urls from './urls'

// import { formatUrl } from '../../qiniu/utils'
// 这个东西是unsplash要求加在header里的验证，
const header = {

};
const headerParam={
  'Content-Type': 'application/x-www-form-urlencoded'
};
/**在发送前验证*/
function beforeSend(header){
  if(sessionStorage.getItem("token")){
    header.Authorization = sessionStorage.getItem("token")
  }
  return header;
}

import Qs from 'qs'
export default {
  insertOrUpdate(params){
    return api.post(urls.insertOrUpdate, params, beforeSend(header))
  },
  listByPage(params){
    return api.post(urls.listByPage, params, beforeSend(header))
  },
  listDtoByPage(params){
    return api.post(urls.listDtoByPage, params, beforeSend(header))
  },
  remove(params){
    return api.post(urls.remove, Qs.stringify(params), beforeSend(headerParam))
  },
  removes(params){
    return api.post(urls.removes, params, beforeSend(header))
  },
  queryGroupByLike(params) {
    return api.post(urls.queryGroupByLike, Qs.stringify(params), beforeSend(headerParam))
  },
  lists(params){
    return api.post(urls.lists, Qs.stringify(params), beforeSend(headerParam))
  }
}
