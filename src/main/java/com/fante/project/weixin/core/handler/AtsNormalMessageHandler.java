package com.fante.project.weixin.core.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.weixin4j.model.message.OutputMessage;
import org.weixin4j.model.message.normal.*;
import org.weixin4j.model.message.output.TextOutputMessage;
import org.weixin4j.spi.INormalMessageHandler;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description: 自定义普通消息处理器
 */
@Component
public class AtsNormalMessageHandler implements INormalMessageHandler {

    private static Logger log = LoggerFactory.getLogger(AtsEventMessageHandler.class);

    @Override
    public OutputMessage textTypeMsg(TextInputMessage msg) {
        TextOutputMessage out = new TextOutputMessage();
        out.setContent("文本消息: " + msg.getContent());
        return out;
    }

    @Override
    public OutputMessage imageTypeMsg(ImageInputMessage msg) {
        TextOutputMessage out = new TextOutputMessage();
        out.setContent("图片消息: " + msg.getPicUrl());
        return out;
    }

    @Override
    public OutputMessage voiceTypeMsg(VoiceInputMessage msg) {
        TextOutputMessage out = new TextOutputMessage();
        out.setContent("语音消息: " + msg.getMediaId());
        return out;
    }

    @Override
    public OutputMessage videoTypeMsg(VideoInputMessage msg) {
        TextOutputMessage out = new TextOutputMessage();
        out.setContent("视频消息: " + msg.getMediaId());
        return out;
    }

    @Override
    public OutputMessage shortvideoTypeMsg(ShortVideoInputMessage msg) {
        TextOutputMessage out = new TextOutputMessage();
        out.setContent("小视频消息: " + msg.getMediaId());
        return out;
    }

    @Override
    public OutputMessage locationTypeMsg(LocationInputMessage msg) {
        TextOutputMessage out = new TextOutputMessage();
        out.setContent("地理位置消息: " + msg.getLabel());
        return out;
    }

    @Override
    public OutputMessage linkTypeMsg(LinkInputMessage msg) {
        TextOutputMessage out = new TextOutputMessage();
        out.setContent("链接消息: " + msg.getUrl());
        return out;
    }
}
