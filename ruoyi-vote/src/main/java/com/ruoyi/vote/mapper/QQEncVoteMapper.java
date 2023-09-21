package com.ruoyi.vote.mapper;

import java.util.List;
import com.ruoyi.vote.domain.QQEncVote;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * 投票记录查看Mapper接口
 * 
 * @author wmao
 * @date 2021-02-15
 */
public interface QQEncVoteMapper 
{
    /**
     * 查询投票记录查看
     * 
     * @return 投票记录查看
     */
    public int countVoterNum();
    
    /**
     * 查询投票记录查看
     * 
     * @param id 投票记录查看ID
     * @return 投票记录查看
     */
    public QQEncVote selectQQEncVoteById(String id);

    /**
     * 查询投票记录查看列表
     * 
     * @param qQEncVote 投票记录查看
     * @return 投票记录查看集合
     */
    public List<QQEncVote> selectQQEncVoteList(QQEncVote qQEncVote);

    /**
     * 新增投票记录查看
     * 
     * @param qQEncVote 投票记录查看
     * @return 结果
     */
    public int insertQQEncVote(QQEncVote qQEncVote);

    /**
     * 修改投票记录查看
     * 
     * @param qQEncVote 投票记录查看
     * @return 结果
     */
    public int updateQQEncVote(QQEncVote qQEncVote);

    /**
     * 删除投票记录查看
     * 
     * @param id 投票记录查看ID
     * @return 结果
     */
    public int deleteQQEncVoteById(String id);

    /**
     * 批量删除投票记录查看
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteQQEncVoteByIds(String[] ids);
    /**
     * 新增投票记录查看
     * 
     * @param qqEncVotes 投票记录查看
     * @return 结果
     */
    public int batchInsertQQEncVote(@Param("qqEncVotes") List<QQEncVote> qqEncVotes);
    
    /**
     * 是否已经投过票
     * @param userId
     * @return 
     */
    public int alreadyVoted(@Param("userId") String userId);
}
