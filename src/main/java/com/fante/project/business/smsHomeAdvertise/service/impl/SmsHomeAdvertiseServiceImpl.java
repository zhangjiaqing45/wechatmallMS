package com.fante.project.business.smsHomeAdvertise.service.impl;

import com.fante.common.business.constant.BizConstants;
import com.fante.common.business.enums.CommonUse;
import com.fante.common.business.enums.SmsAdvertiseConst;
import com.fante.common.constant.Constants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.common.utils.text.Convert;
import com.fante.project.business.smsHomeAdvertise.domain.SmsHomeAdvertise;
import com.fante.project.business.smsHomeAdvertise.domain.SmsHomeAdvertisePositionDTO;
import com.fante.project.business.smsHomeAdvertise.domain.SmsHomeAdvertiseProductSelectDTO;
import com.fante.project.business.smsHomeAdvertise.mapper.SmsHomeAdvertiseMapper;
import com.fante.project.business.smsHomeAdvertise.service.ISmsHomeAdvertiseService;
import com.fante.project.system.config.service.IConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 广告管理Service业务层处理
 *
 * @author fante
 * @date 2020-04-07
 */
@Service
public class SmsHomeAdvertiseServiceImpl implements ISmsHomeAdvertiseService {

    private static Logger log = LoggerFactory.getLogger(SmsHomeAdvertiseServiceImpl.class);

    @Autowired
    private SmsHomeAdvertiseMapper smsHomeAdvertiseMapper;
    @Autowired
    private IConfigService configService;

