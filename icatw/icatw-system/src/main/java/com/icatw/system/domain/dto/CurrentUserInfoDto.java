package com.icatw.system.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 当前用户信息dto
 *
 * @author icatw
 * @date 2022/05/29
 */
@Data
public class CurrentUserInfoDto implements Serializable {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户账号（第三方token）
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户电话
     */
    private String phonenumber;

    /**
     * 登陆ip
     */
    private String ipaddr;

    /**
     * 登陆地区
     */
    private String loginLocation;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 浏览器
     */
    private String browser;

}
