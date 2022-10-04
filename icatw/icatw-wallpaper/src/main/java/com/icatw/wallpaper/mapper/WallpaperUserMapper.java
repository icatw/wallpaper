package com.icatw.wallpaper.mapper;

import java.util.List;
import com.icatw.wallpaper.domain.WallpaperUser;

/**
 * 用户管理Mapper接口
 * 
 * @author icatw
 * @date 2022-10-04
 */
public interface WallpaperUserMapper 
{
    /**
     * 查询用户管理
     * 
     * @param userId 用户管理主键
     * @return 用户管理
     */
    public WallpaperUser selectWallpaperUserByUserId(Long userId);

    /**
     * 查询用户管理列表
     * 
     * @param wallpaperUser 用户管理
     * @return 用户管理集合
     */
    public List<WallpaperUser> selectWallpaperUserList(WallpaperUser wallpaperUser);

    /**
     * 新增用户管理
     * 
     * @param wallpaperUser 用户管理
     * @return 结果
     */
    public int insertWallpaperUser(WallpaperUser wallpaperUser);

    /**
     * 修改用户管理
     * 
     * @param wallpaperUser 用户管理
     * @return 结果
     */
    public int updateWallpaperUser(WallpaperUser wallpaperUser);

    /**
     * 删除用户管理
     * 
     * @param userId 用户管理主键
     * @return 结果
     */
    public int deleteWallpaperUserByUserId(Long userId);

    /**
     * 批量删除用户管理
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWallpaperUserByUserIds(Long[] userIds);
}
