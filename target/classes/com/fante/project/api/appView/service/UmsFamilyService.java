package com.fante.project.api.appView.service;

import com.fante.common.business.enums.UmsMemberConst;
import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.project.business.umsMember.domain.UmsMember;
import com.fante.project.business.umsMember.service.IUmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author ftnet
 * @Description UmsFamilyService
 * @CreatTime 2020/5/8
 */
@Service
public class UmsFamilyService {
    @Autowired
    IUmsMemberService iUmsMemberService;
    /**
     * 获取下级
     * @param member
     * @return
     */
    public List<UmsMember> getMemeberChildren(UmsMember member) {
        //设置查询条件
        UmsMember select = new UmsMember();
        select.setPid(member.getId());
        return iUmsMemberService.selectUmsMemberList(select);
    }
}
