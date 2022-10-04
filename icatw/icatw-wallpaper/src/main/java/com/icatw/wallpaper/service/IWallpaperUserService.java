package com.icatw.wallpaper.service;

import java.util.List;
import com.icatw.wallpaper.domain.WallpaperUser;

/**
 * 用户管理Service接口
 * 
 * @author icatw
 * @date 2022-10-04
 */
public interface IWallpaperUserService 
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
     * 批量删除用户管理
     * 
     * @param userIds 需要删除的用户管理主键集合
     * @return 结果
     */
    public int deleteWallpaperUserByUserIds(Long[] userIds);

    /**
     * 删除用户管理信息
     * 
     * @param userId 用户管理主键
     * @return 结果
     */
    public int deleteWallpaperUserByUserId(Long userId);
}
