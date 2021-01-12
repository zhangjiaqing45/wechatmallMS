package com.fante.project.business.umsDistribution.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import com.fante.common.business.constant.BizConstants;
import com.fante.common.business.enums.OrderConst;
import com.fante.common.business.enums.UmsMemberConst;
import com.fante.common.constant.Constants;
import com.fante.common.utils.Arith;
import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.bean.BeanUtils;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.project.api.omsOrderProcess.domain.OmsOrderCommissionDTO;
import com.fante.project.business.pmsProduct.service.IPmsProductService;
import com.fante.project.business.umsDistribution.domain.UmsDistributionResult;
import org.apache.poi.ss.formula.functions.Now;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.umsDistribution.mapper.UmsDistributionMapper;
import com.fante.project.business.umsDistribution.domain.UmsDistribution;
import com.fante.project.business.umsDistribution.service.IUmsDistributionService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 分销比例商品角色关系Service业务层处理
 *
 * @author fante
 * @date 2020-04-30
 */
@Service
public class UmsDistributionServiceImpl implements IUmsDistributionService {

    private static Logger log = LoggerFactory.getLogger( UmsDistributionServiceImpl.class );

    @Autowired
    private UmsDistributionMapper umsDistributionMapper;

    @Autowired
    private IPmsProductService iPmsProductService;

    /**
     * 查询分销比例商品角色关系
     *
     * @param id 分销比例商品角色关系ID
     * @return 分销比例商品角色关系
     */
    @Override
    public UmsDistribution selectUmsDistributionById(Long id) {
        if (ObjectUtils.isEmpty( id )) {
            return null;
        }
        return umsDistributionMapper.selectUmsDistributionById( id );
    }

    /**
     * 查询分销比例商品角色关系列表
     *
     * @param umsDistribution 分销比例商品角色关系
     * @return 分销比例商品角色关系集合
     */
    @Override
    public List<UmsDistribution> selectUmsDistributionList(UmsDistribution umsDistribution) {
        return umsDistributionMapper.selectUmsDistributionList( umsDistribution );
    }

    /**
     * 查询分销比例商品角色关系数量
     *
     * @param umsDistribution 分销比例商品角色关系
     * @return 结果
     */
    @Override
    public int countUmsDistribution(UmsDistribution umsDistribution) {
        return umsDistributionMapper.countUmsDistribution( umsDistribution );
    }

