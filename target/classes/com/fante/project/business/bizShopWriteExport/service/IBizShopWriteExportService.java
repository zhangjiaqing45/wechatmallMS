package com.fante.project.business.bizShopWriteExport.service;

import com.fante.project.business.bizShopInfo.domain.BizShopUserDTO;
import com.fante.project.business.bizShopWriteExport.domain.BizShopWriteDTO;
import com.fante.project.business.bizShopWriteExport.domain.BizShopWriteExportDTO;
import com.fante.project.system.user.domain.User;

import java.util.List;

/**
 * @program: Fante
 * @Date: 2020年9月1日21:53:47
 * @Author: Mr.JIN
 * @Description: 店铺用户服务
 */
public interface IBizShopWriteExportService {


    /**
     * 店铺用户信息
     * @param bizShopUserDTO
     * @return
     */
    public List<BizShopWriteDTO>  selectShopWriteJoinList(BizShopWriteDTO bizShopWriteDTO);

}
