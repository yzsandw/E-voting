package com.ruoyi.vote.mapper;

import java.util.List;
import com.ruoyi.vote.domain.QQCandidate;

/**
 * 候选人Mapper接口
 * 
 * @author heyuan
 * @date 2021-07-23
 */
public interface QQCandidateMapper 
{
    /**
     * 查询候选人
     * 
     * @param id 候选人ID
     * @return 候选人
     */
    public QQCandidate selectQQCandidateById(String id);

    /**
     * 查询候选人列表
     * 
     * @param qQCandidate 候选人
     * @return 候选人集合
     */
    public List<QQCandidate> selectQQCandidateList(QQCandidate qQCandidate);

    /**
     * 新增候选人
     * 
     * @param qQCandidate 候选人
     * @return 结果
     */
    public int insertQQCandidate(QQCandidate qQCandidate);

    /**
     * 修改候选人
     * 
     * @param qQCandidate 候选人
     * @return 结果
     */
    public int updateQQCandidate(QQCandidate qQCandidate);

    /**
     * 删除候选人
     * 
     * @param id 候选人ID
     * @return 结果
     */
    public int deleteQQCandidateById(String id);

    /**
     * 批量删除候选人
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteQQCandidateByIds(String[] ids);
}
