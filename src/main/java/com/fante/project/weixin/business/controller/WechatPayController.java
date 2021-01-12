package com.fante.project.weixin.business.controller;

import com.fante.common.business.constant.BizConstants;
import com.fante.common.utils.StringUtils;
import com.fante.framework.config.FanteConfig;
import com.fante.framework.config.ServerConfig;
import com.fante.framework.interceptor.annotation.RepeatSubmit;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.api.umsProcess.service.UmsMemberProcessService;
import com.fante.project.business.bizShopInfo.domain.BizShopInfo;
import com.fante.project.business.bizShopInfo.service.IBizShopInfoService;
import com.fante.project.business.omsOrder.domain.OmsOrder;
import com.fante.project.business.omsOrder.service.IOmsOrderService;
import com.fante.project.business.omsOrderWriteOff.domain.OmsOrderWriteOff;
import com.fante.project.business.omsOrderWriteOff.service.IOmsOrderWriteOffService;
import com.fante.project.business.omsPayOrder.domain.OmsPayOrder;
import com.fante.project.business.omsPayOrder.service.IOmsPayOrderService;
import com.fante.project.business.umsMember.domain.UmsMember;
import com.fante.project.business.umsMember.service.IUmsMemberService;
import com.fante.project.weixin.business.service.WechatOrderService;
import com.fante.project.yapi.business.util.payUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.weixin4j.util.XStreamFactory;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * @program: Fante
 * @Date: 2020/2/21 10:23
 * @Author: Mr.Z
 * @Description:
 */
