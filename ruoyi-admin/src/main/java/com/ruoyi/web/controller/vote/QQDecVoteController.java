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
import com.ruoyi.vote.domain.QQEncVote;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.vote.service.IQQEncVoteService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 投票记录查看Controller
 *
 * @author wmao
 * @date 2021-02-14
 */
@Controller
@RequestMapping("/vote/employee")
public class QQDecVoteController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(QQDecVoteController.class);
    private static final String PREFIX = "vote/employee";
    
    private static final DateFormat DF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private IQQEncVoteService qqEncVoteService;

    @RequiresPermissions("vote:employee:view")
    @GetMapping()
    public String items() {
        return PREFIX + "/items";
    }

    /**
     * 查询投票记录查看列表
     */
    @RequiresPermissions("vote:employee:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(QQEncVote qqEncVote) {
        startPage();
        List<QQEncVote> list = qqEncVoteService.selectQQEncVoteList(qqEncVote);
        return getDataTable(list);
    }

    /**
     * 导出投票记录查看列表
     */
    @RequiresPermissions("vote:employee:export")
    @Log(title = "投票记录查看", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(QQEncVote qqEncVote) {
        List<QQEncVote> list = qqEncVoteService.selectQQEncVoteList(qqEncVote);
        ExcelUtil<QQEncVote> util = new ExcelUtil<QQEncVote>(QQEncVote.class);
        return util.exportExcel(list, "employee");
    }

    /**
     * 新增投票记录查看
     */
    @GetMapping("/add")
    public String add() {
        return PREFIX + "/add";
    }

    /**
     * 新增保存投票记录查看
     */
    @RequiresPermissions("vote:employee:add")
    @Log(title = "投票记录查看", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(QQEncVote qqEncVote) {
        return toAjax(qqEncVoteService.insertQQEncVote(qqEncVote));
    }

    /**
     * 修改投票记录查看
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        QQEncVote qqEncVote = qqEncVoteService.selectQQEncVoteById(id);
        mmap.put("qqEncVote", qqEncVote);
        return PREFIX + "/edit";
    }

    /**
     * 修改保存投票记录查看
     */
    @RequiresPermissions("vote:employee:edit")
    @Log(title = "投票记录查看", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(QQEncVote qqEncVote) {
        return toAjax(qqEncVoteService.updateQQEncVote(qqEncVote));
    }

    /**
     * 删除投票记录查看
     */
    @RequiresPermissions("vote:employee:remove")
    @Log(title = "投票记录查看", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(qqEncVoteService.deleteQQEncVoteByIds(ids));
    }
}
