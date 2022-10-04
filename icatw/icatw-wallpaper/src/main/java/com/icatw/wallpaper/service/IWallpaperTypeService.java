package com.icatw.wallpaper.service;

import com.icatw.wallpaper.domain.WallpaperType;

import java.util.List;

/**
 * 分类管理Service接口
 *
 * @author icatw
 * @date 2022-10-04
 */
public interface IWallpaperTypeService
{
    /**
     * 查询分类管理
     *
     * @param typeId 分类管理主键
     * @return 分类管理
     */
    public WallpaperType selectWallpaperTypeByTypeId(Long typeId);

    /**
     * 查询分类管理列表
     *
     * @param wallpaperType 分类管理
     * @return 分类管理集合
     */
    public List<WallpaperType> selectWallpaperTypeList(WallpaperType wallpaperType);

    /**
     * 新增分类管理
     *
     * @param wallpaperType 分类管理
     * @return 结果
     */
    public int insertWallpaperType(WallpaperType wallpaperType);

    /**
     * 修改分类管理
     *
     * @param wallpaperType 分类管理
     * @return 结果
     */
    public int updateWallpaperType(WallpaperType wallpaperType);

    /**
     * 批量删除分类管理
     *
     * @param typeIds 需要删除的分类管理主键集合
     * @return 结果
     */
    public int deleteWallpaperTypeByTypeIds(Long[] typeIds);

    /**
     * 删除分类管理信息
     *
     * @param typeId 分类管理主键
     * @return 结果
     */
    public int deleteWallpaperTypeByTypeId(Long typeId);
}
