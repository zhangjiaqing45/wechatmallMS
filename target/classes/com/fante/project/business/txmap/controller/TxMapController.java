package com.fante.project.business.txmap.controller;

import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.framework.config.TxMapConfig;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.business.txmap.service.TxMapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: Fante
 * @Date: 2020/2/10 21:41
 * @Author: Mr.Z
 * @Description: 腾讯地图
 */
@Controller
@RequestMapping("/business/txMap")
public class TxMapController {

    private static Logger log = LoggerFactory.getLogger(TxMapController.class);

    private String prefix = "business/txMap";

    @Autowired
    private TxMapConfig txMapConfig;
    @Autowired
    private TxMapService txMapService;

    /**
     * 地图选点
     * @param map
     * @return
     */
    @GetMapping("pointSelect")
    public String pointSelect(ModelMap map, String coord) {
        coord = StringUtils.isBlank(coord) ? "" : coord;
        map.put("mapSelectUrl", txMapConfig.getLocpickerUrl(coord));
        return prefix + "/pointSelect";
    }

    /**
     * 地址转坐标
     * @param address
     * @return
     */
    @GetMapping("addrToCoord")
    @ResponseBody
    public AjaxResult addrToCoord(String address) {
        Checker.check(StringUtils.isBlank(address), "缺少地址信息");
        return AjaxResult.success()
                .put("coord", txMapService.addrToCoord(address));
    }

    /**
     * 指定加盟商--地图显示工单位置与工单所在市的加盟商位置
     * @param map
     * @param orderId
     * @return
     */
    @GetMapping("orderDispatch")
    public String orderDispatch(ModelMap map, Long orderId) {

        map.put("apiUrl", txMapConfig.getApiUrl());
        return prefix + "/orderDispatch";
    }

}
