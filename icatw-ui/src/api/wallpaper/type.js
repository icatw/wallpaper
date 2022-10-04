import request from '@/utils/request'

// 查询分类管理列表
export function listType(query) {
  return request({
    url: '/wallpaper/type/list',
    method: 'get',
    params: query
  })
}

// 查询分类管理列表
export function listAllType() {
  return request({
    url: '/wallpaper/type',
    method: 'get'
  })
}

// 查询分类管理详细
export function getType(typeId) {
  return request({
    url: '/wallpaper/type/' + typeId,
    method: 'get'
  })
}

// 新增分类管理
export function addType(data) {
  return request({
    url: '/wallpaper/type',
    method: 'post',
    data: data
  })
}

// 修改分类管理
export function updateType(data) {
  return request({
    url: '/wallpaper/type',
    method: 'put',
    data: data
  })
}

// 壁纸状态修改
export function changeTypeStatus(typeId, isDeleted) {
  const data = {
    typeId,
    isDeleted
  }
  return request({
    url: '/wallpaper/type/changeStatus',
    method: 'put',
    data: data
  })
}

// 删除分类管理
export function delType(typeId) {
  return request({
    url: '/wallpaper/type/' + typeId,
    method: 'delete'
  })
}
