package com.ruoyi.vote.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 权值设置对象 qq_rolew
 * 
 * @author ruoyi
 * @date 2021-08-10
 */
public class QqRolew extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long roleNum;

    /**  */
    @Excel(name = "权值")
    private Long roleWeight;

    public void setRoleNum(Long roleNum) 
    {
        this.roleNum = roleNum;
    }

    public Long getRoleNum() 
    {
        return roleNum;
    }
    public void setRoleWeight(Long roleWeight) 
    {
        this.roleWeight = roleWeight;
    }

    public Long getRoleWeight() 
    {
        return roleWeight;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("roleNum", getRoleNum())
            .append("roleWeight", getRoleWeight())
            .toString();
    }
}
