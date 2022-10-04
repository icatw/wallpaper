package com.icatw.web.controller.system;

import com.icatw.common.core.domain.AjaxResult;
import com.icatw.common.core.domain.model.LoginUser;
import com.icatw.system.domain.dto.CurrentUserInfoDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author icatw
 * @date 2022/5/29
 * @email 762188827@qq.com
 * @apiNote
 */
@Api(tags = "获取当前登录用户信息")
@RestController
@RequestMapping("/user/current")
public class CurrUserController {

    @ApiOperation("获取当前用户信息，包含浏览器ip信息")
    @GetMapping("/getUserInfo")
    public AjaxResult getUserInfo() {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CurrentUserInfoDto userInfoDto = new CurrentUserInfoDto();
        copyUserInfo(loginUser, userInfoDto);
        return AjaxResult.success(userInfoDto);
    }

    private void copyUserInfo(LoginUser loginUser, CurrentUserInfoDto userInfoDto) {
        userInfoDto.setUserId(loginUser.getUserId());
        userInfoDto.setUserName(loginUser.getUser().getUserName());
        userInfoDto.setNickName(loginUser.getUser().getNickName());
        userInfoDto.setPhonenumber(loginUser.getUser().getPhonenumber());
        userInfoDto.setEmail(loginUser.getUser().getEmail());
        userInfoDto.setAvatar(loginUser.getUser().getAvatar());
        userInfoDto.setBrowser(loginUser.getBrowser());
        userInfoDto.setIpaddr(loginUser.getIpaddr());
        userInfoDto.setOs(loginUser.getOs());
        userInfoDto.setLoginLocation(loginUser.getLoginLocation());

    }
}
