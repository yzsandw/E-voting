package com.ruoyi.vote.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.vote.mapper.QqRolewMapper;
import com.ruoyi.vote.domain.QqRolew;
import com.ruoyi.vote.service.IQqRolewService;
import com.ruoyi.common.core.text.Convert;

/**
 * 权值设置Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-08-10
 */
@Service
public class QqRolewServiceImpl implements IQqRolewService 
{
    @Autowired
    private QqRolewMapper qqRolewMapper;

    /**
     * 查询权值设置
     * 
     * @param roleNum 权值设置ID
     * @return 权值设置
     */
    @Override
    public QqRolew selectQqRolewById(Long roleNum)
    {
        return qqRolewMapper.selectQqRolewById(roleNum);
    }

    /**
     * 查询权值设置列表
     * 
     * @param qqRolew 权值设置
     * @return 权值设置
     */
    @Override
    public List<QqRolew> selectQqRolewList(QqRolew qqRolew)
    {
        return qqRolewMapper.selectQqRolewList(qqRolew);
    }

    /**
     * 新增权值设置
     * 
     * @param qqRolew 权值设置
     * @return 结果
     */
    @Override
    public int insertQqRolew(QqRolew qqRolew)
    {
        return qqRolewMapper.insertQqRolew(qqRolew);
    }

    /**
     * 修改权值设置
     * 
     * @param qqRolew 权值设置
     * @return 结果
     */
    @Override
    public int updateQqRolew(QqRolew qqRolew)
    {
        return qqRolewMapper.updateQqRolew(qqRolew);
    }

    /**
     * 删除权值设置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteQqRolewByIds(String ids)
    {
        return qqRolewMapper.deleteQqRolewByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除权值设置信息
     * 
     * @param roleNum 权值设置ID
     * @return 结果
     */
    @Override
    public int deleteQqRolewById(Long roleNum)
    {
        return qqRolewMapper.deleteQqRolewById(roleNum);
    }
}
