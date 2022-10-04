package com.icatw.wallpaper.mapper;

import java.util.List;
import com.icatw.wallpaper.domain.WallpaperType;

/**
 * 分类管理Mapper接口
 * 
 * @author icatw
 * @date 2022-10-04
 */
public interface WallpaperTypeMapper 
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
     * 删除分类管理
     * 
     * @param typeId 分类管理主键
     * @return 结果
     */
    public int deleteWallpaperTypeByTypeId(Long typeId);

    /**
     * 批量删除分类管理
     * 
     * @param typeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWallpaperTypeByTypeIds(Long[] typeIds);
}
