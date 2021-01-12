package com.fante.project.business.smsHomeAdvertise.service;

import com.fante.project.business.smsHomeAdvertise.domain.SmsHomeAdvertise;
import com.fante.project.business.smsHomeAdvertise.domain.SmsHomeAdvertisePositionDTO;
import com.fante.project.business.smsHomeAdvertise.domain.SmsHomeAdvertiseProductSelectDTO;

import java.util.List;

/**
 * 广告管理Service接口
 *
 * @author fante
 * @date 2020-04-07
 */
public interface ISmsHomeAdvertiseService {
    /**
     * 查询广告管理
     *
     * @param id 广告管理ID
     * @return 广告管理
     */
    public SmsHomeAdvertise selectSmsHomeAdvertiseById(Long id);

    /**
     * 查询广告管理列表
     *
     * @param smsHomeAdvertise 广告管理
     * @return 广告管理集合
     */
    public List<SmsHomeAdvertise> selectSmsHomeAdvertiseList(SmsHomeAdvertise smsHomeAdvertise);

    /**
     * 查询广告管理数量
     *
     * @param smsHomeAdvertise 广告管理
     * @return 结果
     */
    public int countSmsHomeAdvertise(SmsHomeAdvertise smsHomeAdvertise);

    /**
     * 唯一校验
     *
     * @param smsHomeAdvertise 广告管理
     * @return 结果
     */
    public String checkSmsHomeAdvertiseUnique(SmsHomeAdvertise smsHomeAdvertise);

    /**
     * 新增广告管理
     *
     * @param smsHomeAdvertise 广告管理
     * @return 新增广告管理数量
     */
    public int insertSmsHomeAdvertise(SmsHomeAdvertise smsHomeAdvertise);

    /**
     * 修改广告管理
     *
     * @param smsHomeAdvertise 广告管理
     * @return 修改广告管理数量
     */
    public int updateSmsHomeAdvertise(SmsHomeAdvertise smsHomeAdvertise);

    /**
     * 批量删除广告管理
     *
     * @param ids 需要删除的数据ID
     * @return 删除广告管理数量
     */
    public int deleteSmsHomeAdvertiseByIds(String ids);

    /**
     * 删除广告管理信息
     *
     * @param id 广告管理ID
     * @return 删除广告管理数量
     */
    public int deleteSmsHomeAdvertiseById(Long id);

    /**
     * 选择跳转商品
     * @param productSelectDTO
     * @return
     */
    List<SmsHomeAdvertiseProductSelectDTO> advertiseProductSelect(SmsHomeAdvertiseProductSelectDTO productSelectDTO);


    /**
     * 广告新增处理
     *
     * @param smsHomeAdvertise
     * @return
     */
    public int insertProcess(SmsHomeAdvertise smsHomeAdvertise);

    /**
     * 广告修改处理
     *
     * @param smsHomeAdvertise
     * @return
     */
    public int updateProcess(SmsHomeAdvertise smsHomeAdvertise);

    /**
     * 变更状态
     * @param id
     * @param status
     * @return
     */
    public int changeStatus(Long id, String status);

    /**
     * 变更排序
     * @param id
     * @param sort
     * @return
     */
    public int changeSort(Long id, Integer sort);


    /**
     * 按照广告位置查询数据
     * @param status
     * @param showNum
     * @return
     */
    List<SmsHomeAdvertisePositionDTO> selectAdvertiseWithPosition(int showNum);

    }
