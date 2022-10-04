package com.icatw.wallpaper.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.icatw.common.annotation.Excel;
import com.icatw.common.core.domain.BaseEntity;

/**
 * 分类管理对象 wallpaper_type
 * 
 * @author icatw
 * @date 2022-10-04
 */
public class WallpaperType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long typeId;

    /** 分类名 */
    @Excel(name = "分类名")
    private String typeName;

    /** 逻辑删除（1删除，0未删除） */
    @Excel(name = "逻辑删除", readConverterExp = "1=删除，0未删除")
    private Long isDeleted;

    public void setTypeId(Long typeId) 
    {
        this.typeId = typeId;
    }

    public Long getTypeId() 
    {
        return typeId;
    }
    public void setTypeName(String typeName) 
    {
        this.typeName = typeName;
    }

    public String getTypeName() 
    {
        return typeName;
    }
    public void setIsDeleted(Long isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Long getIsDeleted() 
    {
        return isDeleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("typeId", getTypeId())
            .append("typeName", getTypeName())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
