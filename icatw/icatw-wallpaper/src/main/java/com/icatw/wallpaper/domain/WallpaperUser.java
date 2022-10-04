package com.icatw.wallpaper.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.icatw.common.annotation.Excel;
import com.icatw.common.core.domain.BaseEntity;

/**
 * 用户管理对象 wallpaper_user
 * 
 * @author icatw
 * @date 2022-10-04
 */
public class WallpaperUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键自增id */
    @Excel(name = "主键自增id")
    private Long userId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickName;

    /** 密码 */
    private String password;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 【是否启用账号】 */
    @Excel(name = "【是否启用账号】")
    private Long status;

    /** 最后登陆时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后登陆时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastLoginTime;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setNickName(String nickName) 
    {
        this.nickName = nickName;
    }

    public String getNickName() 
    {
        return nickName;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setLastLoginTime(Date lastLoginTime) 
    {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getLastLoginTime() 
    {
        return lastLoginTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("nickName", getNickName())
            .append("password", getPassword())
            .append("sex", getSex())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("lastLoginTime", getLastLoginTime())
            .toString();
    }
}
