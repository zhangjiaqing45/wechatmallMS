package com.fante.project.business.smsGroupGame.service.impl;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import com.fante.common.business.enums.ProductAttributeCategoryConst;
import com.fante.common.business.enums.SmsGroupGameConst;
import com.fante.common.utils.Checker;
import com.fante.common.constant.Constants;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.project.api.appView.domain.PmsGroupProductDetailPageInfo;
import com.fante.project.business.pmsProduct.domain.PmsProduct;
import com.fante.project.business.pmsProduct.service.IPmsProductService;
import com.fante.project.business.pmsSkuStock.domain.PmsSkuStock;
import com.fante.project.business.pmsSkuStock.service.IPmsSkuStockService;
import com.fante.project.business.smsGroupGame.domain.SmsGroupGameBtnsResult;
import com.fante.project.business.smsGroupGame.domain.SmsGroupGameParam;
import com.fante.project.business.smsGroupGame.mapper.SmsGroupGameMapper;
import com.fante.project.business.smsGroupGameSku.domain.SmsGroupGameSku;
import com.fante.project.business.smsGroupGameSku.service.ISmsGroupGameSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.smsGroupGame.domain.SmsGroupGame;
import com.fante.project.business.smsGroupGame.service.ISmsGroupGameService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

import javax.xml.crypto.Data;

/**
 * 团购商品Service业务层处理
 *
 * @author fante
 * @date 2020-03-30
 */
@Service
public class SmsGroupGameServiceImpl implements ISmsGroupGameService {

    private static Logger log = LoggerFactory.getLogger(SmsGroupGameServiceImpl.class);

    @Autowired
    private SmsGroupGameMapper smsGroupGameMapper;
    /**商品sku*/
    @Autowired
    private IPmsSkuStockService iPmsSkuStockService;
    /**商品*/
    @Autowired
    private IPmsProductService iPmsProductService;
    /**团购商品sku*/
    @Autowired
    private ISmsGroupGameSkuService iSmsGroupGameSkuService;

