package com.ruoyi.web.controller.vote;
import com.mysql.cj.jdbc.Driver;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.List;
import com.mysql.cj.xdevapi.DatabaseObject;
import com.mysql.cj.xdevapi.Schema;
import com.mysql.cj.xdevapi.Session;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.utils.DBConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.alg.Paillier;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.vote.domain.QQEncVote;
import com.ruoyi.vote.service.IQQEncVoteService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import static com.ruoyi.common.utils.ShiroUtils.getSysUser;
import static com.ruoyi.common.utils.uuid.IdUtils.fastSimpleUUID;
import com.ruoyi.vote.domain.QQCandidate;
import static com.ruoyi.vote.enums.Vote.NO;
import static com.ruoyi.vote.enums.Vote.YES;
import static com.ruoyi.vote.enums.Vote_w.YES1;
import com.ruoyi.vote.service.IQQCandidateService;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import static java.util.Objects.nonNull;
import org.apache.commons.collections4.CollectionUtils;
import static org.apache.commons.collections4.CollectionUtils.collect;
import org.apache.commons.collections4.Transformer;
import static org.apache.commons.lang3.ArrayUtils.contains;
import static org.apache.commons.lang3.ArrayUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.split;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ruoyi.vote.domain.QqRolew;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 投票记录查看Controller
 *
 * @author wmao
 * @date 2021-02-15
 */
