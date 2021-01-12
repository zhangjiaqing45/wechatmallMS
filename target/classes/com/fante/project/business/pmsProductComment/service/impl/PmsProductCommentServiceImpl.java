package com.fante.project.business.pmsProductComment.service.impl;

import java.util.List;

import com.fante.common.business.enums.CommonUse;
import com.fante.common.business.enums.OrderConst;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.constant.Constants;
import com.fante.common.utils.StringUtils;
import com.fante.project.business.omsOrderItem.service.IOmsOrderItemService;
import com.fante.project.business.pmsProduct.domain.PmsProduct;
import com.fante.project.business.pmsProduct.service.IPmsProductService;
import com.fante.project.business.pmsSkuStock.service.IPmsSkuStockService;
import com.fante.project.business.umsMember.domain.UmsMember;
import com.fante.project.business.umsMember.service.IUmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.pmsProductComment.mapper.PmsProductCommentMapper;
import com.fante.project.business.pmsProductComment.domain.PmsProductComment;
import com.fante.project.business.pmsProductComment.service.IPmsProductCommentService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 商品评价Service业务层处理
 *
 * @author fante
 * @date 2020-03-19
 */
@Service
public class PmsProductCommentServiceImpl implements IPmsProductCommentService {

    private static Logger log = LoggerFactory.getLogger(PmsProductCommentServiceImpl.class);

    @Autowired
    private PmsProductCommentMapper pmsProductCommentMapper;


    /**
     * 查询商品评价
     *
     * @param id 商品评价ID
     * @return 商品评价
     */
    @Override
    public PmsProductComment selectPmsProductCommentById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return pmsProductCommentMapper.selectPmsProductCommentById(id);
    }

    /**
     * 查询商品评价列表
     *
     * @param pmsProductComment 商品评价
     * @return 商品评价集合
     */
    @Override
    public List<PmsProductComment> selectPmsProductCommentList(PmsProductComment pmsProductComment) {
        return pmsProductCommentMapper.getPmsProductCommentList(pmsProductComment);
    }

    /**
     * 查询商品评价数量
     *
     * @param pmsProductComment 商品评价
     * @return 结果
     */
    @Override
    public int countPmsProductComment(PmsProductComment pmsProductComment){
        return pmsProductCommentMapper.countPmsProductComment(pmsProductComment);
    }

    /**
     * 唯一校验
     *
     * @param pmsProductComment 商品评价
     * @return 结果
     */
    @Override
    public String checkPmsProductCommentUnique(PmsProductComment pmsProductComment) {
        return pmsProductCommentMapper.checkPmsProductCommentUnique(pmsProductComment) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增商品评价
     *
     * @param pmsProductComment 商品评价
     * @return 新增商品评价数量
     */
    @Override
    public int insertPmsProductComment(PmsProductComment pmsProductComment) {
        validateField(pmsProductComment);
        //设置评论时间
        pmsProductComment.setCreateTime(DateUtils.getNowDate());
        return pmsProductCommentMapper.insertPmsProductComment(pmsProductComment);
    }

    /**
     * 验证必要参数
     * @param comment
     */
    private void validateField(PmsProductComment comment){
        Checker.check(ObjectUtils.isEmpty(comment.getMemberId()),"用户信息缺失");
        Checker.check(ObjectUtils.isEmpty(comment.getProductId()),"商品信息缺失");
        Checker.check(ObjectUtils.isEmpty(comment.getProductSkuId()),"商品信息缺失");
        Checker.check(StringUtils.isEmpty(comment.getContent()),"评论信息缺失");
    }


    /**
     * 修改商品评价
     *
     * @param pmsProductComment 商品评价
     * @return 修改商品评价数量
     */
    @Override
    public int updatePmsProductComment(PmsProductComment pmsProductComment) {
        pmsProductComment.setUpdateTime(DateUtils.getNowDate());
        return pmsProductCommentMapper.updatePmsProductComment(pmsProductComment);
    }

    /**
     * 真实删除商品评价对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除商品评价数量
     */
    @Override
    public int deletePmsProductCommentByIds(String ids) {
        return pmsProductCommentMapper.deleteCommentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商品评价信息
     *
     * @param id 商品评价ID
     * @return 删除商品评价数量
     */
    @Override
    public int deletePmsProductCommentById(Long id) {
        return pmsProductCommentMapper.deletePmsProductCommentById(id);
    }
    /**
     * 审核通过
     * @param ids
     * @return
     */
    @Override
    public int pass(String ids) {
        return pmsProductCommentMapper.pass(Convert.toStrArray(ids));
    }
    /**
     * 审核拒绝
     * @param ids
     * @return
     */
    @Override
    public int refuse(String ids) {
        return pmsProductCommentMapper.refuse(Convert.toStrArray(ids));
    }
    /**
     * (app)获取商品评论
     * @param id
     * @return
     */
    @Override
    public List<PmsProductComment> getCommentListForApp(Long id) {
        Checker.check(ObjectUtils.isEmpty(id),"缺少商品信息参数！");
        PmsProductComment comment = new PmsProductComment();
        comment.setProductId(id);
        //状态:展示
        comment.setShowStatus(Integer.valueOf(CommonUse.Status.ENABLE.getType()));
        return pmsProductCommentMapper.getCommentListForApp(comment);
    }
    /**
     * 获取商品评论信息通过 订单详情
     * @param orderItemId
     * @return
     */
    @Override
    public PmsProductComment getCommentInfoByOrder(Long orderItemId) {
        return pmsProductCommentMapper.getCommentInfoByOrder(orderItemId);
    }
}
