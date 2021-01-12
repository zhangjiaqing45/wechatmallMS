package com.fante.project.business.pmsProductComment.mapper;

import com.fante.project.business.pmsProductComment.domain.PmsProductComment;
import com.fante.project.mapperBase.PmsProductCommentMapperBase;

import java.util.List;

/**
 * 商品评价Mapper扩展接口
 *
 * @author fante
 * @date 2020-03-19
 */

public interface PmsProductCommentMapper extends PmsProductCommentMapperBase {
    /**
     * 真实删除
     * @param ids
     * @return
     */
    int deleteCommentByIds(String[] ids);
    /**
     * 审核通过
     * @param ids
     * @return
     */
    int pass(String[] ids);
    /**
     * 审核拒绝
     * @param ids
     * @return
     */
    int refuse(String[] ids);
    /**
     * 查询商品评价列表
     * 支持时间单方向查询
     * @param pmsProductComment 商品评价
     * @return 商品评价集合
     */
    public List<PmsProductComment> getPmsProductCommentList(PmsProductComment pmsProductComment);
    /**
     * (app)获取商品评论
     * @param pmsProductComment
     * @return
     */
    List<PmsProductComment> getCommentListForApp(PmsProductComment pmsProductComment);
    /**
     * 获取商品评论信息通过 订单详情
     * @param orderItemId
     * @return
     */
    PmsProductComment getCommentInfoByOrder(Long orderItemId);
}