@Controller
@RequestMapping("/wechat/pay")
public class WechatPayController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(WechatPayController.class);
    // 通信成功标识
    public static final String SIGN_SUCCESS = "SUCCESS";
    @Autowired
    WechatOrderService wechatOrderService;
    @Autowired
    IOmsOrderService orderService;
    @Autowired
    IOmsPayOrderService payOrderService;
    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    IOmsOrderWriteOffService orderWriteOffService;
    @Autowired
    UmsMemberProcessService memberProcessService;
    @Autowired
    IUmsMemberService memberService;
    @Autowired
    IBizShopInfoService bizShopInfoService;

    /**
     * 执行统一下单
     * @param request
     * @param url
     * @param payOrderSn
     * @return
     * @throws Exception
     */
    @PostMapping("unifiedorder")
    @ResponseBody
    public AjaxResult unifiedOrder(HttpServletRequest request, String url, String payOrderSn) throws Exception {
        return AjaxResult.success()
                .put("rsp", wechatOrderService.wxUnifiedorder(request, url, payOrderSn, getTokenUserId(), getTokenClientId()));
    }

    @GetMapping("/toPay")
    @ResponseBody
    public AjaxResult toPay(String payOrderSn,ModelMap map){
        OmsPayOrder payOrder= payOrderService.selectOmsPayOrderByOrderSn(payOrderSn);
        System.out.println("payOrder.getPayOrderSn()="+payOrder.getPayOrderSn());
        if(payOrder !=null){
            OmsOrder order=new OmsOrder();
            order.setPayOrderId(payOrder.getId());
            List <OmsOrder> ordlist= orderService.selectOmsOrderList(order);
            if(ordlist.size()>0){
                BizShopInfo shop= bizShopInfoService.selectBizShopInfoById(ordlist.get(0).getShopId());

                //String url ="http://run.buybal.com";//测试
                String url="https://up.chinanums.com";
                String openpayapi ="/openpayapi/v1.0/cashier/JsPay";
                url = url + openpayapi;
                Map<String, String> paramMap =new HashMap<>();
                paramMap.put("clientId","9000144");//正式系统标识
                //paramMap.put("clientId","1");//系统标识
                paramMap.put("merchantId",shop.getAccount());//正式商户id
                //paramMap.put("merchantId","95116323");//测试商户id
                paramMap.put("mchntName",shop.getCompanyName());//正式商户名称
                //paramMap.put("mchntName","凡特测试");//测试商户名称
                //paramMap.put("deviceId","");//设备号id非必传 长度不能超过24位
                paramMap.put("totalFee",payOrder.getPayTotalPrice().multiply(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_DOWN).longValue()+"");//金额，单位分
                paramMap.put("outOrderNo",payOrder.getPayOrderSn());//支付订单号
                //paramMap.put("subject","在线购买");//订单主题，金燕商户必填
                paramMap.put("bodyDesc","在线购买");//订单描述非必传
                if(StringUtils.isNotEmpty(shop.getOrgId())){
                    paramMap.put("orgId",shop.getOrgId());//机构id，金燕商户必填
                }
                paramMap.put("notifyUrl","http://www.henangaiyin.com/wechat/pay/notify");//后台通知地址
                paramMap.put("callBackUrl","http://www.henangaiyin.com/guomdh5/#/pages/web/web");//前台通知地址
                //paramMap.put("sign",payUtil.signMap(paramMap,"111111"));//签名
                String signMap = payUtil.signMap(paramMap, "XNIKTKX6UiP0Yk3Bkq4g6u8TW5Fl7DzQfoBO0hD/AmDQEABQrZzF+g==");
                log.info("signMap::"+signMap);
                paramMap.put("sign",signMap);//签名
                map.put("action",url);
                map.put("paramMap",paramMap);
            }

        }
        return AjaxResult.success().put("map", map);
    }

    @RequestMapping("/notify")
    @RepeatSubmit
    public void notify(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        log.info("驻马店支付回调:: 开始");
        // 获取请求流
        ServletInputStream in = req.getInputStream();
        // 将流转换为字符串
        String xmlMsg = XStreamFactory.inputStream2String(in);
        System.out.println("xmlMsg="+xmlMsg);
        JSONObject json = JSONObject.fromObject(xmlMsg);
        String resultCode=json.getString("resultCode");
        String outOrderNo=json.getString("outOrderNo");
        System.out.println("resultCode="+resultCode);
        System.out.println("outOrderNo="+outOrderNo);
        if (StringUtils.isNotBlank(resultCode) && Objects.equals(SIGN_SUCCESS, resultCode)) {
            boolean verify =true;
            if (verify) {
                log.info("到账处理业务:: 开始");
                wechatOrderService.payNotify(outOrderNo);
                log.info("到账处理业务:: 结束");
                log.info("驻马店银行支付回调-成功");
                JSONObject json2 = new JSONObject();
                json2.put("returnCode","success");
                json2.put("returnMsg","回调成功！");
                resp.getWriter().write(json2.toString());
            }
        } else {
            log.error("驻马店银行支付回调 支付失败");
            JSONObject json2 = new JSONObject();
            json2.put("returnCode","fail");
            json2.put("returnMsg","回调失败！");
            resp.getWriter().write(json2.toString());
        }
    }

    //核销订单
    //参数：订单号
    //返回：成功、失败
    @GetMapping("/writeOff")
    @ResponseBody
    public AjaxResult writeOff(String orderSn){
        String openid = getTokenClientId();

        OmsOrder order=new OmsOrder();
        order.setOrderSn(orderSn);
        List<OmsOrder> list= orderService.selectOmsOrderList(order);
        boolean result=false;
        if(list.size()>0){
            UmsMember hxuser= memberProcessService.get(openid);
            OmsOrder orders=list.get(0);
            if(!hxuser.getShopId().equals(orders.getShopId())){
                return AjaxResult.error("非本店铺订单无法核销！");
            }
            orders.setWriteOffStatus("1");
            orders.setStatus("3");
            int i= orderService.updateOmsOrder(orders);
            //添加核销记录
            UmsMember user= memberService.selectUmsMemberById(orders.getMemberId());
            OmsOrderWriteOff writeOff=new OmsOrderWriteOff();
            writeOff.setOutTradeNo(orderSn);
            writeOff.setHxOpenid(openid);
            writeOff.setHxNickname(hxuser.getNickname());
            writeOff.setOpenid(user.getOpenid());
            writeOff.setNickname(user.getNickname());
            if(i==1){
                writeOff.setStatus("1");
                result=true;
            }else{
                writeOff.setStatus("0");
            }
            writeOff.setDelFlag("0");
            orderWriteOffService.insertOmsOrderWriteOff(writeOff);
        }
        if (result){
            return AjaxResult.success("核销成功！");
        }else{
            return AjaxResult.error("核销失败，请稍后重试！");
        }
    }

    //查询核销状态
    //参数：订单号
    //返回核销状态
    @GetMapping("/getWriteOff")
    @ResponseBody
        public AjaxResult getWriteOff(String orderSn){
        OmsOrder order=new OmsOrder();
        order.setOrderSn(orderSn);
        List<OmsOrder> list= orderService.selectOmsOrderList(order);
        if(list.size()>0){
            OmsOrder orders=list.get(0);
            return AjaxResult.success(orders.getWriteOffStatus());
        }else{
            return AjaxResult.error("查询失败！");
        }
    }


    private String getParam(HttpServletRequest request) throws IOException {
        // 读取参数
        InputStream inputStream;
        StringBuilder sb = new StringBuilder();
        inputStream = request.getInputStream();
        String s;
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        while ((s = in.readLine()) != null) {
            sb.append(s);
        }
        in.close();
        inputStream.close();
        return sb.toString();
    }
    @PostMapping("/createHtml")
    @ResponseBody
    public AjaxResult createHtml(String templateString){
        String destDir="";
        //用于存储html字符串
        StringBuilder stringHtml = new StringBuilder();
        try {
            String openid = getTokenClientId();
            String filePath = FanteConfig.getProfile() + "/" + BizConstants.path.UPLOAD;// 上传文件路径
            String url = filePath+"/modelHtml.html";// 模板文件地址
            String savepath = filePath +"/"+ openid + ".html";// 生成文件地址
            Boolean a= JspToHtmlFile(url, savepath);
            System.out.println("filePath="+filePath);
            System.out.println("url="+url);
            System.out.println("savepath="+savepath);
            System.out.println("a="+a);
            if(a){
                //打开文件
                PrintStream printStream = new PrintStream(new FileOutputStream(savepath));
                //输入HTML文件内容
                stringHtml.append(templateString);
                //将HTML文件内容写入文件中
                printStream.println(stringHtml.toString());
                destDir = serverConfig.getUrl() + "/profile/upload/" + openid + ".html";
                System.out.println("destDir="+destDir);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return AjaxResult.success().put("url", destDir);
    }

    public static boolean JspToHtmlFile(String filePath, String saveHtmlFile) {
        String str = "";
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(filePath);
            HttpURLConnection httpUrl = (HttpURLConnection)url.openConnection();
            InputStream is = httpUrl.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                buffer.append(line+"\n");
            }
            is.close();
            br.close();
        } catch (Exception e) {

        }

        try {
            //必须设置编码格式不然会出现乱码
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(saveHtmlFile),"UTF-8"));
            bufferedWriter.write(str);
            bufferedWriter.newLine();//换行
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 执行申请退款
     * @param
     * @return
     * @throws Exception
     */
    //@PostMapping("refundorder")
    //@ResponseBody
    //public AjaxResult refundOrder(Long applyId) throws Exception {
    //    return AjaxResult.success()
    //            .put("rsp", wechatOrderService.wxRefundOrder(applyId));
    //}
    public static void main(String[] args) {
        String xmlMsg = "{'amount':1,'timePaid':'2020-11-318 10:18:52','clientId':'1','orderNo':'03339716052339231404355555','bankType':'','resultCode':'SUCCESS','cardType':'0','sign':'17fda92003f43903cf598396666c6437','outOrderNo':'1605233867742','deviceId':'00000','resultMsg':'成功'}";
    }
}
