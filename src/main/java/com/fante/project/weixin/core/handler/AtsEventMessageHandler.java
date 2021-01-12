package com.fante.project.weixin.core.handler;

import com.fante.common.exception.BusinessException;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.weixin.core.service.impl.WeixinSubscribeImpl;
import com.fante.project.weixin.core.utils.WechatRedis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.weixin4j.model.message.OutputMessage;
import org.weixin4j.model.message.event.*;
import org.weixin4j.model.message.output.TextOutputMessage;
import org.weixin4j.spi.IEventMessageHandler;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description: 自定义事件消息处理器
 */
@Component
public class AtsEventMessageHandler implements IEventMessageHandler {

    private static Logger log = LoggerFactory.getLogger(AtsEventMessageHandler.class);

    @Autowired
    WeixinSubscribeImpl weixinSubscribe;
    @Autowired
    WechatRedis wechatRedis;

    @Override
    public OutputMessage subscribe(SubscribeEventMessage msg) {
        try {
            //weixinSubscribe.subscribeProcess(msg.getFromUserName());
            TextOutputMessage out = new TextOutputMessage();
            out.setContent("感谢您的关注！");
            return out;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BusinessException(AjaxResult.Type.ERROR.value(), "subscribe:" + e.getMessage());
        }
    }

    @Override
    public OutputMessage unSubscribe(UnSubscribeEventMessage msg) {
        try {
            //weixinSubscribe.unsubscribeProcess(msg.getFromUserName());
            TextOutputMessage out = new TextOutputMessage();
            out.setContent("您已取消关注！");
            return out;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BusinessException(AjaxResult.Type.ERROR.value(), "unSubscribe:" + e.getMessage());
        }
    }

    @Override
    public OutputMessage qrsceneSubscribe(QrsceneSubscribeEventMessage msg) {
        try {
            //weixinSubscribe.subscribeProcess(msg.getFromUserName());
            TextOutputMessage out = new TextOutputMessage();
            out.setContent("感谢您的关注！");
            return out;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BusinessException(AjaxResult.Type.ERROR.value(), "qrsceneSubscribe:" + e.getMessage());
        }
    }

    @Override
    public OutputMessage qrsceneScan(QrsceneScanEventMessage msg) {
        TextOutputMessage out = new TextOutputMessage();
        out.setContent("已关注用户扫描带参数二维码！");
        return out;
    }

    @Override
    public OutputMessage location(LocationEventMessage msg) {
        TextOutputMessage out = new TextOutputMessage();
        out.setContent("上报地理位置！");
        return out;
    }

    @Override
    public OutputMessage click(ClickEventMessage msg) {
        TextOutputMessage out = new TextOutputMessage();
        out.setContent("点击了菜单！");
        return out;
    }

    @Override
    public OutputMessage view(ViewEventMessage msg) {
        TextOutputMessage out = new TextOutputMessage();
        out.setContent("点击了链接！");
        return out;
    }

    @Override
    public OutputMessage scanCodePush(ScanCodePushEventMessage msg) {
        TextOutputMessage out = new TextOutputMessage();
        out.setContent("扫码！");
        return out;
    }

    @Override
    public OutputMessage scanCodeWaitMsg(ScanCodeWaitMsgEventMessage msg) {
        TextOutputMessage out = new TextOutputMessage();
        out.setContent("扫码等待中！");
        return out;
    }

    @Override
    public OutputMessage picSysPhoto(PicSysPhotoEventMessage msg) {
        TextOutputMessage out = new TextOutputMessage();
        out.setContent("发起拍照！");
        return out;
    }

    @Override
    public OutputMessage picPhotoOrAlbum(PicPhotoOrAlbumEventMessage msg) {
        TextOutputMessage out = new TextOutputMessage();
        out.setContent("选择相册！");
        return out;
    }

    @Override
    public OutputMessage picWeixin(PicWeixinEventMessage msg) {
        TextOutputMessage out = new TextOutputMessage();
        out.setContent("发送图片！");
        return out;
    }

    @Override
    public OutputMessage locationSelect(LocationSelectEventMessage msg) {
        TextOutputMessage out = new TextOutputMessage();
        out.setContent("选择地理位置！");
        return out;
    }
}
