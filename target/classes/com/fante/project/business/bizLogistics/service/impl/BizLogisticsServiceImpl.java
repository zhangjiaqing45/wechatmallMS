package com.fante.project.business.bizLogistics.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.text.Convert;
import com.fante.framework.config.AliyunWlConfig;
import com.fante.project.business.bizLogistics.domain.BizLogistics;
import com.fante.project.business.bizLogistics.mapper.BizLogisticsMapper;
import com.fante.project.business.bizLogistics.service.IBizLogisticsService;
import com.fante.project.business.bizLogistics.utils.QueryUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 物流信息Service业务层处理
 *
 * @author fante
 * @date 2020-02-06
 */
@Service
public class BizLogisticsServiceImpl implements IBizLogisticsService {

    private static Logger log = LoggerFactory.getLogger(BizLogisticsServiceImpl.class);

    @Autowired
    private BizLogisticsMapper bizLogisticsMapper;
    @Autowired
    QueryUtils queryUtils;
    @Autowired
    AliyunWlConfig aliyunWlConfig;

    /**
     * 物流修改意见：
     * 1、录入物流信息的角色：设备厂商、用户
     * 2、在录入物流信心时，增加一条物流表记录
     * 3、物流查询时，仅查询物流表内的物流单号
     * 4、物流表增加工单ID字段
     */


    /**
     * 返回状态码
     * status 0:正常查询 201:快递单号错误 203:快递公司不存在 204:快递公司识别失败 205:没有信息 207:该单号被限制，错误单号
     */
    private static final String STATUS_NORMAL = "0";

    /**
     * 快递投递状态码
     * 0：快递收件(揽件)1.在途中 2.正在派件 3.已签收 4.派送失败 5.疑难件 6.退件签收
     */
    private static final String[] UPDATE_DELIVERYSTATUS = {"0", "1", "2"};

    /**
     * 查询物流信息
     *
     * @param id 物流信息ID
     * @return 物流信息
     */
    @Override
    public BizLogistics selectBizLogisticsById(Long id) {
        return bizLogisticsMapper.selectBizLogisticsById(id);
    }

    /**
     * 远程获取物流数据
     *
     * @param number
     * @param type
     * @return
     * @throws Exception
     */
    private BizLogistics queryRemote(String number, String type) throws Exception {
        String resultStr = queryUtils.query(number, type);
        JSONObject resultJson = JSONObject.parseObject(resultStr);
        //获取订单号和type
        JSONObject result = JSONObject.parseObject(resultJson.getString("result"));
        number = result.getString("number");
        type = result.getString("type");
        //物流详细信息
        String resultList = result.getString("list");
        BizLogistics bizLogistics = JSON.toJavaObject(result, BizLogistics.class);
        bizLogistics.setNumber(number);
        bizLogistics.setType(type);
        bizLogistics.setStatus(resultJson.getString("status"));
        bizLogistics.setMsg(resultJson.getString("msg"));
        bizLogistics.setResuleList(resultList);
        bizLogistics.setTime(System.currentTimeMillis());
        return bizLogistics;
    }

    /**
     * 判断单子是否需要更新
     * @param bizLogistics
     * @return
     */
    private boolean needUpdate(BizLogistics bizLogistics) {
        // 是否为正常单
        if (!Objects.equals(bizLogistics.getStatus(), STATUS_NORMAL)) {
            log.info("异常单：无需查询");
            return false;
        }

        // 正常单判断是否需要更新
        if (!Arrays.asList(UPDATE_DELIVERYSTATUS).contains(bizLogistics.getDeliverystatus())) {
            log.info("正常单：结束投递，无需更新");
            return false;
        }

        // 正常单判断是否到更新时间
        long now = System.currentTimeMillis();
        long diff = now - bizLogistics.getTime();
        if (diff < aliyunWlConfig.getInterval() * 1000) {
            log.info("正常单：未到更新时间，无需更新");
            return false;
        }
        return true;
    }


    /**
     * 查询物流信息
     *
     * @param number 单号
     * @param type   公司名称代码（选填）
     * @return 物流信息
     */
    @Override
    public BizLogistics queryByNumberOrType(String number, String type) throws Exception {
        Checker.check(StringUtils.isBlank(number), "缺少物流单号");
        //本地查询
        BizLogistics bizLogistics = bizLogisticsMapper.queryByNumber(number);
        if (ObjectUtils.isEmpty(bizLogistics)) {
            // 远程查询
            bizLogistics = queryRemote(number, type);
            // 插入数据
            insertBizLogistics(bizLogistics);
        } else {
            // 数据库已有数据，检查是否需要更新
            if (needUpdate(bizLogistics)) {
                // 远程查询
                bizLogistics = queryRemote(number, type);
                // 更新数据
                updateBizLogistics(bizLogistics);
            }
        }
        return bizLogistics;
    }

    /**
     * 查询物流信息列表
     *
     * @param bizLogistics 物流信息
     * @return 物流信息
     */
    @Override
    public List<BizLogistics> selectBizLogisticsList(BizLogistics bizLogistics) {
        return bizLogisticsMapper.selectBizLogisticsList(bizLogistics);
    }

    /**
     * 新增物流信息
     *
     * @param bizLogistics 物流信息
     * @return 结果
     */
    @Override
    public int insertBizLogistics(BizLogistics bizLogistics) {
        return bizLogisticsMapper.insertBizLogistics(bizLogistics);
    }

    /**
     * 修改物流信息
     *
     * @param bizLogistics 物流信息
     * @return 结果
     */
    @Override
    public int updateBizLogistics(BizLogistics bizLogistics) {
        return bizLogisticsMapper.updateBizLogistics(bizLogistics);
    }

    /**
     * 删除物流信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBizLogisticsByIds(String ids) {
        return bizLogisticsMapper.deleteBizLogisticsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除物流信息信息
     *
     * @param id 物流信息ID
     * @return 结果
     */
    @Override
    public int deleteBizLogisticsById(Long id) {
        return bizLogisticsMapper.deleteBizLogisticsById(id);
    }
}
