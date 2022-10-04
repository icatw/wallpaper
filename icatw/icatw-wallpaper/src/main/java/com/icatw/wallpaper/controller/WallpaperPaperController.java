package com.icatw.wallpaper.controller;

import com.icatw.common.annotation.Log;
import com.icatw.common.core.controller.BaseController;
import com.icatw.common.core.domain.AjaxResult;
import com.icatw.common.core.page.TableDataInfo;
import com.icatw.common.enums.BusinessType;
import com.icatw.common.utils.poi.ExcelUtil;
import com.icatw.wallpaper.domain.WallpaperPaper;
import com.icatw.wallpaper.domain.WallpaperType;
import com.icatw.wallpaper.service.IWallpaperPaperService;
import com.icatw.wallpaper.service.IWallpaperTypeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 壁纸列表Controller
 *
 * @author icatw
 * @date 2022-10-04
 */
@RestController
@RequestMapping("/wallpaper/paper")
@Api(tags = "壁纸模块")
public class WallpaperPaperController extends BaseController {
    @Autowired
    private IWallpaperPaperService wallpaperPaperService;
    @Autowired
    private IWallpaperTypeService wallpaperTypeService;

    /**
     * 查询壁纸列表列表
     */
    @PreAuthorize("@ss.hasPermi('wallpaper:paper:list')")
    @GetMapping("/list")
    public TableDataInfo list(WallpaperPaper wallpaperPaper) {
        startPage();
        List<WallpaperPaper> list = wallpaperPaperService.selectWallpaperPaperList(wallpaperPaper);
        list.forEach(paper -> {
            Long typeId = paper.getTypeId();
            WallpaperType wallpaperType = wallpaperTypeService.selectWallpaperTypeByTypeId(typeId);
            paper.setType(wallpaperType);
        });
        return getDataTable(list);
    }

    /**
     * 导出壁纸列表列表
     */
    @PreAuthorize("@ss.hasPermi('wallpaper:paper:export')")
    @Log(title = "壁纸列表" , businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WallpaperPaper wallpaperPaper) {
        List<WallpaperPaper> list = wallpaperPaperService.selectWallpaperPaperList(wallpaperPaper);
        ExcelUtil<WallpaperPaper> util = new ExcelUtil<WallpaperPaper>(WallpaperPaper.class);
        util.exportExcel(response, list, "壁纸列表数据");
    }

    /**
     * 获取壁纸列表详细信息
     */
    @PreAuthorize("@ss.hasPermi('wallpaper:paper:query')")
    @GetMapping(value = "/{paperId}")
    public AjaxResult getInfo(@PathVariable("paperId") Long paperId) {
        return AjaxResult.success(wallpaperPaperService.selectWallpaperPaperByPaperId(paperId));
    }

    /**
     * 新增壁纸列表
     */
    @PreAuthorize("@ss.hasPermi('wallpaper:paper:add')")
    @Log(title = "壁纸列表" , businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WallpaperPaper wallpaperPaper) {
        return toAjax(wallpaperPaperService.insertWallpaperPaper(wallpaperPaper));
    }

    /**
     * 修改壁纸列表
     */
    @PreAuthorize("@ss.hasPermi('wallpaper:paper:edit')")
    @Log(title = "壁纸列表" , businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WallpaperPaper wallpaperPaper) {
        return toAjax(wallpaperPaperService.updateWallpaperPaper(wallpaperPaper));
    }

    /**
     * 删除壁纸列表
     */
    @PreAuthorize("@ss.hasPermi('wallpaper:paper:remove')")
    @Log(title = "壁纸列表" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{paperIds}")
    public AjaxResult remove(@PathVariable Long[] paperIds) {
        return toAjax(wallpaperPaperService.deleteWallpaperPaperByPaperIds(paperIds));
    }
}
