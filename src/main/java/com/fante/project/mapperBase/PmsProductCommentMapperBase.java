package com.fante.project.mapperBase;

import com.fante.project.business.pmsProductComment.domain.PmsProductComment;
import java.util.List;

/**
 * 商品评价Mapper基础接口
 *
 * @author fante
 * @date 2020-04-24
 */
public interface PmsProductCommentMapperBase {
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
    public int checkPmsProductCommentUnique(PmsProductComment pmsProductComment);

    /**
     * 新增商品评价
     *
     * @param pmsProductComment 商品评价
     * @return 结果
     */
    public int insertPmsProductComment(PmsProductComment pmsProductComment);

    /**
     * 修改商品评价
     *
     * @param pmsProductComment 商品评价
     * @return 结果
     */
    public int updatePmsProductComment(PmsProductComment pmsProductComment);

    /**
     * 删除商品评价
     *
     * @param id 商品评价ID
     * @return 结果
     */
    public int deletePmsProductCommentById(Long id);

    /**
     * 批量删除商品评价
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePmsProductCommentByIds(String[] ids);

}
