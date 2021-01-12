package com.fante.project.business.pmsProductAttribute.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.fante.common.business.enums.ProductAttributeCategoryConst;
import com.fante.common.constant.Constants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.pmsProductAttribute.mapper.PmsProductAttributeMapper;
import com.fante.project.business.pmsProductAttribute.domain.PmsProductAttribute;
import com.fante.project.business.pmsProductAttribute.service.IPmsProductAttributeService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 商品属性＆参数Service业务层处理
 *
 * @author fante
 * @date 2020-03-09
 */
@Service
public class PmsProductAttributeServiceImpl implements IPmsProductAttributeService {

    private static Logger log = LoggerFactory.getLogger(PmsProductAttributeServiceImpl.class);

    @Autowired
    private PmsProductAttributeMapper pmsProductAttributeMapper;

    /**
     * 查询商品属性＆参数
     *
     * @param id 商品属性＆参数ID
     * @return 商品属性＆参数
     */
    @Override
    public PmsProductAttribute selectPmsProductAttributeById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return pmsProductAttributeMapper.selectPmsProductAttributeById(id);
    }

    /**
     * 查询商品属性＆参数列表
     *
     * @param pmsProductAttribute 商品属性＆参数
     * @return 商品属性＆参数集合
     */
    @Override
    public List<PmsProductAttribute> selectPmsProductAttributeList(PmsProductAttribute pmsProductAttribute) {
        return pmsProductAttributeMapper.selectPmsProductAttributeList(pmsProductAttribute);
    }

    /**
     * 验证 选择[从列表中选取],则可选值列表不能为空
     * 根据 输入类型值inputType 判断并设置 参数列表inputList的值
     * @param pmsProductAttribute
     * @return
     */
    private PmsProductAttribute verifyInputType(PmsProductAttribute pmsProductAttribute){
        Integer inputType = pmsProductAttribute.getInputType();
        //若从列表中选择,需要验证列表数据并处理成逗号拼接的字符串
        if(Objects.equals(ProductAttributeCategoryConst.EntryMethodEnum.SELECT_ENTRY.getType(),inputType+"") ){
            //验证参数
            String inputListStr = pmsProductAttribute.getInputList();
            Checker.check(StringUtils.isEmpty(inputListStr),"选择[从列表中选取],则可选值列表不能为空!");
            String[] split = inputListStr.split("\\r\\n");
            StringBuilder builder = new StringBuilder();
            for (String s : split) {
                if(!StringUtils.isEmpty(s)){
                    builder.append(s.trim()+",");
                }
            }
            builder.deleteCharAt(builder.length()-1);
            pmsProductAttribute.setInputList(builder.toString());
        }else{
            pmsProductAttribute.setInputList("");
        }
        return pmsProductAttribute;
    }
    /**
     * 新增商品属性＆参数
     *
     * @param pmsProductAttribute 商品属性＆参数
     * @return 新增商品属性＆参数数量
     */
    @Override
    public int insertPmsProductAttribute(PmsProductAttribute pmsProductAttribute) {
        //验证参数
        Checker.check(StringUtils.isEmpty(pmsProductAttribute.getName()),"名称不能为空！");
        //验证并设置 参数列表inputList的值
        pmsProductAttribute = verifyInputType(pmsProductAttribute);
        //设置创建人 店铺id 创建时间
        User sysUser = ShiroUtils.getSysUser();
        //默人都支持新增
        pmsProductAttribute.setHandAddStatus(Integer.parseInt(ProductAttributeCategoryConst.SupportManualEnum.SUPPORT_YES.getType()));
        pmsProductAttribute.setCreateBy(sysUser.getLoginName());
        pmsProductAttribute.setShopId(sysUser.getDeptId());
        pmsProductAttribute.setCreateTime(DateUtils.getNowDate());
        return pmsProductAttributeMapper.insertPmsProductAttribute(pmsProductAttribute);
    }

    /**
     * 修改商品属性＆参数
     *
     * @param pmsProductAttribute 商品属性＆参数
     * @return 修改商品属性＆参数数量
     */
    @Override
    public int updatePmsProductAttribute(PmsProductAttribute pmsProductAttribute) {
        //验证并设置 参数列表inputList的值
        pmsProductAttribute = verifyInputType(pmsProductAttribute);
        //默人都支持新增
        pmsProductAttribute.setHandAddStatus(Integer.parseInt(ProductAttributeCategoryConst.SupportManualEnum.SUPPORT_YES.getType()));
        //设置更新人 更新时间
        pmsProductAttribute.setUpdateBy(ShiroUtils.getLoginName());
        pmsProductAttribute.setUpdateTime(DateUtils.getNowDate());
        return pmsProductAttributeMapper.updatePmsProductAttribute(pmsProductAttribute);
    }

    /**
     * 删除商品属性＆参数对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除商品属性＆参数数量
     */
    @Override
    public int deletePmsProductAttributeByIds(String ids) {
        return pmsProductAttributeMapper.deletePmsProductAttributeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商品属性＆参数信息
     *
     * @param id 商品属性＆参数ID
     * @return 删除商品属性＆参数数量
     */
    @Override
    public int deletePmsProductAttributeById(Long id) {
        return pmsProductAttributeMapper.deletePmsProductAttributeById(id);
    }


    /**
     * 唯一校验
     *
     * @param pmsProductAttribute 商品属性＆参数
     * @return 结果
     */
    @Override
    public String checkPmsProductAttributeUnique(PmsProductAttribute pmsProductAttribute) {
        return pmsProductAttributeMapper.checkPmsProductAttributeUnique(pmsProductAttribute) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }
}
