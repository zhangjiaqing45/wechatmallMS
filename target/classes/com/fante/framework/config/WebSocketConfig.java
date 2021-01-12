package com.fante.framework.config;

import com.fante.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description: WebSocket配置
 * 通过EnableWebSocketMessageBroker开启使用STOMP协议来传输基于代理(message broker)的消息,
 * 这时控制器支持使用@MessageMapping,就像使用@RequestMapping一样
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final String SEPARATOR = ",";

    @Value("${webSocket.endPoints}")
    private String endPoints;

    @Value("${webSocket.allowedOrigins}")
    private String allowedOrigins;

    @Value("${webSocket.simpleBroker}")
    private String simpleBroker;

    @Value("${webSocket.appDestPrefix}")
    private String appDestPrefix;

    @Value("${webSocket.userDestPrefix}")
    private String userDestPrefix;

    /**
     * 注册STOMP协议的节点(endpoint),并映射指定的url
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 注册STOMP的endpoint,并指定使用SockJS协议
        registry.addEndpoint(StringUtils.split(endPoints, SEPARATOR))
                .setAllowedOrigins(StringUtils.split(allowedOrigins, SEPARATOR))
                .withSockJS();
    }

    /**
     * 配置消息代理
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 配置消息代理，在指定域上可以向客户端发消息
        registry.enableSimpleBroker(StringUtils.split(simpleBroker, SEPARATOR));
        // 客户单向服务器端发送时的主题上面需要加appDestPrefix作为前缀
        registry.setApplicationDestinationPrefixes(appDestPrefix);
        // 给指定用户发送一对一的主题前缀
        registry.setUserDestinationPrefix(userDestPrefix);
    }
}
