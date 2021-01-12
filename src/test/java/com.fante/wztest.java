package com.fante;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.fante.common.business.constant.BizConstants;
import com.fante.common.business.enums.OrderConst;
import com.fante.common.business.enums.UmsAddressConst;
import com.fante.common.business.enums.UmsMemberConst;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.LocalDateUtil;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.bean.BeanUtils;
import com.fante.project.api.mq.resp.OrderHandleResult;
import com.fante.project.api.omsOrderProcess.domain.ConfirmOrderParam;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.Callable;

/**
 * @author ftnet
 * @Description test
 * @CreatTime 2020/4/7
 */
public class wztest {
    private static Logger log = LoggerFactory.getLogger(wztest.class);
    public static void main(String[] args) throws IOException {
        System.out.println("----------------自动取消未支付过期订单------------------");
        System.out.println("|---------------"+DateUtils.getTime()+"------------------|");
        System.out.println("------------------------------------------------------");

        log.info("------------------------------------------------------");
        log.info("|-----------------超时自动关闭订单---------------------|");
        log.info("|---------------"+DateUtils.getTime()+"------------------|");
        log.info("------------------------------------------------------");

      /*  String a = "{\"颜色\":\"蓝色\",\"内存\":\"256\"}";
        System.out.println(a.replaceAll(BizConstants.regexp.REGEXP_SP_DATA_TRIM,""));*/
        /*
        String nowDay = LocalDateUtil.dateFormat(new Date(), "d");
        List<String> signRecordList = Arrays.asList("1","2","7");
        System.out.println(   signRecordList.contains(nowDay));*/

        /*OrderHandleResult redisMsg = (OrderHandleResult) null;

        Map<String,Object> result = new HashMap<String,Object>(){{
           put("1",new BigDecimal(30));
           put("2",new BigDecimal(50));
           put("3",new BigDecimal(40));
        }};

        Map<String ,Object> errMsg =  new HashMap<String,Object>();
        String rids = Arrays.asList("").stream().filter(StringUtils::isNotEmpty)
                .reduce((pids, item) -> pids + "," + item).orElse(StringUtils.EMPTY);
        result.put("noSend",rids);
        System.out.println(JSON.toJSONString(result));*/

    }


  /*  public static OSSClient test(){
        File pdf = new File("C:\\Users\\Administrator\\Desktop\\clipboard.png");
        MultipartFile multipartFile = new MockMultipartFile(pdf.getName(), pdf.getName(),
                ContentType.APPLICATION_OCTET_STREAM.toString(),  new FileInputStream(pdf));
        uploadDocument(multipartFile,"test");
    }*/
   /* public static OSSClient getOssClient(){
        OSSClient oss = new OSSClient("oss-cn-beijing.aliyuncs.com","LTAI4Fx4Ks6mFXdTBXyiAoAZ","GUyW8ws8SDgEz9icc4A02SOTJsQjhO");//获取oss服务
        boolean b = oss.doesBucketExist("wangzhao");//判断仓库是否存在
        if(!b){
            //通过api接口的形式创建仓库bucket
            CreateBucketRequest bucket = new CreateBucketRequest(null);
            bucket.setBucketName("wangzhao");
            bucket.setCannedACL(CannedAccessControlList.PublicRead);//设置权限为公共读
            oss.createBucket(bucket);
            oss.shutdown();
        }
        return oss;
    }
    //文件 和 类型
    public static String uploadDocument(MultipartFile mflie, String businessType){
        //获取oss
        OSSClient oss = getOssClient();
        //获取文件的后缀名称
        String ext = mflie.getOriginalFilename().substring(mflie.getOriginalFilename().lastIndexOf("."));
        //上传的文件路径规则-->例如： http://www.baidu.com/img/20220228/aes3f2f321.jpg
        String newFileName = getNewFileName(businessType,ext);
        String url = "";
        try{
            oss.putObject("wangzhao",newFileName,new ByteArrayInputStream(mflie.getBytes()));
            //url = http(s)://bucket域名/拼接的newFileName
            url ="https://wangzhao.oss-cn-beijing.aliyuncs.com/" + newFileName;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            oss.shutdown();
        }
        return url;
    }
    //参数 文件类型  和 后缀
    public static String getNewFileName(String businessType,String ext){
        if(StringUtils.isEmpty(businessType)){
            businessType="default";
        }
        //获取当前日期
        String dateStr = DateUtils.dateTimeNow();
        //避免重名用uuid
        String uuid = "aaaa";
        //组合：类型/时间/文件名称.后缀
        return  businessType+"/"+dateStr +"/"+uuid+"."+ext;
    }*/
}
