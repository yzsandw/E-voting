package com.ruoyi.vote.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 候选人对象 qq_candidate
 *
 * @author wmao
 * @date 2021-02-13
 */
public class QQCandidate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 姓名 */
    @Excel(name = "肖像")
    private String imgPath;

    /** 描述 */
    @Excel(name = "描述")
    private String desc;

    private int votesNum = 0;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public String getDesc()
    {
        return desc;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("imgPath", getImgPath())
                .append("desc", getDesc())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }

    /**
     * @return the imgPath
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * @param imgPath the imgPath to set
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    /**
     * @return the votesNum
     */
    public int getVotesNum() {
        return votesNum;
    }

    /**
     * @param votesNum the votesNum to set
     */
    public void setVotesNum(int votesNum) {
        this.votesNum = votesNum;
    }
}