    /**
     * 唯一校验
     *
     * @param umsDistribution 分销比例商品角色关系
     * @return 结果
     */
    @Override
    public String checkUmsDistributionUnique(UmsDistribution umsDistribution) {
        return umsDistributionMapper.checkUmsDistributionUnique( umsDistribution ) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增分销比例商品角色关系
     *
     * @param umsDistribution 分销比例商品角色关系
     * @return 新增分销比例商品角色关系数量
     */
    @Override
    public int insertUmsDistribution(UmsDistribution umsDistribution) {
        return umsDistributionMapper.insertUmsDistribution( umsDistribution );
    }

    /**
     * 修改分销比例商品角色关系
     *
     * @param umsDistribution 分销比例商品角色关系
     * @return 修改分销比例商品角色关系数量
     */
    @Override
    public int updateUmsDistribution(UmsDistribution umsDistribution) {
        //验证 参数
        Checker.check(ObjectUtils.isEmpty(umsDistribution.getProductId()),"商品参数缺失");
        Checker.check(ObjectUtils.isEmpty( umsDistribution.getRatio()),"分销比例参数缺失");
        //计算并验证比例
        BigDecimal ratio = umsDistribution.getRatio().divide(new BigDecimal(100));
        boolean i = ratio.compareTo(BigDecimal.ZERO)>=0;
        boolean j = ratio.compareTo(BigDecimal.ONE)<0;
        Checker.checkOp(i && j ,"修改比例的值必须在0-99之间");
        umsDistribution.setRatio(ratio);
        boolean isDistribution = UmsMemberConst.RoleType.isDistribution(umsDistribution.getRoleType());
        Checker.checkOp(isDistribution,"分销角色类型错误");
        Long id = umsDistribution.getId();
        //如果为空则插入否则更新
        if(ObjectUtils.isEmpty(id)){
            umsDistribution.setCreateTime(new Date());
            umsDistribution.setCreateBy(ShiroUtils.getLoginName());
            return umsDistributionMapper.insertUmsDistribution(umsDistribution);
        }
        umsDistribution.setUpdateTime(new Date());
        umsDistribution.setUpdateBy(ShiroUtils.getLoginName());
        return umsDistributionMapper.updateUmsDistribution( umsDistribution );
    }

    /**
     * 删除分销比例商品角色关系对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除分销比例商品角色关系数量
     */
    @Override
    public int deleteUmsDistributionByIds(String ids) {
        return umsDistributionMapper.deleteUmsDistributionByIds( Convert.toStrArray( ids ) );
    }

    /**
     * 删除分销比例商品角色关系信息
     *
     * @param id 分销比例商品角色关系ID
     * @return 删除分销比例商品角色关系数量
     */
    @Override
    public int deleteUmsDistributionById(Long id) {
        return umsDistributionMapper.deleteUmsDistributionById( id );
    }

    /**
     * 根据商品id查询该商品sku 到各个分销金
     *
     * @param id
     * @return
     */
    @Override
    public List<UmsDistributionResult> selectUmsDistributionByProudctId(Long id) {
        //查询待分销信息
        List<UmsDistributionResult> infoList = umsDistributionMapper.selectUmsDistributionByProudctId( id, ShiroUtils.getSysUser().getDeptId() );

        //获取带分销商品中所有类型->去重->从小到大排序->获取set集合
        Set<String> roleTypeSet = infoList.stream()
                .map( item -> {
                    String roleType = item.getRoleType();
                    //设置角色名称
                    item.setRoleName( UmsMemberConst.RoleType.get( roleType ).getDescribe() );
                    return roleType;
                } )
                .collect( Collectors.toSet() );
        roleTypeSet.add( UmsMemberConst.RoleType.GENERAL.getType() );

        //查询商品信息
        UmsDistributionResult initInfo = new UmsDistributionResult();
        AtomicBoolean flag = new AtomicBoolean( true );
        //获取还未设置的分销角色 进行初始化
        Arrays.stream( UmsMemberConst.RoleType.values() )
                .filter( em -> !(roleTypeSet.contains( em.getType()  )) )
                .forEach( em ->{
                    UmsDistributionResult cont = null;
                    if (flag.get()) {
                        cont = umsDistributionMapper.selectSkuInfoForDistribution( id, ShiroUtils.getSysUser().getDeptId() );
                        Checker.check( ObjectUtils.isEmpty( cont ), "该商品已删除或不存在" );
                        BeanUtils.copyProperties( cont, initInfo );
                        flag.set( false );
                    } else {
                        cont = new UmsDistributionResult();
                        BeanUtils.copyProperties( initInfo, cont );
                    }
                    cont.setRoleType( em.getType() );
                    cont.setRoleName( em.getDescribe() );
                    infoList.add( cont );
                });
        infoList.forEach(x->{
            x.getSkuList().forEach(y->{
                String spdata = y.getSpData().replaceAll(BizConstants.regexp.REGEXP_SP_DATA_TRIM, "");
                y.setSpData(spdata);
            });
        });
        return infoList;
    }
    /**
     * 根据获取分销金额
     * @param itemIds
     * @param memberId
     * @return
     */
    @Override
    public List<OmsOrderCommissionDTO>  getCommissionByOrderItemIds(List<Long> itemIds, Long memberId) {
        return umsDistributionMapper.getCommissionByOrderItemIds(itemIds,memberId);
    }
    /**
     * 根据订单详情获取单个分销金额
     * @param orderItemId
     * @param memberId
     * @return
     */
    @Override
    public BigDecimal getCommissionByOrderItem( Long orderItemId, Long memberId) {
        return umsDistributionMapper.getCommissionByOrderItem(orderItemId,memberId);
    }
}
