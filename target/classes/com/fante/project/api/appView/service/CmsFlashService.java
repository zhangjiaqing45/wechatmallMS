package com.fante.project.api.appView.service;

import com.fante.common.business.enums.SmsFlashConst;
import com.fante.common.utils.Checker;
import com.fante.common.utils.LocalDateUtil;
import com.fante.common.utils.StringUtils;
import com.fante.project.api.appView.domain.FlashProductParam;
import com.fante.project.api.appView.domain.FlashSessionTimeRsp;
import com.fante.project.api.appView.domain.PmsFlashProductDetailPageInfo;
import com.fante.project.business.pmsProduct.service.IPmsProductService;
import com.fante.project.business.smsFlashPromotion.service.ISmsFlashPromotionService;
import com.fante.project.business.smsFlashPromotionProduct.domain.SmsFlashPromotionProductDTO;
import com.fante.project.business.smsFlashPromotionProduct.service.ISmsFlashPromotionProductService;
import com.fante.project.business.smsFlashPromotionSession.domain.SmsFlashPromotionSession;
import com.fante.project.business.smsFlashPromotionSession.service.ISmsFlashPromotionSessionService;
import com.fante.project.business.smsFlashPromotionSku.service.ISmsFlashPromotionSkuService;
import jdk.nashorn.internal.ir.ReturnNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by ftnet on 2020/5/1
 */
@Service
public class CmsFlashService {
    private static Logger log = LoggerFactory.getLogger( CmsFlashService.class);
    /**
     * 时间段服务接口
     */
    @Autowired
    private ISmsFlashPromotionSessionService iSmsFlashPromotionSessionService;
    /**
     * 秒杀活动 和sku关系表服务接口
     */
    @Autowired
    private ISmsFlashPromotionSkuService iSmsFlashPromotionSkuService;
    /**
     * 秒杀事件服务接口
     */
    @Autowired
    private ISmsFlashPromotionService iSmsFlashPromotionService;
    /**
     * 秒杀事件 和 商品关系 的 服务接口
     */
    @Autowired
    private ISmsFlashPromotionProductService iSmsFlashPromotionProductService;
    /**
     * 收藏 服务接口
     */
    @Autowired
    private CmsStarService cmsStarService;
    /**
     * 商品
     */
    @Autowired
    private IPmsProductService iPmsProductService;

    /**
     * 获取可用秒杀事件段
     */
    public List<SmsFlashPromotionSession> getEnableFlashSession(){
        List<SmsFlashPromotionSession> sessions = iSmsFlashPromotionSessionService.selectAvailableSessionList();
        Checker.check( ObjectUtils.isEmpty( sessions ),"秒杀活动暂未开始" );
        return sessions;
    }
    /**
     * 获取 离当前时间最近的 秒杀时间段：
     * 说明：1.如果当前时间在所有时间段之前，则获取即将开启的第一个活动的时间段
     *      2.如果当前时间在所有时间段之间:
     *          a.当前时间正好在其中一个时间段，返回该时间段
     *          b.当前时间正好不在任何一个时间段中，获取离当前时间段最近的下一个时间段
     *      3.如果当前时间在所有时间段之后：则获取所有时间段中的第一个时间段
     */
    public FlashSessionTimeRsp getFlashSessionTimeNow( List<SmsFlashPromotionSession> sessionTimes){
        //排序
        List<SmsFlashPromotionSession> sessions = sessionTimes.stream()
                //按照 开始时间 从小到大排序
                .sorted( Comparator.comparing(SmsFlashPromotionSession::getStartTime))
                .collect( Collectors.toList());

        int size = sessions.size();
        for (int i = 0; i < size; i++) {
            SmsFlashPromotionSession session = sessions.get( i );

            if (i == 0 && StringUtils.equals(
                    judgeSessionTimeOfNow( session ),
                    SmsFlashConst.StatusEnum.AWAIT.getType()
                )) {
                //当 当前时间按小于 最小开始时间
                return new FlashSessionTimeRsp(session,SmsFlashConst.StatusEnum.AWAIT.getType());
            }else if (i == (size-1) && StringUtils.equals(
                    judgeSessionTimeOfNow( session ),
                    SmsFlashConst.StatusEnum.SOLDOUT.getType()
            )) {
                //当 当前时间按大于 最大结束时间
                return new FlashSessionTimeRsp(sessions.get( 0 ),SmsFlashConst.StatusEnum.SOLDOUT.getType());
            }else{
                String compare = judgeSessionTimeOfNow( session );
                if(SmsFlashConst.StatusEnum.PUTAWAY.getType().equals( compare )){
                    //当 当前时间在开始和结束时间之间
                    return new FlashSessionTimeRsp(sessions.get( i ),SmsFlashConst.StatusEnum.PUTAWAY.getType());
                }else if(SmsFlashConst.StatusEnum.AWAIT.getType().equals( compare )){
                    //当 当前时间小于 开始时间
                    return new FlashSessionTimeRsp(sessions.get( i ),SmsFlashConst.StatusEnum.AWAIT.getType());
                }
            }
        }
        return null;
    }
    /**
     * 该时间段与当前时间对比判断该时间段活动是否开始：
     * 还未开始： 0
     * 已经过时： 2
     * 正在进行： 1
     */
    private String  judgeSessionTimeOfNow(SmsFlashPromotionSession sessionTime){
        String startTime = sessionTime.getStartTime();
        String endTime = sessionTime.getEndTime();
        String nowTime = LocalDateUtil.dateFormat( new Date(), LocalDateUtil.DatePattern.Hms1.pattern() );
        //比较时间
        String timeStatus;
        if(nowTime.compareTo( startTime )<0){
            //选择的活动时间段 还未开始 例如： (now) 08:00:00  (session)09:00:00 ~ 10:00:00
            timeStatus = SmsFlashConst.StatusEnum.AWAIT.getType();
        }else if(nowTime.compareTo( endTime )>0){
            //选择的活动时间段 已经过时 例如： (now) 11:00:00  (session)09:00:00 ~ 10:00:00
            timeStatus = SmsFlashConst.StatusEnum.SOLDOUT.getType();
        }else{
            //选择的活动时间段 正在进行 例如： (now) 09:30:00  (session)09:00:00 ~ 10:00:00
            timeStatus = SmsFlashConst.StatusEnum.PUTAWAY.getType();
        }
        return timeStatus;
    }