    /**
     * 查询广告管理
     *
     * @param id 广告管理ID
     * @return 广告管理
     */
    @Override
    public SmsHomeAdvertise selectSmsHomeAdvertiseById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return smsHomeAdvertiseMapper.selectSmsHomeAdvertiseById(id);
    }

    /**
     * 查询广告管理列表
     *
     * @param smsHomeAdvertise 广告管理
     * @return 广告管理集合
     */
    @Override
    public List<SmsHomeAdvertise> selectSmsHomeAdvertiseList(SmsHomeAdvertise smsHomeAdvertise) {
        return smsHomeAdvertiseMapper.selectSmsHomeAdvertiseList(smsHomeAdvertise);
    }

    /**
     * 查询广告管理数量
     *
     * @param smsHomeAdvertise 广告管理
     * @return 结果
     */
    @Override
    public int countSmsHomeAdvertise(SmsHomeAdvertise smsHomeAdvertise){
        return smsHomeAdvertiseMapper.countSmsHomeAdvertise(smsHomeAdvertise);
    }

    /**
     * 唯一校验
     *
     * @param smsHomeAdvertise 广告管理
     * @return 结果
     */
    @Override
    public String checkSmsHomeAdvertiseUnique(SmsHomeAdvertise smsHomeAdvertise) {
        return smsHomeAdvertiseMapper.checkSmsHomeAdvertiseUnique(smsHomeAdvertise) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增广告管理
     *
     * @param smsHomeAdvertise 广告管理
     * @return 新增广告管理数量
     */
    @Override
    public int insertSmsHomeAdvertise(SmsHomeAdvertise smsHomeAdvertise) {
        if (StringUtils.isBlank(smsHomeAdvertise.getCreateBy())) {
            smsHomeAdvertise.setCreateBy(ShiroUtils.getLoginName());
        }
        smsHomeAdvertise.setCreateTime(DateUtils.getNowDate());
        return smsHomeAdvertiseMapper.insertSmsHomeAdvertise(smsHomeAdvertise);
    }

    /**
     * 修改广告管理
     *
     * @param smsHomeAdvertise 广告管理
     * @return 修改广告管理数量
     */
    @Override
    public int updateSmsHomeAdvertise(SmsHomeAdvertise smsHomeAdvertise) {
        if (StringUtils.isBlank(smsHomeAdvertise.getUpdateBy())) {
            smsHomeAdvertise.setUpdateBy(ShiroUtils.getLoginName());
        }
        smsHomeAdvertise.setUpdateTime(DateUtils.getNowDate());
        return smsHomeAdvertiseMapper.updateSmsHomeAdvertise(smsHomeAdvertise);
    }

    /**
     * 删除广告管理对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除广告管理数量
     */
    @Override
    public int deleteSmsHomeAdvertiseByIds(String ids) {
        return smsHomeAdvertiseMapper.deleteSmsHomeAdvertiseByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除广告管理信息
     *
     * @param id 广告管理ID
     * @return 删除广告管理数量
     */
    @Override
    public int deleteSmsHomeAdvertiseById(Long id) {
        return smsHomeAdvertiseMapper.deleteSmsHomeAdvertiseById(id);
    }

    /**
     * 选择跳转商品
     * @param productSelectDTO
     * @return
     */
    @Override
    public List<SmsHomeAdvertiseProductSelectDTO> advertiseProductSelect(SmsHomeAdvertiseProductSelectDTO productSelectDTO) {
        return smsHomeAdvertiseMapper.advertiseProductSelect(productSelectDTO);
    }

    /**
     * 广告新增处理
     *
     * @param smsHomeAdvertise
     * @return
     */
    @Override
    public int insertProcess(SmsHomeAdvertise smsHomeAdvertise) {
System.out.println("smsHomeAdvertise.getPosition()="+smsHomeAdvertise.getPosition());
        if("hfloat".equals(smsHomeAdvertise.getPosition())){
            SmsHomeAdvertise smsHomeAdvertise0=new SmsHomeAdvertise();
            smsHomeAdvertise0.setPosition("hfloat");
          List<SmsHomeAdvertise> list=  smsHomeAdvertiseMapper.selectSmsHomeAdvertiseList(smsHomeAdvertise0);
           System.out.println("list.size()="+list.size());
            Checker.check(list.size()>0, "已存在首页浮动图标，请勿重复添加！");
        }

        SmsAdvertiseConst.UrlType urlType = SmsAdvertiseConst.UrlType.get(smsHomeAdvertise.getUrlType());
        Checker.check(ObjectUtils.isEmpty(urlType), "链接类型异常");

        smsHomeAdvertise.setStatus(Constants.ENABLE);

        if("other".equals(smsHomeAdvertise.getUrlType())){
            System.out.println("smsHomeAdvertise.getUrl()="+smsHomeAdvertise.getUrl());
            smsHomeAdvertise.setUrl(smsHomeAdvertise.getUrl());
            smsHomeAdvertise.setUrlDesp("外部链接");
        }else{
            smsHomeAdvertise.setUrl(getPrefix(urlType) + smsHomeAdvertise.getUrlTarget());
        }


        return insertSmsHomeAdvertise(smsHomeAdvertise);
    }

    /**
     * 广告修改处理
     *
     * @param smsHomeAdvertise
     * @return
     */
    @Override
    public int updateProcess(SmsHomeAdvertise smsHomeAdvertise) {
        SmsAdvertiseConst.UrlType urlType = SmsAdvertiseConst.UrlType.get(smsHomeAdvertise.getUrlType());
        Checker.check(ObjectUtils.isEmpty(urlType), "链接类型异常");
        if("other".equals(smsHomeAdvertise.getUrlType())){
            System.out.println("smsHomeAdvertise.getUrl()="+smsHomeAdvertise.getUrl());
            smsHomeAdvertise.setUrl(smsHomeAdvertise.getUrl());
            smsHomeAdvertise.setUrlDesp("外部链接");
        }else{
            smsHomeAdvertise.setUrl(getPrefix(urlType) + smsHomeAdvertise.getUrlTarget());
        }

        return updateSmsHomeAdvertise(smsHomeAdvertise);
    }

    /**
     * 根据链接类型获取前缀
     * @param urlType
     * @return
     */
    private String getPrefix(SmsAdvertiseConst.UrlType urlType) {
        switch (urlType) {
            case NONE:
                return "";
            case SHOP:
                return StringUtils.defaultString(configService.selectConfigByKey(BizConstants.smsAdvertise.AD_URL_PREFIX_SHOP));
            case PROMOTION:
                return "";
            case PRODUCT:
                return StringUtils.defaultString(configService.selectConfigByKey(BizConstants.smsAdvertise.AD_URL_PREFIX_PRODUCT));
            default:
                return "";
        }
    }

    /**
     * 变更状态
     * @param id
     * @param status
     * @return
     */
    @Override
    public int changeStatus(Long id, String status) {
        Checker.check(ObjectUtils.isEmpty(id), "缺少广告标识");
        Checker.check(StringUtils.isBlank(status), "缺少状态标识");
        SmsHomeAdvertise smsHomeAdvertise = new SmsHomeAdvertise();
        smsHomeAdvertise.setId(id);
        smsHomeAdvertise.setStatus(status);
        return updateSmsHomeAdvertise(smsHomeAdvertise);
    }

    /**
     * 变更排序
     * @param id
     * @param sort
     * @return
     */
    @Override
    public int changeSort(Long id, Integer sort) {
        Checker.check(ObjectUtils.isEmpty(id), "缺少广告标识");
        Checker.check(ObjectUtils.isEmpty(sort), "缺少排序");
        SmsHomeAdvertise smsHomeAdvertise = new SmsHomeAdvertise();
        smsHomeAdvertise.setId(id);
        smsHomeAdvertise.setSort(sort);
        return updateSmsHomeAdvertise(smsHomeAdvertise);
    }

    /**
     * 按照广告位置查询数据
     * @param showNum
     * @return
     */
    @Override
    public List<SmsHomeAdvertisePositionDTO> selectAdvertiseWithPosition(int showNum) {
        return smsHomeAdvertiseMapper.selectAdvertiseWithPosition(CommonUse.Status.ENABLE.getType(), showNum);
    }
}
