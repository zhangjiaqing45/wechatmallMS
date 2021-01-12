package com.fante.project.common;

import com.fante.common.business.constant.BizConstants;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.file.FileUploadUtils;
import com.fante.common.utils.file.FileUtils;
import com.fante.common.utils.file.MimeTypeUtils;
import com.fante.common.utils.oss.OSSUtils;
import com.fante.framework.config.AliyunOSSConfig;
import com.fante.framework.config.FanteConfig;
import com.fante.framework.config.ServerConfig;
import com.fante.framework.web.domain.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 通用请求处理
 *
 * @author fante
 */
@Controller
public class CommonController {
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private FanteConfig fanteConfig;
    @Autowired
    private AliyunOSSConfig aliyunOSSConfig;
    @Autowired
    private OSSUtils ossUtils;

    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     * @param delete   是否删除
     */
    @GetMapping("common/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request) {
        try {
            if (ObjectUtils.isEmpty(delete)) {
                delete = false;
            }
            if (!FileUtils.isValidFilename(fileName)) {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            //String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);

            String filePath = FanteConfig.getProfile() + "/" + BizConstants.path.DOWNLOAD + "/" + fileName;

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, fileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete) {
                FileUtils.deleteFile(filePath);
            }
        } catch (Exception e) {
            log.error("下载文件失败", e.getMessage());
        }
    }

    /**
     * 通用上传请求
     * @param file
     * @return
     */
    @PostMapping("/common/upload")
    @ResponseBody
    public AjaxResult uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // 上传文件路径
            String filePath = "";
            if (aliyunOSSConfig.isEnabled()) {
                filePath = FanteConfig.getProjectCode() + "/" + BizConstants.path.UPLOAD;
                return ossUpload(file, filePath);
            } else {
                filePath = FanteConfig.getProfile() + "/" + BizConstants.path.UPLOAD;
                return upload(file, filePath);
            }
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }


    /**
     * 重要信息上传请求
     * @param file
     * @return
     */
    @PostMapping("/common/orgupload")
    @ResponseBody
    public AjaxResult orgUploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // 上传文件路径
            String filePath = "";
            if (aliyunOSSConfig.isEnabled()) {
                filePath = FanteConfig.getProjectCode() + "/" + BizConstants.path.ORG_UPLOAD;
                return ossUpload(file, filePath);
            } else {
                filePath = FanteConfig.getProfile() + "/" + BizConstants.path.ORG_UPLOAD;
                return upload(file, filePath);
            }
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 富文本图片上传请求
     * @param file
     * @return
     */
    @PostMapping("/common/richTextUpload")
    @ResponseBody
    public AjaxResult richTextUpload(@RequestParam("file") MultipartFile file) {
        try {
            // 上传文件路径
            String filePath = "";
            if (aliyunOSSConfig.isEnabled()) {
                filePath = FanteConfig.getProjectCode() + "/" + BizConstants.path.RICH_TEXT_UPLOAD;
                return ossUpload(file, filePath);
            } else {
                filePath = FanteConfig.getProfile() + "/" + BizConstants.path.RICH_TEXT_UPLOAD;
                return upload(file, filePath);
            }
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 微信公众号图片上传请求
     * @param file
     * @return
     */
    @PostMapping("/wechat/upload")
    @ResponseBody
    public AjaxResult wechatUpload(@RequestParam("file") MultipartFile file, boolean media) {
        try {
            // 上传文件路径
            String filePath = "";
            String[] allowedExtension = media ? MimeTypeUtils.MEDIA_EXTENSION : MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION;
            if (aliyunOSSConfig.isEnabled()) {
                filePath = FanteConfig.getProjectCode() + "/" + BizConstants.path.WECHAT_UPLOAD;
                return ossUpload(file, filePath, allowedExtension);
            } else {
                filePath = FanteConfig.getProfile() + "/" + BizConstants.path.WECHAT_UPLOAD;
                return upload(file, filePath, allowedExtension);
            }
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 普通上传处理
     * @param file
     * @param filePath
     * @return
     * @throws IOException
     */
    private AjaxResult upload(MultipartFile file, String filePath) throws Exception {
        // 上传并返回新文件名称
        String fileName = FanteConfig.getProjectCode() + "/" + FileUploadUtils.upload(filePath, file);
        String url = serverConfig.getUrl() + "/" + fileName;
        return AjaxResult.success("上传成功").put("fileName", fileName).put("url", url);
    }

    /**
     * 普通上传处理
     * @param file
     * @param filePath
     * @param allowedExtension
     * @return
     * @throws IOException
     */
    private AjaxResult upload(MultipartFile file, String filePath, String[] allowedExtension) throws Exception {
        // 上传并返回新文件名称
        String fileName = FanteConfig.getProjectCode() + "/" + FileUploadUtils.upload(filePath, file, allowedExtension);
        String url = serverConfig.getUrl() + "/" + fileName;
        return AjaxResult.success("上传成功").put("fileName", fileName).put("url", url);
    }

    /**
     * OSS上传处理
     * @param file
     * @param filePath
     * @return
     * @throws Exception
     */
    private AjaxResult ossUpload(MultipartFile file, String filePath) throws Exception {
        String fileName = ossUtils.upload(filePath, file);
        if (StringUtils.isBlank(fileName)) {
            return AjaxResult.error("上传异常");
        }
        String url = aliyunOSSConfig.getBucketDomian() + "/" + fileName;
        return AjaxResult.success("上传成功").put("fileName", fileName).put("url", url);
    }

    /**
     * OSS上传处理
     * @param file
     * @param filePath
     * @param allowedExtension
     * @return
     * @throws Exception
     */
    private AjaxResult ossUpload(MultipartFile file, String filePath, String[] allowedExtension) throws Exception {
        String fileName = ossUtils.upload(filePath, file, allowedExtension);
        if (StringUtils.isBlank(fileName)) {
            return AjaxResult.error("上传异常");
        }
        String url = aliyunOSSConfig.getBucketDomian() + "/" + fileName;
        return AjaxResult.success("上传成功").put("fileName", fileName).put("url", url);
    }

    /**
     * 本地资源通用下载
     */
    @GetMapping("/common/download/resource")
    public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 本地资源路径
        String localPath = FanteConfig.getProfile();
        // 数据库资源地址
        String downloadPath = localPath + StringUtils.substringAfter(resource, FanteConfig.getProjectCode());
        // 下载名称
        String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, downloadName));
        FileUtils.writeBytes(downloadPath, response.getOutputStream());
    }
}
