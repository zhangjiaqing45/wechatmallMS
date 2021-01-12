package com.fante.common.utils;

import com.fante.common.business.enums.ApiErrorEnum;
import com.fante.common.exception.BusinessException;
import com.fante.project.yapi.business.domain.PayConstants;
import okhttp3.FormBody;
import okhttp3.FormBody.Builder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2020 Inc. All rights reserved. <p>
 * Company: 凡特网络技术<p>
 *
 * @author Hunk
 * @date 2020/8/10 10:21
 * @since 1.0
 */
public class Sha1Util {
    public static String getNonceStr() {
        Random random = new Random();
        return Md5Utils.hash(String.valueOf(random.nextInt(10000)));
    }
    public static String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    //Sha1签名
    public static String getSha1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };

        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }

    public static String signMap(Map<String, String> plainMap, String signKey) {
        String[] keyArray = plainMap.keySet().stream().toArray(String[]::new);
        Arrays.sort(keyArray);
        StringBuffer bf = new StringBuffer();
        for (String key : keyArray) {
            String vlaue = plainMap.get(key);
            if (StringUtils.isNotBlank(vlaue)) {
                bf.append(key).append("=").append(vlaue).append("&");
            }
        }
        bf.append("key=").append(signKey);
        return DigestUtils.md5Hex(bf.toString().getBytes());
    }

    public static String post(Map<String, String> map,String url) throws IOException {
        if (map == null || map.size() <= 0){
            throw new BusinessException(ApiErrorEnum.API_ERROR_CODE_PARAMS_CHECK_EXCEPTION.getCode(), "请求参数不能为空");
        }

        List<String> list = new ArrayList<>();

        String timestamp = Sha1Util.getTimeStamp();
        String nonce = Sha1Util.getNonceStr();

        list.add(timestamp);
        list.add(nonce);
        list.add(PayConstants.Token);

        List<String> list2 = list.stream().sorted().collect(Collectors.toList());
        String ss = String.join("", list2);

        String signature = getSha1(ss).toLowerCase();

        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        formBodyBuilder.add("timestamp", timestamp)
                .add("nonce", nonce)
                .add("signature", signature);
        Set<String> set = map.keySet();
        Iterator<String> iterator = set.iterator();
        while  (iterator.hasNext()){
            String key = iterator.next();
            formBodyBuilder.add(key, map.get(key));
        }

        FormBody formBody = formBodyBuilder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }

    public static void main(String[] args) throws Exception{
        Map<String, String> map = new HashMap<>();
        map.put("merchantId","900001-95116503");
        map.put("outTradeNo","dm1561626196");
        map.put("refundCode","1");
        map.put("amount","1");
        /* ===================================== 同步用户 ==================================================== */
       // map.put("method", HttpInterfaceConstants.JJYL_METHOD_ADD_USER);

        /* ===================================== 获取医院列表 ==================================================== */
//        map.put("method", HttpInterfaceConstants.JJYL_METHOD_GET_HOSPITAL_LIST);

        /* ===================================== 获取科室列表 ==================================================== */

//        map.put("method", HttpInterfaceConstants.JJYL_METHOD_GET_DEPARTMENTS_LIST);
//        map.put("groupno", "YY0001");

        /* ===================================== 获取科室医生列表 ==================================================== */

//        map.put("method", HttpInterfaceConstants.JJYL_METHOD_GET_DOCTOR_LIST);
//        map.put("groupno", "YY0001");
//        map.put("H_Code", "YY00010001");

        /* ===================================== 获取商品分类列表 ==================================================== */
        // 暂时无数据返回
//        map.put("method", HttpInterfaceConstants.JJYL_METHOD_GET_PRODUCT_TYPE);

        /* ===================================== 获取分类商品列表 ==================================================== */
        // 暂时无数据返回
        //map.put("method", HttpInterfaceConstants.JJYL_METHOD_GET_PRODUCT);


        //String result = Sha1Util.post(map,PayConstants.CommonUrl+PayConstants.);

        String result = Sha1Util.signMap(map,PayConstants.CommonUrl+PayConstants.Sign);
        System.out.println("返回结果： ============> " + result);
    }
}




