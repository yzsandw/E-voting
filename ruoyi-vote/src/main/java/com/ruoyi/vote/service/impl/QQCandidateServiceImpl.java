package com.ruoyi.vote.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.vote.mapper.QQCandidateMapper;
import com.ruoyi.vote.domain.QQCandidate;
import com.ruoyi.vote.service.IQQCandidateService;
import com.ruoyi.common.core.text.Convert;

/**
 * 候选人Service业务层处理
 * 
 * @author heyuan
 * @date 2021-07-23
 */
@Service
public class QQCandidateServiceImpl implements IQQCandidateService 
{
    @Autowired
    private QQCandidateMapper qQCandidateMapper;

    /**
     * 查询候选人
     * 
     * @param id 候选人ID
     * @return 候选人
     */
    @Override
    public QQCandidate selectQQCandidateById(String id)
    {
        return qQCandidateMapper.selectQQCandidateById(id);
    }

    /**
     * 查询候选人列表
     * 
     * @param qQCandidate 候选人
     * @return 候选人
     */
    @Override
    public List<QQCandidate> selectQQCandidateList(QQCandidate qQCandidate)
    {
        return qQCandidateMapper.selectQQCandidateList(qQCandidate);
    }

    /**
     * 新增候选人
     * 
     * @param qQCandidate 候选人
     * @return 结果
     */
    @Override
    public int insertQQCandidate(QQCandidate qQCandidate)
    {
        qQCandidate.setCreateTime(DateUtils.getNowDate());
        return qQCandidateMapper.insertQQCandidate(qQCandidate);
    }

    /**
     * 修改候选人
     * 
     * @param qQCandidate 候选人
     * @return 结果
     */
    @Override
    public int updateQQCandidate(QQCandidate qQCandidate)
    {
        qQCandidate.setUpdateTime(DateUtils.getNowDate());
        return qQCandidateMapper.updateQQCandidate(qQCandidate);
    }

    /**
     * 删除候选人对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteQQCandidateByIds(String ids)
    {
        return qQCandidateMapper.deleteQQCandidateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除候选人信息
     * 
     * @param id 候选人ID
     * @return 结果
     */
    @Override
    public int deleteQQCandidateById(String id)
    {
        return qQCandidateMapper.deleteQQCandidateById(id);
    }
}
