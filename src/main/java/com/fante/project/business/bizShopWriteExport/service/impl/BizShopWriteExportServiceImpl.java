package com.fante.project.business.bizShopWriteExport.service.impl;

import com.fante.common.business.constant.BizConstants;
import com.fante.common.business.enums.AuditTypeEnum;
import com.fante.common.business.enums.UserTypeEnum;
import com.fante.common.constant.Constants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.project.business.bizShopInfo.domain.BizShopUserDTO;
import com.fante.project.business.bizShopInfo.mapper.BizShopUserMapper;
import com.fante.project.business.bizShopInfo.service.impl.BizShopUserServiceImpl;
import com.fante.project.business.bizShopWriteExport.domain.BizShopWriteDTO;
import com.fante.project.business.bizShopWriteExport.domain.BizShopWriteExportDTO;
import com.fante.project.business.bizShopWriteExport.mapper.BizShopWriteExportMapper;
import com.fante.project.business.bizShopWriteExport.service.IBizShopWriteExportService;
import com.fante.project.system.config.service.IConfigService;
import com.fante.project.system.role.domain.Role;
import com.fante.project.system.role.service.IRoleService;
import com.fante.project.system.sms.service.SmsService;
import com.fante.project.system.sms.utils.SmsRedis;
import com.fante.project.system.user.domain.User;
import com.fante.project.system.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @program: Fante
 * @Date: 2020-9-1 21:57:22
 * @Author: Mr.JIN
 * @Description: 店铺入驻
 */
@Service
public class BizShopWriteExportServiceImpl implements IBizShopWriteExportService {

    private static Logger log = LoggerFactory.getLogger(BizShopWriteExportServiceImpl.class);

    @Autowired
    BizShopWriteExportMapper bizShopWriteExportMapper;


    /**
     * 店铺用户信息
     * @param BizShopWriteExportDTO
     * @return
     */
    @Override
    public List<BizShopWriteDTO> selectShopWriteJoinList(BizShopWriteDTO bizShopWriteDTO) {
        return bizShopWriteExportMapper.selectShopWriteJoinList(bizShopWriteDTO);
    }


}
