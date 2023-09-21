/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ruoyi.web.controller.vote;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.utils.ServletUtils;
import static com.ruoyi.common.utils.ShiroUtils.getLoginName;
import com.ruoyi.vote.domain.QQCandidate;
import com.ruoyi.vote.service.IQQCandidateService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author wmao
 */
@Controller
public class QQVoteController  extends BaseController{
    private static final Logger LOG = LoggerFactory.getLogger(QQVoteController.class);

    @Autowired
    private IQQCandidateService qqCandidateService;
    
    @GetMapping("/show")
    public String show(ModelMap mmap, HttpServletRequest request, HttpServletResponse response)
    {
        LOG.info("now enter QQVoteController show method!");
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request))
        {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }
        List<QQCandidate> cddts = qqCandidateService.selectQQCandidateList(null);
        if(isNotEmpty(cddts)){
            mmap.put("cddts", cddts);
        }
        
        mmap.put("user", getLoginName());
        LOG.info("now leave QQVoteController show method!");
        return "vote";
    }
}