@Controller
@RequestMapping("/vote/staff")
public class QQEncVoteController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(QQEncVoteController.class);

    private static final String PREFIX = "vote/staff";

    @Autowired
    private IQQCandidateService qqCandidateService;

    @Autowired
    private IQQEncVoteService qqEncVoteService;

    @Autowired
    private Paillier paillier;

    @RequiresPermissions("vote:staff:add")
    @PostMapping("/vnu")
    @ResponseBody
    public AjaxResult votesUserNum() {
        return AjaxResult.success(qqEncVoteService.countVoterNum());
    }

    /**
     * 查询投票记录查看列表
     */
    @RequiresPermissions("vote:staff:add")
    @PostMapping("/vnt")
    @ResponseBody
    public AjaxResult votes() {
//        String driverName = "com.mysql.cj.jdbc.Driver";
//        String url = "jdbc:mysql://localhost:3306/vote?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&allowMutiQueries=true&serverTimezone=GMT%2B8";
//        String userName = "root";
//        String userPwd = "";
//        Connection dbConn = null;//连接
//        Statement stmt = null;//执行语句
//        ResultSet rst=null;//结果集
        final List<Map<String, Object>> vtt = new ArrayList<>();
        List<QQCandidate> candidates = qqCandidateService.selectQQCandidateList(null);
        if (CollectionUtils.isNotEmpty(candidates)) {
            paillier.init();
            candidates.forEach(candidate -> {
                List<QQEncVote> votes = qqEncVoteService.selectQQEncVoteList(new QQEncVote(candidate.getId()));
                Collection<BigInteger> vts = CollectionUtils.isNotEmpty(votes) ? collect(votes, new Transformer<QQEncVote, BigInteger>() {
                    @Override
                    public BigInteger transform(QQEncVote vt) {
                        return new BigInteger(vt.getBallotRecord());
                    }
                }) : null;
                Map<String, Object> vttm = new HashMap<>();
                BigInteger vtr = paillier.cipherAdd(CollectionUtils.isNotEmpty(vts) ? vts.toArray(new BigInteger[0]) : null);
                vttm.put("id", candidate.getId());
                vttm.put("name", candidate.getName());
                vttm.put("counter", paillier.decryption(vtr).intValue());
                System.out.println(candidate.getId());
                vtt.add(vttm);
            });
        }
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            dbConn = DriverManager.getConnection(url, userName, userPwd);
//            System.out.println("Connection Successful!");   //如果连接成功 控制台输出Connection Successful!
//            stmt = dbConn.createStatement();
//            PreparedStatement up_mess = dbConn.prepareStatement("INSERT INTO qq_enccount (id, en_message) VALUES(?,?) ON DUPLICATE KEY UPDATE id=VALUES(id)");//更新数据，有就更新，没有就插入
//            vttm.forEach((key,value) -> {
//                try {
//                    up_mess.setInt(1,Integer.valueOf(key));
//                    up_mess.setString(2,value.toString());
//                    up_mess.executeQuery();
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//            });//遍历每一个值
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return AjaxResult.success(vtt);
    }

    /**
     * 查询投票记录查看列表
     */
    @PostMapping("/vn")
    @ResponseBody
    public AjaxResult votesNum() {
        final Map<String, Integer> vtt = new HashMap<>();
        List<QQCandidate> candidates = qqCandidateService.selectQQCandidateList(null);
        if (CollectionUtils.isNotEmpty(candidates)) {
            paillier.init();
            candidates.forEach(candidate -> {
                List<QQEncVote> votes = qqEncVoteService.selectQQEncVoteList(new QQEncVote(candidate.getId()));
                Collection<BigInteger> vts = CollectionUtils.isNotEmpty(votes) ? collect(votes, new Transformer<QQEncVote, BigInteger>() {
                    @Override
                    public BigInteger transform(QQEncVote vt) {
                        return new BigInteger(vt.getBallotRecord());
                    }
                }) : null;
                BigInteger vtr = paillier.cipherAdd(CollectionUtils.isNotEmpty(vts) ? vts.toArray(new BigInteger[0]) : null);
                //LOG.info("vts:{},vtr:{}", vts.toArray(new BigInteger[0]), vtr);
                vtt.put(candidate.getId(), paillier.decryption(vtr).intValue());
            });
        }
        return AjaxResult.success(vtt);
    }

    /**
     * 投票
     */
    @RequiresPermissions("vote:staff:add")
    @PostMapping("/poll")
    @ResponseBody
    public AjaxResult poll(String ids) {
        int ret = 0;
        String[] idArray = split(ids, ",");
        if (isNotEmpty(idArray)) {
            List<QQCandidate> candidates = qqCandidateService.selectQQCandidateList(null);
            SysUser sysUser = getSysUser();
            if (nonNull(sysUser)) {
                boolean areadyVoted = qqEncVoteService.alreadyVoted("" + sysUser.getUserId());
                if (areadyVoted) {
                    return error("请勿重复投票！");
                }
                Date currentTime = Calendar.getInstance().getTime();
                List<QQEncVote> votes = new ArrayList<>();
                paillier.init();
                candidates.forEach(candidate -> {
                    QQEncVote vote = new QQEncVote();
                    vote.setId(fastSimpleUUID());
                    vote.setCandidateId(candidate.getId());
                    vote.setCandidateName(candidate.getName());
                    vote.setUserId("" + sysUser.getUserId());
                    vote.setPlainRecord(contains(idArray, candidate.getId()) ? YES.getDesc() : NO.getDesc());
                    vote.setBallotRecord(paillier.encryption(contains(idArray, candidate.getId()) ? YES.getValue() : NO.getValue()).toString());
                    vote.setUserName(sysUser.getLoginName());
                    vote.setCreateBy(sysUser.getLoginName());
                    vote.setUpdateBy(sysUser.getLoginName());
                    vote.setCreateTime(currentTime);
                    vote.setUpdateTime(currentTime);
                    vote.setVoteTime(currentTime);
                    votes.add(vote);
                });
                ret = qqEncVoteService.batchInsertQQEncVote(votes);
            }
            return success("成功推选" + ret + "个人选");
        } else {
            return error("您还没有选中任何人，请检查！");
        }
    }
    /**
     * 加权投票
     */
    @RequiresPermissions("vote:staff:add")
    @PostMapping("/poll_w")
    @ResponseBody
    public AjaxResult poll_w(String ids) {
        SysUser temp1 = getSysUser();
        long c_id = temp1.getUserId();
        String driverName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/vote?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&allowMutiQueries=true&serverTimezone=GMT%2B8";
        String sql = "select role_weight from qq_rolew where role_num = ?";
        String userName = "root";
        String userPwd = "";
        Connection dbConn = null;//连接
        Statement stmt = null;//执行语句
        ResultSet rst=null;//结果集
        long t=1;//最终结果,初始值为1
        long rid=100;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConn = DriverManager.getConnection(url, userName, userPwd);
            System.out.println("Connection Successful!");   //如果连接成功 控制台输出Connection Successful!
            stmt = dbConn.createStatement();
            rst = stmt.executeQuery("select role_id from sys_user_role where user_id = '"+ c_id + "'");
            while(rst.next())
                rid= rst.getInt(1);//先查询roleid
            rst = stmt.executeQuery("select role_weight from qq_rolew where role_num = '"+ rid + "'");
            while(rst.next())
                t= rst.getInt(1);//根据roleid查询权
            System.out.println(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BigInteger temp = BigInteger.valueOf(t);
        int ret = 0;
        String[] idArray = split(ids, ",");
        if (isNotEmpty(idArray)) {
            List<QQCandidate> candidates = qqCandidateService.selectQQCandidateList(null);
            SysUser sysUser = getSysUser();
            if (nonNull(sysUser)) {
                boolean areadyVoted = qqEncVoteService.alreadyVoted("" + sysUser.getUserId());
                if (areadyVoted) {
                    return error("请勿重复投票！");
                }
                Date currentTime = Calendar.getInstance().getTime();
                List<QQEncVote> votes = new ArrayList<>();
                paillier.init();
                candidates.forEach(candidate -> {
                    QQEncVote vote = new QQEncVote();
                    vote.setId(fastSimpleUUID());
                    vote.setCandidateId(candidate.getId());
                    vote.setCandidateName(candidate.getName());
                    vote.setUserId("" + sysUser.getUserId());
                    vote.setPlainRecord(contains(idArray, candidate.getId()) ? "是" : NO.getDesc());
                    vote.setBallotRecord(paillier.encryption(contains(idArray, candidate.getId()) ? temp : NO.getValue()).toString());
                    vote.setUserName(sysUser.getLoginName());
                    vote.setCreateBy(sysUser.getLoginName());
                    vote.setUpdateBy(sysUser.getLoginName());
                    vote.setCreateTime(currentTime);
                    vote.setUpdateTime(currentTime);
                    vote.setVoteTime(currentTime);
                    votes.add(vote);
                });
                ret = qqEncVoteService.batchInsertQQEncVote(votes);
            }
            return success("成功推选" + ret + "个人选");
        } else {
            return error("您还没有选中任何人，请检查！");
        }
    }

    @RequiresPermissions("vote:staff:view")
    @GetMapping()
    public String items() {
        return PREFIX + "/items";
    }

    /**
     * 查询投票记录查看列表
     */
    @RequiresPermissions("vote:staff:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(QQEncVote qQEncVote) {
        startPage();
        if (nonNull(qQEncVote) && nonNull(qQEncVote.getVoteTime())) {
            Date voteTime = qQEncVote.getVoteTime();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(voteTime);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            qQEncVote.setCreateTime(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.SECOND, -1);
            qQEncVote.setUpdateTime(calendar.getTime());
        }
        List<QQEncVote> list = qqEncVoteService.selectQQEncVoteList(qQEncVote);
        return getDataTable(list);
    }

    /**
     * 导出投票记录查看列表
     */
    @RequiresPermissions("vote:staff:export")
    @Log(title = "投票记录查看", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(QQEncVote qQEncVote) {
        List<QQEncVote> list = qqEncVoteService.selectQQEncVoteList(qQEncVote);
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
    @RequiresPermissions("vote:staff:add")
    @Log(title = "投票记录查看", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(QQEncVote qQEncVote) {
        return toAjax(qqEncVoteService.insertQQEncVote(qQEncVote));
    }

    /**
     * 修改投票记录查看
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        QQEncVote qQEncVote = qqEncVoteService.selectQQEncVoteById(id);
        mmap.put("qQEncVote", qQEncVote);
        return PREFIX + "/edit";
    }

    /**
     * 修改保存投票记录查看
     */
    @RequiresPermissions("vote:staff:edit")
    @Log(title = "投票记录查看", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(QQEncVote qQEncVote) {
        return toAjax(qqEncVoteService.updateQQEncVote(qQEncVote));
    }

    /**
     * 删除投票记录查看
     */
    @RequiresPermissions("vote:staff:remove")
    @Log(title = "投票记录查看", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(qqEncVoteService.deleteQQEncVoteByIds(ids));
    }
}
