package com.icatw.wallpaper.service.impl;

import com.icatw.common.utils.DateUtils;
import com.icatw.wallpaper.domain.WallpaperType;
import com.icatw.wallpaper.mapper.WallpaperTypeMapper;
import com.icatw.wallpaper.service.IWallpaperTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分类管理Service业务层处理
 *
 * @author icatw
 * @date 2022-10-04
 */
@Service
public class WallpaperTypeServiceImpl implements IWallpaperTypeService
{
    @Autowired
    private WallpaperTypeMapper wallpaperTypeMapper;

    /**
     * 查询分类管理
     *
     * @param typeId 分类管理主键
     * @return 分类管理
     */
    @Override
    public WallpaperType selectWallpaperTypeByTypeId(Long typeId)
    {
        return wallpaperTypeMapper.selectWallpaperTypeByTypeId(typeId);
    }

    /**
     * 查询分类管理列表
     *
     * @param wallpaperType 分类管理
     * @return 分类管理
     */
    @Override
    public List<WallpaperType> selectWallpaperTypeList(WallpaperType wallpaperType)
    {
        return wallpaperTypeMapper.selectWallpaperTypeList(wallpaperType);
    }

    /**
     * 新增分类管理
     *
     * @param wallpaperType 分类管理
     * @return 结果
     */
    @Override
    public int insertWallpaperType(WallpaperType wallpaperType)
    {
        wallpaperType.setCreateTime(DateUtils.getNowDate());
        return wallpaperTypeMapper.insertWallpaperType(wallpaperType);
    }

    /**
     * 修改分类管理
     *
     * @param wallpaperType 分类管理
     * @return 结果
     */
    @Override
    public int updateWallpaperType(WallpaperType wallpaperType)
    {
        wallpaperType.setUpdateTime(DateUtils.getNowDate());
        return wallpaperTypeMapper.updateWallpaperType(wallpaperType);
    }

    /**
     * 批量删除分类管理
     *
     * @param typeIds 需要删除的分类管理主键
     * @return 结果
     */
    @Override
    public int deleteWallpaperTypeByTypeIds(Long[] typeIds)
    {
        return wallpaperTypeMapper.deleteWallpaperTypeByTypeIds(typeIds);
    }

    /**
     * 删除分类管理信息
     *
     * @param typeId 分类管理主键
     * @return 结果
     */
    @Override
    public int deleteWallpaperTypeByTypeId(Long typeId)
    {
        return wallpaperTypeMapper.deleteWallpaperTypeByTypeId(typeId);
    }
}
