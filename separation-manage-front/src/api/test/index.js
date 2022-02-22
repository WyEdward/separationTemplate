import api from '../index'
import urls from './urls'

const header = {}

function beforeSend(header) {
  return header
}

export default {
  listUsers() {
    return api.post(urls.listUsers, {}, beforeSend(header))
  }
}
