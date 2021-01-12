package com.fante.project.business.bizDescription.service.impl;

import com.fante.common.constant.UserConstants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.common.utils.text.Convert;
import com.fante.framework.web.domain.Ztree;
import com.fante.project.business.bizDescription.domain.BizDescription;
import com.fante.project.business.bizDescription.mapper.BizDescriptionMapper;
import com.fante.project.business.bizDescription.service.IBizDescriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 文档说明Service业务层处理
 * 
 * @author fante
 * @date 2020-01-17
 */
@Service
public class BizDescriptionServiceImpl implements IBizDescriptionService 
{

    private static Logger log = LoggerFactory.getLogger(BizDescriptionServiceImpl.class);

    @Autowired
    private BizDescriptionMapper bizDescriptionMapper;

    /**
     * 查询文档说明
     * 
     * @param id 文档说明ID
     * @return 文档说明
     */
    @Override
    public BizDescription selectBizDescriptionById(Long id)
    {
        return bizDescriptionMapper.selectBizDescriptionById(id);
    }

    /**
     * 查询文档说明列表
     * 
     * @param bizDescription 文档说明
     * @return 文档说明
     */
    @Override
    public List<BizDescription> selectBizDescriptionList(BizDescription bizDescription)
    {
        return bizDescriptionMapper.selectBizDescriptionList(bizDescription);
    }

    /**
     * 新增文档说明
     * 
     * @param bizDescription 文档说明
     * @return 结果
     */
    @Override
    public int insertBizDescription(BizDescription bizDescription)
    {
        Long parentId = bizDescription.getParentId();
        if (0L != parentId){
            BizDescription description=bizDescriptionMapper.selectBizDescriptionById(parentId);
            // 如果父节点不为"正常"状态,则不允许新增子节点
            Checker.check(UserConstants.EXCEPTION.equals(description.getStatus()),"文档停用，不允许新增");
        }
        bizDescription.setCreateTime(DateUtils.getNowDate());
        bizDescription.setCreateBy(ShiroUtils.getLoginName());
        return bizDescriptionMapper.insertBizDescription(bizDescription);
    }

    /**
     * 修改文档说明
     * 
     * @param bizDescription 文档说明
     * @return 结果
     */
    @Override
    public int updateBizDescription(BizDescription bizDescription)
    {
        Long parentId = bizDescription.getParentId();
        if (0L != parentId){
            BizDescription description=bizDescriptionMapper.selectBizDescriptionById(parentId);
            // 如果父节点不为"正常"状态,则不允许新增子节点
            Checker.check(UserConstants.EXCEPTION.equals(description.getStatus()),"文档停用，不允许修改");
        }
        bizDescription.setUpdateTime(DateUtils.getNowDate());
        bizDescription.setUpdateBy(ShiroUtils.getLoginName());
        return bizDescriptionMapper.updateBizDescription(bizDescription);
    }

    /**
     * 删除文档说明对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBizDescriptionByIds(String ids)
    {
        return bizDescriptionMapper.deleteBizDescriptionByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除文档说明信息
     * 
     * @param id 文档说明ID
     * @return 结果
     */
    @Override
    public int deleteBizDescriptionById(Long id)
    {
        return bizDescriptionMapper.deleteBizDescriptionById(id);
    }

    /**
     * 查询文档说明树列表
     * 
     * @return 所有文档说明信息
     */
    @Override
    public List<Ztree> selectBizDescriptionTree()
    {
        List<BizDescription> bizDescriptionList = bizDescriptionMapper.selectBizDescriptionList(new BizDescription());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (BizDescription bizDescription : bizDescriptionList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(bizDescription.getId());
            ztree.setpId(bizDescription.getParentId());
            ztree.setName(bizDescription.getDescTitle());
            ztree.setTitle(bizDescription.getDescTitle());
            ztrees.add(ztree);
        }
        return ztrees;
    }
}
