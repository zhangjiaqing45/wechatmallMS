package com.fante.project.business.umsMemberShopStar.service.impl;

import java.util.List;
import java.util.Objects;

import com.fante.common.business.enums.ProductAttributeCategoryConst;
import com.fante.common.constant.Constants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.project.business.bizShopInfo.domain.BizShopInfo;
import com.fante.project.business.bizShopInfo.service.IBizShopInfoService;
import com.fante.project.business.pmsProduct.domain.PmsProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.umsMemberShopStar.mapper.UmsMemberShopStarMapper;
import com.fante.project.business.umsMemberShopStar.domain.UmsMemberShopStar;
import com.fante.project.business.umsMemberShopStar.service.IUmsMemberShopStarService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 店铺收藏Service业务层处理
 *
 * @author fante
 * @date 2020-04-24
 */
@Service
public class UmsMemberShopStarServiceImpl implements IUmsMemberShopStarService {

    private static Logger log = LoggerFactory.getLogger(UmsMemberShopStarServiceImpl.class);

    @Autowired
    private UmsMemberShopStarMapper umsMemberShopStarMapper;
    /**
     * 店铺
     */
    @Autowired
    private IBizShopInfoService iBizShopInfoService;

    /**
     * 查询店铺收藏
     *
     * @param id 店铺收藏ID
     * @return 店铺收藏
     */
    @Override
    public UmsMemberShopStar selectUmsMemberShopStarById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return umsMemberShopStarMapper.selectUmsMemberShopStarById(id);
    }

    /**
     * 查询店铺收藏列表
     *
     * @param umsMemberShopStar 店铺收藏
     * @return 店铺收藏集合
     */
    @Override
    public List<UmsMemberShopStar> selectUmsMemberShopStarList(UmsMemberShopStar umsMemberShopStar) {
        return umsMemberShopStarMapper.selectUmsMemberShopStarList(umsMemberShopStar);
    }

    /**
     * 查询店铺收藏数量
     *
     * @param umsMemberShopStar 店铺收藏
     * @return 结果
     */
    @Override
    public int countUmsMemberShopStar(UmsMemberShopStar umsMemberShopStar){
        return umsMemberShopStarMapper.countUmsMemberShopStar(umsMemberShopStar);
    }

    /**
     * 唯一校验
     *
     * @param umsMemberShopStar 店铺收藏
     * @return 结果
     */
    @Override
    public String checkUmsMemberShopStarUnique(UmsMemberShopStar umsMemberShopStar) {
        return umsMemberShopStarMapper.checkUmsMemberShopStarUnique(umsMemberShopStar) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增店铺收藏
     *
     * @param umsMemberShopStar 店铺收藏
     * @return 新增店铺收藏数量
     */
    @Override
    public int insertUmsMemberShopStar(UmsMemberShopStar umsMemberShopStar) {
        //验证店铺是否可用
        BizShopInfo shopInfo = iBizShopInfoService.selectBizShopInfoById(umsMemberShopStar.getShopId());
        Checker.check(ObjectUtils.isEmpty(shopInfo),"该店铺不存在！");
        umsMemberShopStar.setStarTime(DateUtils.getNowDate());
        return umsMemberShopStarMapper.insertUmsMemberShopStar(umsMemberShopStar);
    }

    /**
     * 修改店铺收藏
     *
     * @param umsMemberShopStar 店铺收藏
     * @return 修改店铺收藏数量
     */
    @Override
    public int updateUmsMemberShopStar(UmsMemberShopStar umsMemberShopStar) {
        return umsMemberShopStarMapper.updateUmsMemberShopStar(umsMemberShopStar);
    }

    /**
     * 删除店铺收藏对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除店铺收藏数量
     */
    @Override
    public int deleteUmsMemberShopStarByIds(String ids) {
        return umsMemberShopStarMapper.deleteUmsMemberShopStarByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除店铺收藏信息
     *
     * @param id 店铺收藏ID
     * @return 删除店铺收藏数量
     */
    @Override
    public int deleteUmsMemberShopStarById(Long id,Long memberId) {
        return umsMemberShopStarMapper.memberDelShopStar(id,memberId);
    }


    /**
     * 获取会员所有收藏到店铺
     */
    @Override
    public List<BizShopInfo> getMemberShopStar(Long memberId){
        return umsMemberShopStarMapper.getMemberShopStar(memberId);
    }
}
