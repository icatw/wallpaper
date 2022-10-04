package com.icatw.wallpaper.controller;

import com.icatw.common.annotation.Log;
import com.icatw.common.core.controller.BaseController;
import com.icatw.common.core.domain.AjaxResult;
import com.icatw.common.core.page.TableDataInfo;
import com.icatw.common.enums.BusinessType;
import com.icatw.common.utils.poi.ExcelUtil;
import com.icatw.wallpaper.domain.WallpaperUser;
import com.icatw.wallpaper.service.IWallpaperUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户管理Controller
 *
 * @author icatw
 * @date 2022-10-04
 */
@RestController
@RequestMapping("/wallpaper/user")
@Api(tags = "壁纸用户模块")
public class WallpaperUserController extends BaseController
{
    @Autowired
    private IWallpaperUserService wallpaperUserService;

    /**
     * 查询用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('wallpaper:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(WallpaperUser wallpaperUser)
    {
        startPage();
        List<WallpaperUser> list = wallpaperUserService.selectWallpaperUserList(wallpaperUser);
        return getDataTable(list);
    }

    /**
     * 导出用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('wallpaper:user:export')")
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WallpaperUser wallpaperUser)
    {
        List<WallpaperUser> list = wallpaperUserService.selectWallpaperUserList(wallpaperUser);
        ExcelUtil<WallpaperUser> util = new ExcelUtil<WallpaperUser>(WallpaperUser.class);
        util.exportExcel(response, list, "用户管理数据");
    }

    /**
     * 获取用户管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('wallpaper:user:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return AjaxResult.success(wallpaperUserService.selectWallpaperUserByUserId(userId));
    }

    /**
     * 新增用户管理
     */
    @PreAuthorize("@ss.hasPermi('wallpaper:user:add')")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WallpaperUser wallpaperUser)
    {
        return toAjax(wallpaperUserService.insertWallpaperUser(wallpaperUser));
    }

    /**
     * 修改用户管理
     */
    @PreAuthorize("@ss.hasPermi('wallpaper:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WallpaperUser wallpaperUser)
    {
        return toAjax(wallpaperUserService.updateWallpaperUser(wallpaperUser));
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('wallpaper:user:edit')")
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody WallpaperUser wallpaperUser)
    {
        return toAjax(wallpaperUserService.updateWallpaperStatus(wallpaperUser));
    }
    /**
     * 删除用户管理
     */
    @PreAuthorize("@ss.hasPermi('wallpaper:user:remove')")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(wallpaperUserService.deleteWallpaperUserByUserIds(userIds));
    }
}
