package com.fante.project.weixin.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: Fante
 * @Date: 2020/2/5 17:20
 * @Author: Mr.Z
 * @Description: 公众号通用请求处理
 */
@Controller
@RequestMapping("/wechat/common")
public class WechatCommonController {

    private static Logger log = LoggerFactory.getLogger(WechatCommonController.class);

    /**
     * 微信公众号通用上传请求
     * @param files 文件
     * @param media 是否为媒体类型
     * @return
     */
    //@PostMapping("/upload")
    //@ResponseBody
    //public AjaxResult wechatUploadFile(@RequestParam("file") MultipartFile[] files, boolean media) {
    //    try {
    //        // 上传文件路径
    //        String filePath = FanteConfig.getWechatUploadPath();
    //        List<String> urls = Collections.emptyList();
    //        if (!ObjectUtils.isEmpty(files)) {
    //            String fileName = "";
    //            String[] allowedExtension = media ? MimeTypeUtils.MEDIA_EXTENSION : MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION;
    //            urls = new ArrayList<>();
    //            for (MultipartFile file : files) {
    //                // 上传并返回新文件名称
    //                fileName = FileUploadUtils.upload(filePath, file, allowedExtension);
    //                urls.add(fileName);
    //            }
    //        }
    //        return AjaxResult.success().put("urls", urls);
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //        return AjaxResult.error(e.getMessage());
    //    }
    //}

}
