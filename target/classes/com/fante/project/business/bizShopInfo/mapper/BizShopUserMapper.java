package com.fante.project.business.bizShopInfo.mapper;

import com.fante.project.business.bizShopInfo.domain.BizShopUserDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: Fante
 * @Date: 2020/3/13 14:12
 * @Author: Mr.Z
 * @Description:
 */
public interface BizShopUserMapper {

    /**
     * 店铺用户信息
     * @param bizShopUserDTO
     * @return
     */
    public List<BizShopUserDTO> selectShopUserJoinList(BizShopUserDTO bizShopUserDTO);

    /**
     * 根据条件查询店铺用户信息
     * @param bizShopUserDTO
     * @return
     */
    public BizShopUserDTO selectShopUserJoinSigle(BizShopUserDTO bizShopUserDTO);

    /**
     * 根据用户ID查询店铺用户信息
     * @param userId
     * @return
     */
    public BizShopUserDTO selectShopUserJoinById(@Param("userId") Long userId);
}
