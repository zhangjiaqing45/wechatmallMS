package com.fante.project.weixin.core.component;

import com.fante.project.weixin.core.model.OrderRefund;
import com.fante.project.weixin.core.model.OrderRefundResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.weixin4j.Configuration;
import org.weixin4j.http.HttpsClient;
import org.weixin4j.http.Response;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

/**
 * @program: Fante
 * @Date: 2020/5/7 14:03
 * @Author: Mr.Z
 * @Description: 退款组件
 */
@Component
public class RefundComponent {

    private static Logger log = LoggerFactory.getLogger(RefundComponent.class);


    public OrderRefundResult payOrderRefund(OrderRefund refund, String partnerId, String certPath){
        String xmlPost = refund.toXML();
        System.out.println("调试模式_申请退款接口 提交XML数据：" + xmlPost);
        try {
            HttpsClient http = new HttpsClient();
            Response res = http.postXml("https://api.mch.weixin.qq.com/secapi/pay/refund", xmlPost, partnerId, certPath, partnerId);
            String xmlResult = res.asString();
            JAXBContext context = JAXBContext.newInstance(OrderRefundResult.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            OrderRefundResult result = (OrderRefundResult)unmarshaller.unmarshal(new StringReader(xmlResult));
            return result;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

}
