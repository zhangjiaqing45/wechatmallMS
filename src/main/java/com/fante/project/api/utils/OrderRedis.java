package com.fante.project.api.utils;

import com.fante.common.business.enums.OrderConst;
import com.fante.common.utils.Checker;
import com.fante.framework.redis.RedisUtil;
import com.fante.project.api.mq.resp.OrderHandleResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wz
 * @Description OrderRedis
 * @CreatTime 2020/4/30
 */
@Component
public class OrderRedis {
    private static Logger log = LoggerFactory.getLogger(OrderRedis.class);
    @Autowired
    RedisUtil redisUtil;

    /**************************************** 处理订单 -- 开始 *****************************************/

    /**
     * 获取用户订单处理信息
     *
     * @param memberId
     * @return
     */
    public OrderHandleResult getOrderStatusRedis(Long memberId) {
        String redisKey = (OrderConst.REDIS_ORDER_PREFIX + memberId);
        Object obj = redisUtil.get(redisKey);
        return obj == null ? null : (OrderHandleResult) obj;
    }

    /**
     * 设置用户订单处理信息:处理中
     *
     * @param memberId
     * @param result
     * @return
     */
    public void setOrderRedis(Long memberId, OrderHandleResult result) {
        boolean set = redisUtil.set(OrderConst.REDIS_ORDER_PREFIX + memberId, result, OrderConst.REDIS_ORDER_LIVE_TIME);
        log.info("redis设置订单信息失败:用户id{},设置状态码:{}", memberId, result == null ? "NULL" : result.getCode());
        Checker.checkOp(set, "订单处理失败");
    }


    /**************************************** 处理订单 -- 结束 *****************************************/
}
