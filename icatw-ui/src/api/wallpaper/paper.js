import request from '@/utils/request'

// 查询壁纸列表列表
export function listPaper(query) {
  return request({
    url: '/wallpaper/paper/list',
    method: 'get',
    params: query
  })
}

// 查询壁纸列表详细
export function getPaper(paperId) {
  return request({
    url: '/wallpaper/paper/' + paperId,
    method: 'get'
  })
}

// 新增壁纸列表
export function addPaper(data) {
  return request({
    url: '/wallpaper/paper',
    method: 'post',
    data: data
  })
}

// 修改壁纸列表
export function updatePaper(data) {
  return request({
    url: '/wallpaper/paper',
    method: 'put',
    data: data
  })
}

// 删除壁纸列表
export function delPaper(paperId) {
  return request({
    url: '/wallpaper/paper/' + paperId,
    method: 'delete'
  })
}
