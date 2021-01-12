package com.fante.project.business.bizShopWriteExport.mapper;

import com.fante.project.business.bizShopInfo.domain.BizShopUserDTO;
import com.fante.project.business.bizShopWriteExport.domain.BizShopWriteDTO;
import com.fante.project.business.bizShopWriteExport.domain.BizShopWriteExportDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: Fante
 * @Date: 22020-9-1 21:59:35
 * @Author: Mr.JIN
 * @Description:
 */
public interface BizShopWriteExportMapper {


    /**
     * 店铺用户信息
     * @param BizShopWriteDTO
     *
     * @return
     */
    public List<BizShopWriteDTO> selectShopWriteJoinList(BizShopWriteDTO bizShopWriteDTO);


}
