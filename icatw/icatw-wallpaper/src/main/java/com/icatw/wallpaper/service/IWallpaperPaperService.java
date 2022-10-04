package com.icatw.wallpaper.service;

import com.icatw.wallpaper.domain.WallpaperPaper;

import java.util.List;

/**
 * 壁纸列表Service接口
 *
 * @author icatw
 * @date 2022-10-04
 */
public interface IWallpaperPaperService
{
    /**
     * 查询壁纸列表
     *
     * @param paperId 壁纸列表主键
     * @return 壁纸列表
     */
    public WallpaperPaper selectWallpaperPaperByPaperId(Long paperId);

    /**
     * 查询壁纸列表列表
     *
     * @param wallpaperPaper 壁纸列表
     * @return 壁纸列表集合
     */
    public List<WallpaperPaper> selectWallpaperPaperList(WallpaperPaper wallpaperPaper);

    /**
     * 新增壁纸列表
     *
     * @param wallpaperPaper 壁纸列表
     * @return 结果
     */
    public int insertWallpaperPaper(WallpaperPaper wallpaperPaper);

    /**
     * 修改壁纸列表
     *
     * @param wallpaperPaper 壁纸列表
     * @return 结果
     */
    public int updateWallpaperPaper(WallpaperPaper wallpaperPaper);

    /**
     * 批量删除壁纸列表
     *
     * @param paperIds 需要删除的壁纸列表主键集合
     * @return 结果
     */
    public int deleteWallpaperPaperByPaperIds(Long[] paperIds);

    /**
     * 删除壁纸列表信息
     *
     * @param paperId 壁纸列表主键
     * @return 结果
     */
    public int deleteWallpaperPaperByPaperId(Long paperId);

    /**
     * 更新墙纸状态
     *
     * @param wallpaperPaper 壁纸纸
     * @return int
     */
    int updateWallpaperStatus(WallpaperPaper wallpaperPaper);
}
