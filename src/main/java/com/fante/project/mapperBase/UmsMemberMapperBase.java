package com.fante.project.mapperBase;

import com.fante.project.business.umsMember.domain.UmsMember;
import java.util.List;

/**
 * 会员Mapper基础接口
 *
 * @author fante
 * @date 2020-05-07
 */
public interface UmsMemberMapperBase {
    /**
     * 查询会员
     *
     * @param id 会员ID
     * @return 会员
     */
    public UmsMember selectUmsMemberById(Long id);

    /**
     * 查询会员列表
     *
     * @param umsMember 会员
     * @return 会员集合
     */
    public List<UmsMember> selectUmsMemberList(UmsMember umsMember);

    /**
     * 查询会员数量
     *
     * @param umsMember 会员
     * @return 结果
     */
    public int countUmsMember(UmsMember umsMember);

    /**
     * 唯一校验
     *
     * @param umsMember 会员
     * @return 结果
     */
    public int checkUmsMemberUnique(UmsMember umsMember);

    /**
     * 新增会员
     *
     * @param umsMember 会员
     * @return 结果
     */
    public int insertUmsMember(UmsMember umsMember);

    /**
     * 修改会员
     *
     * @param umsMember 会员
     * @return 结果
     */
    public int updateUmsMember(UmsMember umsMember);

    /**
     * 删除会员
     *
     * @param id 会员ID
     * @return 结果
     */
    public int deleteUmsMemberById(Long id);

    /**
     * 批量删除会员
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUmsMemberByIds(String[] ids);

}
