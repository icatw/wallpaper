package com.icatw.wallpaper.service.impl;

import com.icatw.common.utils.DateUtils;
import com.icatw.wallpaper.domain.WallpaperPaper;
import com.icatw.wallpaper.mapper.WallpaperPaperMapper;
import com.icatw.wallpaper.service.IWallpaperPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 壁纸列表Service业务层处理
 *
 * @author icatw
 * @date 2022-10-04
 */
@Service
public class WallpaperPaperServiceImpl implements IWallpaperPaperService {
    @Autowired
    private WallpaperPaperMapper wallpaperPaperMapper;

    /**
     * 查询壁纸列表
     *
     * @param paperId 壁纸列表主键
     * @return 壁纸列表
     */
    @Override
    public WallpaperPaper selectWallpaperPaperByPaperId(Long paperId) {
        return wallpaperPaperMapper.selectWallpaperPaperByPaperId(paperId);
    }

    /**
     * 查询壁纸列表列表
     *
     * @param wallpaperPaper 壁纸列表
     * @return 壁纸列表
     */
    @Override
    public List<WallpaperPaper> selectWallpaperPaperList(WallpaperPaper wallpaperPaper) {
        return wallpaperPaperMapper.selectWallpaperPaperList(wallpaperPaper);
    }

    /**
     * 新增壁纸列表
     *
     * @param wallpaperPaper 壁纸列表
     * @return 结果
     */
    @Override
    public int insertWallpaperPaper(WallpaperPaper wallpaperPaper) {
        wallpaperPaper.setCreateTime(DateUtils.getNowDate());
        return wallpaperPaperMapper.insertWallpaperPaper(wallpaperPaper);
    }

    /**
     * 修改壁纸列表
     *
     * @param wallpaperPaper 壁纸列表
     * @return 结果
     */
    @Override
    public int updateWallpaperPaper(WallpaperPaper wallpaperPaper) {
        wallpaperPaper.setUpdateTime(DateUtils.getNowDate());
        return wallpaperPaperMapper.updateWallpaperPaper(wallpaperPaper);
    }

    /**
     * 批量删除壁纸列表
     *
     * @param paperIds 需要删除的壁纸列表主键
     * @return 结果
     */
    @Override
    public int deleteWallpaperPaperByPaperIds(Long[] paperIds) {
        return wallpaperPaperMapper.deleteWallpaperPaperByPaperIds(paperIds);
    }

    /**
     * 删除壁纸列表信息
     *
     * @param paperId 壁纸列表主键
     * @return 结果
     */
    @Override
    public int deleteWallpaperPaperByPaperId(Long paperId) {
        return wallpaperPaperMapper.deleteWallpaperPaperByPaperId(paperId);
    }

    @Override
    public int updateWallpaperStatus(WallpaperPaper wallpaperPaper) {
        wallpaperPaper.setUpdateTime(DateUtils.getNowDate());
        return wallpaperPaperMapper.updateWallpaperPaper(wallpaperPaper);
    }
}
