package com.icatw.wallpaper.controller;

import com.icatw.common.annotation.Log;
import com.icatw.common.core.controller.BaseController;
import com.icatw.common.core.domain.AjaxResult;
import com.icatw.common.core.page.TableDataInfo;
import com.icatw.common.enums.BusinessType;
import com.icatw.common.utils.poi.ExcelUtil;
import com.icatw.wallpaper.domain.WallpaperType;
import com.icatw.wallpaper.service.IWallpaperTypeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 分类管理Controller
 *
 * @author icatw
 * @date 2022-10-04
 */
@RestController
@RequestMapping("/wallpaper/type")
@Api(tags = "壁纸分类模块")
public class WallpaperTypeController extends BaseController
{
    @Autowired
    private IWallpaperTypeService wallpaperTypeService;

    /**
     * 查询分类管理列表
     */
    @PreAuthorize("@ss.hasPermi('wallpaper:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(WallpaperType wallpaperType)
    {
        startPage();
        List<WallpaperType> list = wallpaperTypeService.selectWallpaperTypeList(wallpaperType);
        return getDataTable(list);
    }

    /**
     * 获取分类列表
     */
    @PreAuthorize("@ss.hasPermi('wallpaper:type:list')")
    @GetMapping
    public AjaxResult list()
    {
        return AjaxResult.success(wallpaperTypeService.selectWallpaperTypeList(null));
    }
    /**
     * 导出分类管理列表
     */
    @PreAuthorize("@ss.hasPermi('wallpaper:type:export')")
    @Log(title = "分类管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WallpaperType wallpaperType)
    {
        List<WallpaperType> list = wallpaperTypeService.selectWallpaperTypeList(wallpaperType);
        ExcelUtil<WallpaperType> util = new ExcelUtil<WallpaperType>(WallpaperType.class);
        util.exportExcel(response, list, "分类管理数据");
    }

    /**
     * 获取分类管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('wallpaper:type:query')")
    @GetMapping(value = "/{typeId}")
    public AjaxResult getInfo(@PathVariable("typeId") Long typeId)
    {
        return AjaxResult.success(wallpaperTypeService.selectWallpaperTypeByTypeId(typeId));
    }

    /**
     * 新增分类管理
     */
    @PreAuthorize("@ss.hasPermi('wallpaper:type:add')")
    @Log(title = "分类管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WallpaperType wallpaperType)
    {
        return toAjax(wallpaperTypeService.insertWallpaperType(wallpaperType));
    }

    /**
     * 修改分类管理
     */
    @PreAuthorize("@ss.hasPermi('wallpaper:type:edit')")
    @Log(title = "分类管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WallpaperType wallpaperType)
    {
        return toAjax(wallpaperTypeService.updateWallpaperType(wallpaperType));
    }

    /**
     * 删除分类管理
     */
    @PreAuthorize("@ss.hasPermi('wallpaper:type:remove')")
    @Log(title = "分类管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{typeIds}")
    public AjaxResult remove(@PathVariable Long[] typeIds)
    {
        return toAjax(wallpaperTypeService.deleteWallpaperTypeByTypeIds(typeIds));
    }
}