    /**
     * 获取 选择的时间段 的所有可用商品
     * flashPromotionSessionId:时间段id必填
     */
    public List<SmsFlashPromotionProductDTO> getFlashProductList(FlashProductParam param){
        return iSmsFlashPromotionProductService.getFlashProductList(param);
    }

    /**
     * 根据 sessionId 判断 该时间段与当前时间对比判断该时间段活动是否开始：
     * 还未开始： 0
     * 已经过时： 2
     * 正在进行： 1
     */
    public String getSessionTimeStatus(Long sessionId){
        SmsFlashPromotionSession sessionTime = iSmsFlashPromotionSessionService.selectSmsFlashPromotionSessionById( sessionId );
        Checker.check( ObjectUtils.isEmpty( sessionTime ),"该时间段暂无开放秒杀活动" );
        return judgeSessionTimeOfNow(sessionTime);
    }

    /**
     * (app)获取秒杀商品详情
     * @param productId
     * @return
     */
    public PmsFlashProductDetailPageInfo detail(Long productId, Long memberId) {
        //获取可用秒杀商品信息
        PmsFlashProductDetailPageInfo detailInfo = iSmsFlashPromotionProductService.getFlashProductDetailInfo(productId);
        //判断是否为空
        Checker.check(ObjectUtils.isEmpty(detailInfo),"该秒杀商品已下架");
        //判断是否超时
        String startTime = detailInfo.getStartTime();
        String endTime = detailInfo.getEndTime();
        String nowTime = LocalDateUtil.dateFormat( new Date(), LocalDateUtil.DatePattern.Hms1.pattern() );
        Checker.check(nowTime.compareTo( startTime ) <= 0 || nowTime.compareTo( endTime ) >= 0,"该秒杀商品已过时");
        //设置其他关注信息等
        cmsStarService.setDataIntoDetailPageInfo(detailInfo,memberId);

        /*Long shopId = detailInfo.getShopId();
        //查询所有关注店铺的人数
        detailInfo.setShopStarCount(cmsStarService.countStarOfShop(shopId));
        //查询当前用户是否关注店铺
        detailInfo.setShopStar(cmsStarService.isStarOfShop(shopId,memberId));
        //查询当前用户是否关注商品
        detailInfo.setProductStar(cmsStarService.isStarOfProduct(productId,memberId));
        //查询店铺下的商品数量
        PmsProduct product = new PmsProduct();
        product.setShopId(shopId);
        product.setPublishStatus(ProductAttributeCategoryConst.publicStatusEnum.PUTAWAY.getType());
        int productCount = iPmsProductService.countPmsProduct(product);
        detailInfo.setShopProductCount(productCount);*/

        return  detailInfo;
    }
}
