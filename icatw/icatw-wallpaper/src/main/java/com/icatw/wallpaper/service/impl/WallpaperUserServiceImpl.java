package com.icatw.wallpaper.service.impl;

import com.icatw.common.utils.DateUtils;
import com.icatw.wallpaper.domain.WallpaperUser;
import com.icatw.wallpaper.mapper.WallpaperUserMapper;
import com.icatw.wallpaper.service.IWallpaperUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户管理Service业务层处理
 *
 * @author icatw
 * @date 2022-10-04
 */
@Service
public class WallpaperUserServiceImpl implements IWallpaperUserService {
    @Autowired
    private WallpaperUserMapper wallpaperUserMapper;

    /**
     * 查询用户管理
     *
     * @param userId 用户管理主键
     * @return 用户管理
     */
    @Override
    public WallpaperUser selectWallpaperUserByUserId(Long userId) {
        return wallpaperUserMapper.selectWallpaperUserByUserId(userId);
    }

    /**
     * 查询用户管理列表
     *
     * @param wallpaperUser 用户管理
     * @return 用户管理
     */
    @Override
    public List<WallpaperUser> selectWallpaperUserList(WallpaperUser wallpaperUser) {
        return wallpaperUserMapper.selectWallpaperUserList(wallpaperUser);
    }

    /**
     * 新增用户管理
     *
     * @param wallpaperUser 用户管理
     * @return 结果
     */
    @Override
    public int insertWallpaperUser(WallpaperUser wallpaperUser) {
        wallpaperUser.setCreateTime(DateUtils.getNowDate());
        return wallpaperUserMapper.insertWallpaperUser(wallpaperUser);
    }

    /**
     * 修改用户管理
     *
     * @param wallpaperUser 用户管理
     * @return 结果
     */
    @Override
    public int updateWallpaperUser(WallpaperUser wallpaperUser) {
        wallpaperUser.setUpdateTime(DateUtils.getNowDate());
        return wallpaperUserMapper.updateWallpaperUser(wallpaperUser);
    }

    /**
     * 批量删除用户管理
     *
     * @param userIds 需要删除的用户管理主键
     * @return 结果
     */
    @Override
    public int deleteWallpaperUserByUserIds(Long[] userIds) {
        return wallpaperUserMapper.deleteWallpaperUserByUserIds(userIds);
    }

    /**
     * 删除用户管理信息
     *
     * @param userId 用户管理主键
     * @return 结果
     */
    @Override
    public int deleteWallpaperUserByUserId(Long userId) {
        return wallpaperUserMapper.deleteWallpaperUserByUserId(userId);
    }

    @Override
    public int updateWallpaperStatus(WallpaperUser wallpaperUser) {
        wallpaperUser.setUpdateTime(DateUtils.getNowDate());
        return wallpaperUserMapper.updateWallpaperUser(wallpaperUser);
    }
}
