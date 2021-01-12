package com.fante.project.business.pmsProductComment.service;

import com.fante.project.business.pmsProductComment.domain.PmsProductComment;
import java.util.List;

/**
 * 商品评价Service接口
 *
 * @author fante
 * @date 2020-03-19
 */
public interface IPmsProductCommentService {
    /**
     * 查询商品评价
     *
     * @param id 商品评价ID
     * @return 商品评价
     */
    public PmsProductComment selectPmsProductCommentById(Long id);

    /**
     * 查询商品评价列表
     *
     * @param pmsProductComment 商品评价
     * @return 商品评价集合
     */
    public List<PmsProductComment> selectPmsProductCommentList(PmsProductComment pmsProductComment);

    /**
     * 查询商品评价数量
     *
     * @param pmsProductComment 商品评价
     * @return 结果
     */
    public int countPmsProductComment(PmsProductComment pmsProductComment);

    /**
     * 唯一校验
     *
     * @param pmsProductComment 商品评价
     * @return 结果
     */
    public String checkPmsProductCommentUnique(PmsProductComment pmsProductComment);

    /**
     * 新增商品评价
     *
     * @param pmsProductComment 商品评价
     * @return 新增商品评价数量
     */
    public int insertPmsProductComment(PmsProductComment pmsProductComment);

    /**
     * 修改商品评价
     *
     * @param pmsProductComment 商品评价
     * @return 修改商品评价数量
     */
    public int updatePmsProductComment(PmsProductComment pmsProductComment);

    /**
     * 批量删除商品评价
     *
     * @param ids 需要删除的数据ID
     * @return 删除商品评价数量
     */
    public int deletePmsProductCommentByIds(String ids);

    /**
     * 删除商品评价信息
     *
     * @param id 商品评价ID
     * @return 删除商品评价数量
     */
    public int deletePmsProductCommentById(Long id);

    /**
     * 审核通过
     * @param ids
     * @return
     */
    int pass(String ids);

    /**
     * 审核拒绝
     * @param ids
     * @return
     */
    int refuse(String ids);

    /**
     * 获取商品评论
     * @param id
     * @return
     */
    List<PmsProductComment> getCommentListForApp(Long id);
    /**
     * 获取商品评论信息通过 订单详情
     * @param orderItemId
     * @return
     */
    PmsProductComment getCommentInfoByOrder(Long orderItemId);
}
