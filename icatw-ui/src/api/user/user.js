import request from '@/utils/request'

// 预览生成代码
export function getCurrUserInfo() {
  return request({
    url: '/user/current/getUserInfo',
    method: 'get'
  })
}
