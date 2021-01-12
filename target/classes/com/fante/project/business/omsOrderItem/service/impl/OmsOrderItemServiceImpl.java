package com.fante.project.business.omsOrderItem.service.impl;

import java.util.List;
import com.fante.common.utils.DateUtils;
import com.fante.common.constant.Constants;
import com.fante.project.api.omsOrderProcess.domain.OmsMemberOrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.omsOrderItem.mapper.OmsOrderItemMapper;
import com.fante.project.business.omsOrderItem.domain.OmsOrderItem;
import com.fante.project.business.omsOrderItem.service.IOmsOrderItemService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 订单中所包含的商品Service业务层处理
 *
 * @author fante
 * @date 2020-04-01
 */
@Service
public class OmsOrderItemServiceImpl implements IOmsOrderItemService {

    private static Logger log = LoggerFactory.getLogger(OmsOrderItemServiceImpl.class);

    @Autowired
    private OmsOrderItemMapper omsOrderItemMapper;

    /**
     * 查询订单中所包含的商品
     *
     * @param id 订单中所包含的商品ID
     * @return 订单中所包含的商品
     */
    @Override
    public OmsOrderItem selectOmsOrderItemById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return omsOrderItemMapper.selectOmsOrderItemById(id);
    }

    /**
     * 查询订单中所包含的商品列表
     *
     * @param omsOrderItem 订单中所包含的商品
     * @return 订单中所包含的商品集合
     */
    @Override
    public List<OmsOrderItem> selectOmsOrderItemList(OmsOrderItem omsOrderItem) {
        return omsOrderItemMapper.selectOmsOrderItemList(omsOrderItem);
    }

    /**
     * 查询订单中所包含的商品数量
     *
     * @param omsOrderItem 订单中所包含的商品
     * @return 结果
     */
    @Override
    public int countOmsOrderItem(OmsOrderItem omsOrderItem){
        return omsOrderItemMapper.countOmsOrderItem(omsOrderItem);
    }

    /**
     * 唯一校验
     *
     * @param omsOrderItem 订单中所包含的商品
     * @return 结果
     */
    @Override
    public String checkOmsOrderItemUnique(OmsOrderItem omsOrderItem) {
        return omsOrderItemMapper.checkOmsOrderItemUnique(omsOrderItem) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增订单中所包含的商品
     *
     * @param omsOrderItem 订单中所包含的商品
     * @return 新增订单中所包含的商品数量
     */
    @Override
    public int insertOmsOrderItem(OmsOrderItem omsOrderItem) {
        omsOrderItem.setCreateTime(DateUtils.getNowDate());
        return omsOrderItemMapper.insertOmsOrderItem(omsOrderItem);
    }

    /**
     * 修改订单中所包含的商品
     *
     * @param omsOrderItem 订单中所包含的商品
     * @return 修改订单中所包含的商品数量
     */
    @Override
    public int updateOmsOrderItem(OmsOrderItem omsOrderItem) {
        omsOrderItem.setUpdateTime(DateUtils.getNowDate());
        return omsOrderItemMapper.updateOmsOrderItem(omsOrderItem);
    }

    /**
     * 删除订单中所包含的商品对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除订单中所包含的商品数量
     */
    @Override
    public int deleteOmsOrderItemByIds(String ids) {
        return omsOrderItemMapper.deleteOmsOrderItemByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除订单中所包含的商品信息
     *
     * @param id 订单中所包含的商品ID
     * @return 删除订单中所包含的商品数量
     */
    @Override
    public int deleteOmsOrderItemById(Long id) {
        return omsOrderItemMapper.deleteOmsOrderItemById(id);
    }
    /**
     * 批量插入
     * @param orderItemList
     * @return
     */
    @Override
    public int batchInsert(List<OmsOrderItem> orderItemList) {
        return omsOrderItemMapper.batchInsert(orderItemList);
    }
    /**
     * (app) 查询订单详情 和 订单信息
     * @param orderItemId
     * @param memberId
     * @return
     */
    @Override
    public OmsMemberOrderDetail getOrderDetailForReturnByOrderItemId(Long orderItemId,Long memberId) {
        return omsOrderItemMapper.getOrderDetailForReturnByOrderItemId(orderItemId,memberId);
    }
}
