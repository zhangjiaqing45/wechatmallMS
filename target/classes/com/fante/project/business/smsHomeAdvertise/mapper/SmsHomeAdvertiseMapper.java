package com.fante.project.business.smsHomeAdvertise.mapper;

import com.fante.project.business.smsHomeAdvertise.domain.SmsHomeAdvertisePositionDTO;
import com.fante.project.business.smsHomeAdvertise.domain.SmsHomeAdvertiseProductSelectDTO;
import com.fante.project.mapperBase.SmsHomeAdvertiseMapperBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 广告管理Mapper扩展接口
 *
 * @author fante
 * @date 2020-04-07
 */

public interface SmsHomeAdvertiseMapper extends SmsHomeAdvertiseMapperBase {


    /**
     * 选择跳转商品
     * @param productSelectDTO
     * @return
     */
    List<SmsHomeAdvertiseProductSelectDTO> advertiseProductSelect(SmsHomeAdvertiseProductSelectDTO productSelectDTO);

    /**
     * 按照广告位置查询数据
     * @param status
     * @param showNum
     * @return
     */
    List<SmsHomeAdvertisePositionDTO> selectAdvertiseWithPosition(@Param("status") String status, @Param("showNum") int showNum);

}
