package com.qxf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @ClassName WebSocketConfig
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2021/1/21 20:18
 **/
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    /**
     * 注册stomp站点
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry.addEndpoint("/qxf/chat").setAllowedOrigins("*").withSockJS();

    }

    /**
     * 注册拦截"/topic","/queue"的消息
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic","/queue");

    }
}