    /**
     * 查询团购商品
     *
     * @param id 团购商品ID
     * @return 团购商品
     */
    @Override
    public SmsGroupGame selectSmsGroupGameById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return smsGroupGameMapper.selectSmsGroupGameById(id);
    }

    /**
     * 查询团购商品列表
     *
     * @param smsGroupGame 团购商品
     * @return 团购商品集合
     */
    @Override
    public List<SmsGroupGame> selectSmsGroupGameList(SmsGroupGame smsGroupGame) {
        return smsGroupGameMapper.selectSmsGroupGameList(smsGroupGame);
    }
    /**
     * 查询团购商品及下级关联sku的列表
     *
     * @param id 团购商品id
     * @return 团购商品集合
     */
    @Override
    public SmsGroupGameParam getSmsGroupGameDetailbyId(Long id){
        return smsGroupGameMapper.getSmsGroupGameDetailbyId(id);
    }
    /**
     * 查询团购商品列表
     *
     * @param smsGroupGame 团购商品
     * @return 团购商品集合
     */
    @Override
    public List<SmsGroupGameBtnsResult> selectSmsGroupGameBtnList(SmsGroupGame smsGroupGame) {
        return smsGroupGameMapper.getSmsGroupGameList(smsGroupGame);
    }

    /**
     * 查询团购商品数量
     *
     * @param smsGroupGame 团购商品
     * @return 结果
     */
    @Override
    public int countSmsGroupGame(SmsGroupGame smsGroupGame){
        return smsGroupGameMapper.countSmsGroupGame(smsGroupGame);
    }

    /**
     * 唯一校验
     *
     * @param smsGroupGame 团购商品
     * @return 结果
     */
    @Override
    public String checkSmsGroupGameUnique(SmsGroupGame smsGroupGame) {
        return smsGroupGameMapper.checkSmsGroupGameUnique(smsGroupGame) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增团购商品
     *
     * @param param 团购商品
     * @return 新增团购商品数量
     */
    @Override
    public int insertSmsGroupGame(SmsGroupGameParam param) {
        //创建人创建时间
        String loginName = ShiroUtils.getLoginName();
        Date nowDate = DateUtils.getNowDate();

        //团购商品关联的sku 过滤库存
        Checker.check(ObjectUtils.isEmpty(param.getSkus()),"团购有效商品规格不能为空。");
        List<SmsGroupGameSku> skus = param.getSkus().stream().filter(sku -> sku.getGroupStock() > 0).collect(Collectors.toList());
        Checker.check(ObjectUtils.isEmpty(skus),"团购有效商品规格不能为空。");

        Long productId = param.getProductId();
        //验证团购商品参数
        Checker.check(ObjectUtils.isEmpty(param.getAging()),"团购商品开团时效参数缺失。");
        Checker.check(DateUtils.addDays(nowDate,-1).after(Optional.ofNullable(param.getEndTime()).orElse(new Date(0))),"截至时间设置有误。");
        Checker.check(ObjectUtils.isEmpty(param.getTargetMemberCount()),"团购商品目标团购人数参数缺失。");
        Checker.checkOp(param.getTargetMemberCount()>= SmsGroupGameConst.GroupPersonCountLimit.MIN.getType()
                && param.getTargetMemberCount()<=SmsGroupGameConst.GroupPersonCountLimit.MAX.getType()
                ,"团购商品目标团购人数不符合条件。");
        PmsProduct pmsProduct = iPmsProductService.selectPmsProductById(productId);
        Checker.check(ObjectUtils.isEmpty(pmsProduct),"选则的团购商品不存在。");
        //设置团购商品参数
        param.setProductPic(pmsProduct.getPic());
        param.setProductName(pmsProduct.getName());
        param.setProductSn(pmsProduct.getProductSn());
        //店铺名称就不设置了，只设置店铺id
        param.setShopId(ShiroUtils.getSysUser().getDeptId());

        param.setCreateBy(loginName);
        param.setCreateTime(nowDate);
        //获取skus中的最小价格
        BigDecimal minprice = skus.stream().map(sku -> sku.getGroupPrice()).min(BigDecimal::compareTo).get();
        param.setMinPrice(minprice);
        int count = smsGroupGameMapper.insertSmsGroupGame(param);

        //验证和设置团购商品关联的sku信息
        Long ggid = param.getId();
        skus.stream().forEach(sku->{
            Checker.check(ObjectUtils.isEmpty(sku.getGroupPrice()),"团购商品价格参数缺失。");
            Checker.check(ObjectUtils.isEmpty(sku.getGroupStock()),"团购商品库存参数缺失。");
            PmsSkuStock pmsSkuStock = iPmsSkuStockService.selectPmsSkuStockById(sku.getSkuId());
            Checker.check(ObjectUtils.isEmpty(pmsSkuStock),"团购商品选择的规格不存在。");
            Checker.checkOp(Objects.equals(productId,pmsSkuStock.getProductId()),"商品规格与商品数据不匹配。");
            sku.setSkuPic(pmsSkuStock.getPic());
            sku.setSkuSpData(pmsSkuStock.getSpData());
            sku.setProductId(pmsSkuStock.getProductId());
            sku.setSkuCode(pmsSkuStock.getSkuCode());
            sku.setSkuPrice(pmsSkuStock.getPrice());
            sku.setGroupGameId(ggid);
            sku.setCreateBy(loginName);
            sku.setCreateTime(nowDate);
        });
        iSmsGroupGameSkuService.batchInsertGroupGameSku(skus);
        return count;
    }

    /**
     * 修改团购商品
     * 1.团购参数直接根据 团购活动id 修改
     * 2.团购活动skus的信息编辑:
     *      1.先过滤有效skus
     *      2.a.再判断 有id 的修改  b.无id的插入 c.原先有id的编辑的时候没了则删除
     * @param param 团购商品
     * @return 修改团购商品数量
     */
    @Override
    public int updateSmsGroupGame(SmsGroupGameParam param) {
        //更新人和时间
        String loginName = ShiroUtils.getLoginName();
        Date nowDate = DateUtils.getNowDate();
        //声明更新数据
        SmsGroupGame updateData = new SmsGroupGame();
        //团购商品关联的sku 过滤库存
        Checker.check(ObjectUtils.isEmpty(param.getSkus()),"团购有效商品规格不能为空。");
        List<SmsGroupGameSku> skus = param.getSkus().stream().filter(sku -> sku.getGroupStock() > 0).collect(Collectors.toList());
        Checker.check(ObjectUtils.isEmpty(skus),"团购有效商品规格不能为空。");

        //团购商品验证参数
        Long id = param.getId();
        SmsGroupGameParam oldGameDetail = validateAction(id, SmsGroupGameConst.GroupActionEnum.EDIT);
        Checker.check(ObjectUtils.isEmpty(oldGameDetail),"该团购活动不存在.");
        Checker.check(ObjectUtils.isEmpty(param.getAging()),"团购商品开团时效参数缺失。");
        Checker.check(DateUtils.addDays(nowDate,-1).after(Optional.ofNullable(param.getEndTime()).orElse(new Date(0))),"截至时间设置有误。");
        Checker.check(ObjectUtils.isEmpty(param.getTargetMemberCount()),"团购商品目标团购人数参数缺失。");
        Checker.checkOp(param.getTargetMemberCount()>= SmsGroupGameConst.GroupPersonCountLimit.MIN.getType()
                        && param.getTargetMemberCount()<=SmsGroupGameConst.GroupPersonCountLimit.MAX.getType()
                ,"团购商品目标团购人数不符合条件。");

        //设置要更新的数据
        updateData.setId(id);
        updateData.setAging(param.getAging());
        updateData.setVirtualCount(param.getVirtualCount());
        updateData.setEndTime(param.getEndTime());
        updateData.setSort(param.getSort());
        updateData.setRemark(param.getRemark());
        updateData.setTargetMemberCount(param.getTargetMemberCount());

        //获取skus中的最小价格
        BigDecimal minprice = skus.stream().map(sku -> sku.getGroupPrice()).min(BigDecimal::compareTo).get();
        updateData.setMinPrice(minprice);

        updateData.setUpdateBy(loginName);
        updateData.setUpdateTime(nowDate);
        int count = smsGroupGameMapper.updateSmsGroupGame(updateData);
        batchUpdateGroupGameSku(skus,oldGameDetail.getSkus());
        return count;
    }

    /**
     * 团购活动skus的信息编辑:
     *   1.再判断 有id 的修改  
     *   2.无id的插入 
     *   3.原先有id的编辑的时候没了则删除
     */
    private void batchUpdateGroupGameSku(List<SmsGroupGameSku> newSkus,List<SmsGroupGameSku> oldSkus){
        //newSkus已经过滤不需要再非空验证

        //通用参数
        String loginName = ShiroUtils.getLoginName();
        Date nowDate = DateUtils.getNowDate();
        
        //获取新增sku信息
        List<SmsGroupGameSku> insertSkuList = newSkus.stream()
                .filter(item->item.getId()==null)
                .collect(Collectors.toList());

        //获取需要更新的sku信息
        List<SmsGroupGameSku> updateSkuList = newSkus.stream()
                .filter(item->item.getId()!=null)

                .collect(Collectors.toList());

        //获取需要更新的List<skuId>
        List<Long> updateSkuIds = updateSkuList.stream()
                .map(SmsGroupGameSku::getId)
                .collect(Collectors.toList());

        //获取需要删除的sku信息
        String[] removeSkuIds = oldSkus.stream()
                .filter(item-> !updateSkuIds.contains(item.getId()))
                .map(sku->(sku.getId()).toString())
                .toArray(String[] :: new);
        //获取商品id
        Long productId = oldSkus.get(0).getProductId();
        Long groupGameId = oldSkus.get(0).getGroupGameId();
        //新增sku
        if(!ObjectUtils.isEmpty(insertSkuList)){
            insertSkuList.forEach(sku->{
                Checker.check(ObjectUtils.isEmpty(sku.getGroupPrice()),"团购商品价格参数缺失。");
                Checker.check(ObjectUtils.isEmpty(sku.getGroupStock()),"团购商品库存参数缺失。");
                PmsSkuStock pmsSkuStock = iPmsSkuStockService.selectPmsSkuStockById(sku.getSkuId());
                Checker.check(ObjectUtils.isEmpty(pmsSkuStock),"团购商品选择的规格不存在。");
                Checker.checkOp(Objects.equals(productId,pmsSkuStock.getProductId()),"商品规格与商品数据不匹配。");
                sku.setSkuPic(pmsSkuStock.getPic());
                sku.setSkuSpData(pmsSkuStock.getSpData());
                sku.setProductId(pmsSkuStock.getProductId());
                sku.setSkuPrice(pmsSkuStock.getPrice());
                sku.setGroupGameId(groupGameId);
                sku.setCreateBy(loginName);
                sku.setCreateTime(nowDate);
            });
            iSmsGroupGameSkuService.batchInsertGroupGameSku(insertSkuList);
        }
        //删除sku
        if(!ObjectUtils.isEmpty(removeSkuIds)){
            iSmsGroupGameSkuService.deleteSmsGroupGameSkuByIds(removeSkuIds);
        }

        //修改sku
        if(!ObjectUtils.isEmpty(updateSkuList)){
            updateSkuList.forEach(gameSku->{
                Checker.check(ObjectUtils.isEmpty(gameSku.getGroupPrice()),"团购商品价格参数缺失。");
                Checker.check(ObjectUtils.isEmpty(gameSku.getGroupStock()),"团购商品库存参数缺失。");
                gameSku.setUpdateTime(nowDate);
                gameSku.setUpdateBy(loginName);
            });
            iSmsGroupGameSkuService.batchUpdateGameSku(updateSkuList);
        }
    }
    /**
     * 删除团购商品对象
     * 1.开始前的活动真删除
     * 2.开始后的活动假删除
     * @param ids 需要删除的数据ID
     * @return 删除团购商品数量
     */
    @Override
    public int deleteSmsGroupGameByIds(String ids) {
        //查询团购活动根据ids
        List<SmsGroupGame> games = smsGroupGameMapper.selectSmsGroupGameByIds(Convert.toStrArray(ids));
        String[] noStartIds = games.stream()
                .filter(game -> StringUtils.equals(SmsGroupGameConst.GameStatusEnum.TOBELISTED.getType(), game.getStatus()))
                .map(SmsGroupGame::getId)
                .toArray(String[]::new);
        String[] endedIds = games.stream()
                .filter(game -> StringUtils.equals(SmsGroupGameConst.GameStatusEnum.CANCELLISTED.getType(), game.getStatus()))
                .map(SmsGroupGame::getId)
                .toArray(String[]::new);
        int count = 0;
        if(!ObjectUtils.isEmpty(noStartIds)){
            //真实删除未开始的活动
            count += smsGroupGameMapper.realDelSmsGroupGameByIds(noStartIds);
            //删除活动下的团购sku
            iSmsGroupGameSkuService.realDelSmsGroupGameSkuByGGid(noStartIds);
        }
        if (!ObjectUtils.isEmpty(endedIds)){
            //假删除结束的活动
            count += smsGroupGameMapper.deleteSmsGroupGameByIds(endedIds);
            //假删活动下的团购sku
            iSmsGroupGameSkuService.deleteSmsGroupGameSkuByGGid(endedIds);
        }
        return count;
    }

    /**
     * 删除团购商品信息
     *
     * @param id 团购商品ID
     * @return 删除团购商品数量
     */
    @Override
    public int deleteSmsGroupGameById(Long id) {
        //验证
        validateAction(id, SmsGroupGameConst.GroupActionEnum.DEL);
        int count = smsGroupGameMapper.deleteSmsGroupGameById(id);
        //删除活动下的团购sku
        iSmsGroupGameSkuService.realDelSmsGroupGameSkuByGGid(Convert.toStrArray(String.valueOf(id)));
        return count;
    }
    /**
     * 开始活动
     * @param ids
     * @return
     */
    @Override
    public int start(String ids) {
        //查询团购活动根据ids
        int count = smsGroupGameMapper.countSmsGroupGameByIdsOfwaitStart(Convert.toStrArray(ids));
        Checker.check(count==0,"至少选择一条有效团购活动执行此操作！");
        return smsGroupGameMapper.startGroupGames(Convert.toStrArray(ids));
    }
    /**
     * 结束活动
     * @param id
     * @return
     */
    @Override
    public int stop(Long id) {
        //验证
        validateAction(id, SmsGroupGameConst.GroupActionEnum.STOP);
        //更新状态
        SmsGroupGame updateForStart = new SmsGroupGame();
        updateForStart.setId(id);
        updateForStart.setStatus(SmsGroupGameConst.GameStatusEnum.CANCELLISTED.getType());
        return smsGroupGameMapper.updateSmsGroupGame(updateForStart);
    }

    /**
     * 验证商品团购活动 存在/状态/操作
     * @param id
     * @param actionEnum
     */
    private SmsGroupGameParam validateAction(Long id, SmsGroupGameConst.GroupActionEnum actionEnum){
        //验证该商品团购活动
        SmsGroupGameParam groupGame = smsGroupGameMapper.getSmsGroupGameDetailbyId(id);
        Checker.check(ObjectUtils.isEmpty(groupGame),"该活动不存在.");
        //验证活动状态
        String status = groupGame.getStatus();
        SmsGroupGameConst.GameStatusEnum enumByType = SmsGroupGameConst.GameStatusEnum.getEnumByType(status);
        Checker.check(ObjectUtils.isEmpty(groupGame),"活动状态异常.");
        //验证操作合法性
        boolean isExist = SmsGroupGameConst.GroupActionEnum.getBtns(status).stream()
                .anyMatch(btn-> StringUtils.equals(btn,actionEnum.getType()));
        Checker.checkOp(isExist,StringUtils.format("活动{},不可执行{}操作.",enumByType.getDescribe(),actionEnum.getDescribe()));
        return groupGame;
    }
    /**
     * 查询团购中的不重复的商品
     * @return
     */
    @Override
    public List<PmsProduct> selectDistinctProductListForGroupGame() {
        return smsGroupGameMapper.selectDistinctProductListForGroupGame(ShiroUtils.getSysUser().getDeptId());
    }
    /**
     * 查询团购商品及下级关联sku的列表
     * 包含空的sku
     *
     * @param id 团购商品id
     * @return 团购商品集合
     */
    @Override
    public SmsGroupGameParam getSmsGroupGameDetailofEdit(Long id) {
        return smsGroupGameMapper.getSmsGroupGameDetailofEdit(id);
    }
    /**
     * 判断活动是否过期
     * @param groupGameId
     * @return
     */
    @Override
    public int validateGame(Long groupGameId,Long groupGameSkuId,int quantity) {
        return smsGroupGameMapper.validateGame(groupGameId,groupGameSkuId,quantity);
    }

    /**
     * 增加成团数量
     * @param recordId
     * @return
     */
    @Override
    public int groupSuccess(Long recordId) {
        return smsGroupGameMapper.groupSuccess(recordId);
    }
    /**
     * (app)获取可用团购商品
     * @return
     */
    @Override
    public List<SmsGroupGame> getEnableGroupProduct(SmsGroupGame game) {
        //设置活动进行中
        game.setStatus(SmsGroupGameConst.GameStatusEnum.LISTED.getType());
        return smsGroupGameMapper.getEnableGroupProduct(game);
    }
    /**
     * (app)获取团购商品详情
     * @param id
     * @return
     */
    @Override
    public PmsGroupProductDetailPageInfo getGroupProductDetailInfo(Long id) {
        return smsGroupGameMapper.getGroupProductDetailInfo(id,
                //活动状态启用
                SmsGroupGameConst.GameStatusEnum.LISTED.getType(),
                //上架
                ProductAttributeCategoryConst.publicStatusEnum.PUTAWAY.getType());
    }

    /**
     * 通过gameskuid查询skugame
     * @param gameId
     * @return
     */
    @Override
    public SmsGroupGame selectSmsGroupGameBySkuId(Long gameId) {
        return smsGroupGameMapper.selectSmsGroupGameBySkuId(gameId);
    }
}
