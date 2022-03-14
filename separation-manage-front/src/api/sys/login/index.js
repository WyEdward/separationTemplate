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
  /** 登录*/
  login(params){
    return api.post(urls.login, params, beforeSend(header))
  },
  /** 获取用户信息*/
  getUser(params){
    return api.post(urls.getUser, Qs.stringify(params), beforeSend(headerParam));
  },

}
