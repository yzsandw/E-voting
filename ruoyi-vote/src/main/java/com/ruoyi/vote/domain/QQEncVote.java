package com.ruoyi.vote.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 投票记录查看对象 qq_enc_vote
 * 
 * @author wmao
 * @date 2021-02-15
 */
public class QQEncVote extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 投票人ID */
    @Excel(name = "投票人ID")
    private String userId;

    /** 候选人ID */
    @Excel(name = "候选人ID")
    private String candidateId;

    /** 投票人姓名 */
    @Excel(name = "投票人姓名")
    private String userName;

    /** 候选人姓名 */
    @Excel(name = "候选人姓名")
    private String candidateName;
    
    /**
     * 加密投票结果
     */
    private String ballotRecord;
    
    /**
     * 解密投票结果
     */
    private String plainRecord;

    /** 投票日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date voteTime;
    
    public QQEncVote(){
        
    }
    
    public QQEncVote(String candidateId){
        this.candidateId = candidateId;
    }

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setCandidateId(String candidateId) 
    {
        this.candidateId = candidateId;
    }

    public String getCandidateId() 
    {
        return candidateId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setVoteTime(Date voteTime) 
    {
        this.voteTime = voteTime;
    }

    public Date getVoteTime() 
    {
        return voteTime;
    }

    /**
     * @return the candidateName
     */
    public String getCandidateName() {
        return candidateName;
    }

    /**
     * @param candidateName the candidateName to set
     */
    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    /**
     * @return the ballotRecord
     */
    public String getBallotRecord() {
        return ballotRecord;
    }

    /**
     * @param ballotRecord the ballotRecord to set
     */
    public void setBallotRecord(String ballotRecord) {
        this.ballotRecord = ballotRecord;
    }

    /**
     * @return the plainRecord
     */
    public String getPlainRecord() {
        return plainRecord;
    }

    /**
     * @param plainRecord the plainRecord to set
     */
    public void setPlainRecord(String plainRecord) {
        this.plainRecord = plainRecord;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
