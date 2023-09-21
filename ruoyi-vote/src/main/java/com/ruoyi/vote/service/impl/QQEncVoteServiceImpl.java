package com.ruoyi.vote.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.vote.mapper.QQEncVoteMapper;
import com.ruoyi.vote.domain.QQEncVote;
import com.ruoyi.vote.service.IQQEncVoteService;
import com.ruoyi.common.core.text.Convert;

/**
 * 投票记录查看Service业务层处理
 * 
 * @author wmao
 * @date 2021-02-15
 */
@Service
public class QQEncVoteServiceImpl implements IQQEncVoteService 
{
    @Autowired
    private QQEncVoteMapper qQEncVoteMapper;

    /**
     * 查询投票记录查看
     * 
     * @param id 投票记录查看ID
     * @return 投票记录查看
     */
    @Override
    public QQEncVote selectQQEncVoteById(String id)
    {
        return qQEncVoteMapper.selectQQEncVoteById(id);
    }

    /**
     * 查询投票记录查看列表
     * 
     * @param qQEncVote 投票记录查看
     * @return 投票记录查看
     */
    @Override
    public List<QQEncVote> selectQQEncVoteList(QQEncVote qQEncVote)
    {
        return qQEncVoteMapper.selectQQEncVoteList(qQEncVote);
    }

    /**
     * 新增投票记录查看
     * 
     * @param qQEncVote 投票记录查看
     * @return 结果
     */
    @Override
    public int insertQQEncVote(QQEncVote qQEncVote)
    {
        return qQEncVoteMapper.insertQQEncVote(qQEncVote);
    }

    /**
     * 修改投票记录查看
     * 
     * @param qQEncVote 投票记录查看
     * @return 结果
     */
    @Override
    public int updateQQEncVote(QQEncVote qQEncVote)
    {
        return qQEncVoteMapper.updateQQEncVote(qQEncVote);
    }

    /**
     * 删除投票记录查看对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteQQEncVoteByIds(String ids)
    {
        return qQEncVoteMapper.deleteQQEncVoteByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除投票记录查看信息
     * 
     * @param id 投票记录查看ID
     * @return 结果
     */
    @Override
    public int deleteQQEncVoteById(String id)
    {
        return qQEncVoteMapper.deleteQQEncVoteById(id);
    }

    /**
     * 新增投票记录查看
     * 
     * @param qqEncVotes 投票记录查看
     * @return 结果
     */
    @Override
    public int batchInsertQQEncVote(List<QQEncVote> qqEncVotes) {
        return qQEncVoteMapper.batchInsertQQEncVote(qqEncVotes);
    }

    @Override
    public boolean alreadyVoted(String userId) {
        return qQEncVoteMapper.alreadyVoted(userId) > 0;
    }

    @Override
    public int countVoterNum() {
        return qQEncVoteMapper.countVoterNum();
    }
}
