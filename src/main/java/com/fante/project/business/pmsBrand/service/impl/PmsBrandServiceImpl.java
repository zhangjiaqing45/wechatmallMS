package com.fante.project.business.pmsBrand.service.impl;

import java.util.List;

import com.fante.common.constant.Constants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.PinyinUtil;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.security.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.pmsBrand.mapper.PmsBrandMapper;
import com.fante.project.business.pmsBrand.domain.PmsBrand;
import com.fante.project.business.pmsBrand.service.IPmsBrandService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 品牌Service业务层处理
 *
 * @author fante
 * @date 2020-03-09
 */
@Service
public class PmsBrandServiceImpl implements IPmsBrandService {

    private static Logger log = LoggerFactory.getLogger(PmsBrandServiceImpl.class);

    @Autowired
    private PmsBrandMapper pmsBrandMapper;

    /**
     * 查询品牌
     *
     * @param id 品牌ID
     * @return 品牌
     */
    @Override
    public PmsBrand selectPmsBrandById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return pmsBrandMapper.selectPmsBrandById(id);
    }

    /**
     * 查询品牌列表
     *
     * @param pmsBrand 品牌
     * @return 品牌集合
     */
    @Override
    public List<PmsBrand> selectPmsBrandList(PmsBrand pmsBrand) {
        //查询所有品牌
        return pmsBrandMapper.selectPmsBrandList(pmsBrand);
    }

    /**
     * 新增品牌
     *
     * @param pmsBrand 品牌
     * @return 新增品牌数量
     */
    @Override
    public int insertPmsBrand(PmsBrand pmsBrand) {
        //验证参数
        String name = pmsBrand.getName();
        String logo = pmsBrand.getLogo();
        Checker.checkOp(StringUtils.isNoneBlank(name, logo),"品牌参数缺失！");
        //设置时间
        pmsBrand.setCreateTime(DateUtils.getNowDate());
        //设置当前用户所在店铺id
        pmsBrand.setShopId(ShiroUtils.getSysUser().getDeptId());
        //设置当前用户
        pmsBrand.setCreateBy(ShiroUtils.getLoginName());
        //设置首字母
        String firstLetter = PinyinUtil.getPinyinHeadChar(name.substring(0, 1));
        pmsBrand.setFirstLetter(firstLetter.toUpperCase());
        return pmsBrandMapper.insertPmsBrand(pmsBrand);
    }

    /**
     * 修改品牌
     *
     * @param pmsBrand 品牌
     * @return 修改品牌数量
     */
    @Override
    public int updatePmsBrand(PmsBrand pmsBrand) {
        //验证参数
        String name = pmsBrand.getName();
        if(!StringUtils.isEmpty(name)){
            //设置首字母
            String firstLetter = PinyinUtil.getPinyinHeadChar(name.substring(0, 1));
            pmsBrand.setFirstLetter(firstLetter.toUpperCase());
        }
        //设置时间
        pmsBrand.setUpdateTime(DateUtils.getNowDate());
        //设置当前用户
        pmsBrand.setUpdateBy(ShiroUtils.getLoginName());
        return pmsBrandMapper.updatePmsBrand(pmsBrand);
    }

    /**
     * 删除品牌对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除品牌数量
     */
    @Override
    public int deletePmsBrandByIds(String ids) {
        return pmsBrandMapper.deletePmsBrandByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除品牌信息
     *
     * @param id 品牌ID
     * @return 删除品牌数量
     */
    @Override
    public int deletePmsBrandById(Long id) {
        return pmsBrandMapper.deletePmsBrandById(id);
    }
    /**
     * 唯一校验
     *
     * @param pmsBrand 品牌
     * @return 结果
     */
    @Override
    public String checkPmsBrandUnique(PmsBrand pmsBrand) {
        return pmsBrandMapper.checkPmsBrandUnique(pmsBrand) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

}
