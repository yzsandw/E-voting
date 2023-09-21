
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
import com.ruoyi.vote.domain.QQCandidate;
import com.ruoyi.vote.service.IQQCandidateService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 候选人Controller
 *
 * @author wmao
 * @date 2021-02-13
 */
@Controller
@RequestMapping("/vote/candidate")
public class QQCandidateController extends BaseController
{
    private String prefix = "vote/candidate";

    @Autowired
    private IQQCandidateService qQCandidateService;

    @RequiresPermissions("vote:candidate:view")
    @GetMapping()
    public String candidate()
    {
        return prefix + "/candidate";
    }

    /**
     * 查询候选人列表
     */
    @RequiresPermissions("vote:candidate:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(QQCandidate qQCandidate)
    {
        startPage();
        List<QQCandidate> list = qQCandidateService.selectQQCandidateList(qQCandidate);
        return getDataTable(list);
    }

    /**
     * 导出候选人列表
     */
    @RequiresPermissions("vote:candidate:export")
    @Log(title = "候选人", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(QQCandidate qQCandidate)
    {
        List<QQCandidate> list = qQCandidateService.selectQQCandidateList(qQCandidate);
        ExcelUtil<QQCandidate> util = new ExcelUtil<QQCandidate>(QQCandidate.class);
        return util.exportExcel(list, "candidate");
    }

    /**
     * 新增候选人
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存候选人
     */
    @RequiresPermissions("vote:candidate:add")
    @Log(title = "候选人", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(QQCandidate qQCandidate)
    {
        return toAjax(qQCandidateService.insertQQCandidate(qQCandidate));
    }

    /**
     * 修改候选人
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        QQCandidate qQCandidate = qQCandidateService.selectQQCandidateById(id);
        mmap.put("qQCandidate", qQCandidate);
        return prefix + "/edit";
    }

    /**
     * 修改保存候选人
     */
    @RequiresPermissions("vote:candidate:edit")
    @Log(title = "候选人", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(QQCandidate qQCandidate)
    {
        return toAjax(qQCandidateService.updateQQCandidate(qQCandidate));
    }

    /**
     * 删除候选人
     */
    @RequiresPermissions("vote:candidate:remove")
    @Log(title = "候选人", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(qQCandidateService.deleteQQCandidateByIds(ids));
    }
}
