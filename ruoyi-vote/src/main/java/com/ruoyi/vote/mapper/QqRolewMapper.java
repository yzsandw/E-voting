package com.ruoyi.vote.mapper;

import java.util.List;
import com.ruoyi.vote.domain.QqRolew;

/**
 * 权值设置Mapper接口
 * 
 * @author ruoyi
 * @date 2021-08-10
 */
public interface QqRolewMapper 
{
    /**
     * 查询权值设置
     * 
     * @param roleNum 权值设置ID
     * @return 权值设置
     */
    public QqRolew selectQqRolewById(Long roleNum);

    /**
     * 查询权值设置列表
     * 
     * @param qqRolew 权值设置
     * @return 权值设置集合
     */
    public List<QqRolew> selectQqRolewList(QqRolew qqRolew);

    /**
     * 新增权值设置
     * 
     * @param qqRolew 权值设置
     * @return 结果
     */
    public int insertQqRolew(QqRolew qqRolew);

    /**
     * 修改权值设置
     * 
     * @param qqRolew 权值设置
     * @return 结果
     */
    public int updateQqRolew(QqRolew qqRolew);

    /**
     * 删除权值设置
     * 
     * @param roleNum 权值设置ID
     * @return 结果
     */
    public int deleteQqRolewById(Long roleNum);

    /**
     * 批量删除权值设置
     * 
     * @param roleNums 需要删除的数据ID
     * @return 结果
     */
    public int deleteQqRolewByIds(String[] roleNums);
}
