package com.fante.project.business.bizLogistics.controller;

import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.bizLogistics.domain.BizLogistics;
import com.fante.project.business.bizLogistics.service.IBizLogisticsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 物流信息Controller
 * 
 * @author fante
 * @date 2020-02-06
 */
@Controller
@RequestMapping("/business/bizLogistics")
public class BizLogisticsController extends BaseController
{

    private static Logger log = LoggerFactory.getLogger(BizLogisticsController.class);

    private String prefix = "business/bizLogistics";

    @Autowired
    private IBizLogisticsService bizLogisticsService;

    @RequiresPermissions("business:bizLogistics:view")
    @GetMapping()
    public String bizLogistics()
    {
        return prefix + "/bizLogistics";
    }

    /**
     * 查询物流信息列表
     */
    @RequiresPermissions("business:bizLogistics:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BizLogistics bizLogistics)
    {
        startPage();
        List<BizLogistics> list = bizLogisticsService.selectBizLogisticsList(bizLogistics);
        return getDataTable(list);
    }
}
