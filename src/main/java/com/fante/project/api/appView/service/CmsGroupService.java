package com.fante.project.api.appView.service;

import com.fante.common.utils.Checker;
import com.fante.common.utils.LocalDateUtil;
import com.fante.project.api.appView.domain.PmsGroupProductDetailPageInfo;
import com.fante.project.api.omsOrderProcess.service.ISmsMemberCouponService;
import com.fante.project.business.smsGroupGame.domain.SmsGroupGame;
import com.fante.project.business.smsGroupGame.service.ISmsGroupGameService;
import com.fante.project.business.smsGroupGameRecord.domain.SmsGroupGameRecordDetail;
import com.fante.project.business.smsGroupGameRecord.service.ISmsGroupGameRecordService;
import com.fante.project.business.smsGroupGameSku.service.ISmsGroupGameSkuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

/**
 * @author ftnet
 * @Description CmsGroupService
 * @CreatTime 2020/5/2
 */
@Service
public class CmsGroupService {
    private static Logger log = LoggerFactory.getLogger( CmsGroupService.class);

    /**
     * 团购商品
     */
    @Autowired
    private ISmsGroupGameService iSmsGroupGameService;
    /**
     * 团购商品规格
     */
    @Autowired
    private ISmsGroupGameSkuService iSmsGroupGameSkuService;
    /**
     * 组团服务
     */
    @Autowired
    private ISmsGroupGameRecordService iSmsGroupGameRecordService;
    /**
     * 组团成员服务
     */
    @Autowired
    private ISmsMemberCouponService iSmsMemberCouponService;
    /**
     * 收藏 服务接口
     */
    @Autowired
    private CmsStarService cmsStarService;

    /**
     * 获取可用团购商品
     */
    public List<SmsGroupGame> getEnableGroupProduct(SmsGroupGame game){
        List<SmsGroupGame> SmsGroupGames = iSmsGroupGameService.getEnableGroupProduct(game);
        return SmsGroupGames;
    }

    /**
     * 获取团购商品详情
     * @param groupGameId
     * @param memberId
     * @return
     */
    public PmsGroupProductDetailPageInfo detail(Long groupGameId, Long memberId) {
        PmsGroupProductDetailPageInfo detailInfo = iSmsGroupGameService.getGroupProductDetailInfo(groupGameId);
        //判断是否为空
        Checker.check(ObjectUtils.isEmpty(detailInfo),"该团购商品已下架");
        //判断是否超时
        String endTime = LocalDateUtil.dateFormat(  detailInfo.getEndTime(), LocalDateUtil.DatePattern.yMd1.pattern() );
        String nowTime = LocalDateUtil.dateFormat( new Date(), LocalDateUtil.DatePattern.yMd1.pattern() );
        Checker.check( nowTime.compareTo( endTime ) > 0,"该团购商品已过时");
        //设置其他关注信息等
        cmsStarService.setDataIntoDetailPageInfo(detailInfo,memberId);
        return detailInfo;
    }
    /**
     * 获取当前商品团购记录
     * @param ids
     * @return
     */
    public List<SmsGroupGameRecordDetail> getGroupRecordList(List<Long> ids,int aging) {
        return iSmsGroupGameRecordService.getGroupRecordList(ids,aging);
    }

    /**
     * 获取用户团购记录详情
     * @param ids
     * @return
     */
    public List<SmsGroupGameRecordDetail> getMemberGroupRecord(List<Long> ids) {
        return iSmsGroupGameRecordService.getMemberGroupRecord( ids);
    }
    /**
     * 获取当前商品团购记录
     * @param groupGameId
     * @return
     */
    public List<Long> getGroupRecordListBase(Long groupGameId,Long memberId) {
        return iSmsGroupGameRecordService.getGroupRecordListBase(groupGameId, memberId);
    }

    /**
     * 获取用户团购记录详情
     * @param memberId
     * @return
     */
    public List<Long> getMemberGroupRecordBase(Long memberId) {
        return iSmsGroupGameRecordService.getMemberGroupRecordBase( memberId);
    }
}
