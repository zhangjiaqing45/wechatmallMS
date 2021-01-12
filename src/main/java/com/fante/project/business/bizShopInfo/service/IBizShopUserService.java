package com.fante.project.business.bizShopInfo.service;

import com.fante.project.business.bizShopInfo.domain.BizShopUserDTO;
import com.fante.project.system.user.domain.User;

import java.util.List;

/**
 * @program: Fante
 * @Date: 2020/3/12 17:12
 * @Author: Mr.Z
 * @Description: 店铺用户服务
 */
public interface IBizShopUserService {

    /**
     * 店铺入驻注册用户
     * @param user
     * @param verifyCode
     */
    public void register(User user, String verifyCode);

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
    public BizShopUserDTO selectShopUserJoinById(Long userId);

}
