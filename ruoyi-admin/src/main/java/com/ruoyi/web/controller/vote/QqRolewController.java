package com.ruoyi.web.controller.vote;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.vote.domain.QqRolew;
import com.ruoyi.vote.service.IQqRolewService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 权值设置Controller
 * 
 * @author ruoyi
 * @date 2021-08-10
 */
@Controller
@RequestMapping("/vote/rolew")
public class QqRolewController extends BaseController
{
    private String prefix = "vote/rolew";

    @Autowired
    private IQqRolewService qqRolewService;

    @RequiresPermissions("vote:rolew:view")
    @GetMapping()
    public String rolew()
    {
        return prefix + "/rolew";
    }

    /**
     * 查询权值设置列表
     */
    @RequiresPermissions("vote:rolew:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(QqRolew qqRolew)
    {
        startPage();
        List<QqRolew> list = qqRolewService.selectQqRolewList(qqRolew);
        return getDataTable(list);
    }

    /**
     * 导出权值设置列表
     */
    @RequiresPermissions("vote:rolew:export")
    @Log(title = "权值设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(QqRolew qqRolew)
    {
        List<QqRolew> list = qqRolewService.selectQqRolewList(qqRolew);
        ExcelUtil<QqRolew> util = new ExcelUtil<QqRolew>(QqRolew.class);
        return util.exportExcel(list, "rolew");
    }

    /**
     * 新增权值设置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存权值设置
     */
    @RequiresPermissions("vote:rolew:add")
    @Log(title = "权值设置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(QqRolew qqRolew)
    {
        return toAjax(qqRolewService.insertQqRolew(qqRolew));
    }

    /**
     * 修改权值设置
     */
    @GetMapping("/edit/{roleNum}")
    public String edit(@PathVariable("roleNum") Long roleNum, ModelMap mmap)
    {
        QqRolew qqRolew = qqRolewService.selectQqRolewById(roleNum);
        mmap.put("qqRolew", qqRolew);
        return prefix + "/edit";
    }

    /**
     * 修改保存权值设置
     */
    @RequiresPermissions("vote:rolew:edit")
    @Log(title = "权值设置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(QqRolew qqRolew)
    {
        return toAjax(qqRolewService.updateQqRolew(qqRolew));
    }

    /**
     * 删除权值设置
     */
    @RequiresPermissions("vote:rolew:remove")
    @Log(title = "权值设置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(qqRolewService.deleteQqRolewByIds(ids));
    }
}
