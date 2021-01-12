package com.fante.framework.web.service;

import com.fante.common.utils.ServletUtils;
import com.fante.framework.config.AliyunOSSConfig;
import com.fante.project.system.config.service.IConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RuoYi首创 html调用 thymeleaf 实现参数管理
 *
 * @author fante
 */
@Service("config")
public class ConfigService {
    @Autowired
    private IConfigService configService;

    @Autowired
    private AliyunOSSConfig aliyunOSSConfig;

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数名称
     * @return 参数键值
     */
    public String getKey(String configKey) {
        return configService.selectConfigByKey(configKey);
    }


    public String resPath() {
        if (aliyunOSSConfig.isEnabled()) {
            return aliyunOSSConfig.getBucketDomian();
        } else {
            return ServletUtils.getRequest().getContextPath() + "/";
        }
    }

    public String appResPath() {
        return aliyunOSSConfig.isEnabled() ? aliyunOSSConfig.getBucketDomian() : "";
    }

}
