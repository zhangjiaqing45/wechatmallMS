package com.fante.project.business.bizShopInfo.utils;

import com.fante.common.business.constant.BizConstants;
import com.fante.common.utils.idgen.IdGenerator;
import com.fante.framework.redis.RedisUtil;
import com.fante.project.api.utils.OrderRedis;
import com.fante.project.business.smsCoupon.domain.SmsCoupon;
import com.fante.project.system.config.service.IConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @program: Fante
 * @Date: 2020/5/11 11:57
 * @Author: Mr.Z
 * @Description:
 */
@Component
public class ShopRedis {
    private static Logger log = LoggerFactory.getLogger(OrderRedis.class);

    @Autowired
    RedisUtil redisUtil;
    @Autowired
    private IConfigService configService;


    /**
     * 设置店铺邀请链接
     * @return
     */
    public String setInviteEntrance() {
        String key = String.valueOf(IdGenerator.nextId());
        Long expire = Long.valueOf(configService.selectConfigByKey(BizConstants.redis.SHOP_INVITE_ENTRANCE_EXPIRE));
        redisUtil.set(getInviteEntranceKey(key), key, expire);
        return key;
    }

    /**
     * 获取店铺邀请链接
     * @return
     */
    public String getInviteEntrance(String key) {
        Object obj = redisUtil.get(getInviteEntranceKey(key));
        return ObjectUtils.isEmpty(obj) ? null : String.valueOf(obj);
    }

    /**
     * 删除店铺邀请链接
     * @return
     */
    public void delInviteEntrance(String key) {
        redisUtil.del(getInviteEntranceKey(key));
    }

    /**
     * 获取店铺邀请链接KEY
     * @return
     */
    private String getInviteEntranceKey(String key) {
        return BizConstants.redis.SHOP_INVITE_ENTRANCE_KEY + key;
    }
    /*-------------------------------------------------------------------------------------------------*/
    /**
     * 设置发放优惠券提示标记
     * @return
     */
    public boolean setOfferCoupons(String memberId, List<SmsCoupon> coupons) {
        return redisUtil.set(getCouponHintKey(memberId), coupons, BizConstants.redis.FIRST_SUBSCRIBE_ENTRANCE_EXPIRE);
    }

    /**
     * 判断发放优惠券提示标记
     * @return
     */
    public List<SmsCoupon> getOfferCoupons(String mebmerId) {
        Object obj = redisUtil.get(getCouponHintKey(mebmerId));
        return ObjectUtils.isEmpty(obj) ? null : (List<SmsCoupon>)obj;
    }

    /**
     * 删除发放优惠券提示标记
     * @return
     */
    public void delCouponHintFlag(String mebmerId) {
        redisUtil.del(getCouponHintKey(mebmerId));
    }

    /**
     * 获取店铺邀请链接KEY
     * @return
     */
    private String getCouponHintKey(String memberId) {
        return BizConstants.redis.FIRST_SUBSCRIBE_ENTRANCE_KEY + memberId;
    }



}
