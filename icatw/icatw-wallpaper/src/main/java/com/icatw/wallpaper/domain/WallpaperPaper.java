package com.icatw.wallpaper.domain;

import com.icatw.common.annotation.Excel;
import com.icatw.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 壁纸列表对象 wallpaper_paper
 *
 * @author icatw
 * @date 2022-10-04
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WallpaperPaper extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 壁纸id */
    private Long paperId;

    /** 壁纸名 */
    @Excel(name = "壁纸名")
    private String paperName;

    /** 壁纸url */
    @Excel(name = "壁纸url")
    private String paperUrl;

    /** 逻辑删除（1删除，0未删除） */
    @Excel(name = "逻辑删除", readConverterExp = "1=删除，0=未删除")
    private Long isDeleted;

    /** 分类id */
    @Excel(name = "分类id")
    private Long typeId;

    /** 图片分辨率（width*height） */
    @Excel(name = "图片分辨率")
    private String paperSize;

}
