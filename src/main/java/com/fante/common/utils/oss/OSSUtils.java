package com.fante.common.utils.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.fante.common.utils.file.FileUploadUtils;
import com.fante.common.utils.file.MimeTypeUtils;
import com.fante.framework.config.AliyunOSSConfig;
import com.fante.framework.config.FanteConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

/**
 * @program: Fante
 * @Date: 2020/4/10 11:31
 * @Author: Mr.Z
 * @Description:
 */
@Component
public class OSSUtils {

    private static Logger log = LoggerFactory.getLogger(OSSUtils.class);

    @Autowired
    AliyunOSSConfig config;
    @Autowired
    FanteConfig fanteConfig;

    /**
     * oss上传
     * @param baseDir
     * @param file
     * @return
     * @throws Exception
     */
    public String upload(String baseDir, MultipartFile file) throws Exception {
        return upload(baseDir, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
    }

    /**
     * oss上传
     * @param baseDir
     * @param file
     * @param allowedExtension
     * @return
     */
    public String upload(String baseDir, MultipartFile file, String[] allowedExtension) {
        String ossFileName = null;
        OSS ossClient = null;
        try {
            FileUploadUtils.assertAllowed(file, allowedExtension);
            String fileName = FileUploadUtils.extractFilename(file);

            ossClient = getOSSClient();
            ossFileName = getOssFileName(baseDir, fileName);
            ossClient.putObject(config.getBucketName(), ossFileName, new ByteArrayInputStream(file.getBytes()));
        } catch (Exception e) {
            log.error("OSS上传异常: {}", e.getMessage());
        } finally {
            closeClient(ossClient);
        }
        return ossFileName;
    }

    /**
     * oss的文件位置(路径+文件名)
     * @param uploadDir
     * @param fileName
     * @return
     */
    private String getOssFileName(String uploadDir, String fileName) {
        return  uploadDir + "/" + fileName;
    }


    /**
     * 获取oss客户端<br/>
     *
     * @return
     */
    public OSS getOSSClient() {
        OSS ossClient = new OSSClientBuilder().build(config.getEndPoint(), config.getAccessKeyID(), config.getAccessKeySecret());
        String bucketName = config.getBucketName();
        boolean exists = ossClient.doesBucketExist(bucketName);
        if (!exists) {
            createBucket(ossClient, bucketName);
        }
        return ossClient;
    }

    /**
     * 创建存储空间
     * @param ossClient
     * @param bucketName
     */
    private void createBucket(OSS ossClient, String bucketName){
        // 创建CreateBucketRequest对象。
        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
        // 设置存储空间的权限为公共读，默认是私有。
        createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
        // 设置存储空间的存储类型为低频访问类型，默认是标准类型。
        //createBucketRequest.setStorageClass(StorageClass.IA);
        ossClient.createBucket(createBucketRequest);
    }

    /**
     * 关闭OSSClient
     * @param ossClient
     */
    public void closeClient(OSS ossClient) {
        ossClient.shutdown();
    }

}
