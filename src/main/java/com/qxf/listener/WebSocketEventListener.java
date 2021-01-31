package com.qxf.listener;

import com.qxf.entity.SysUser;
import com.qxf.service.SysUserService;
import com.qxf.util.RedisUtil;
import com.qxf.util.UserConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import javax.annotation.Resource;
import java.security.Principal;

/**
 * @ClassName WebSocketEventListener
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2021/1/30 19:24
 **/
@Component
public class WebSocketEventListener {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    RedisUtil redisUtil;

    @Resource
    private SysUserService userService;

    private Logger log = LoggerFactory.getLogger(WebSocketEventListener.class);
    /**
     * 建立连接监听
     *
     * @param sessionConnectedEvent
     */
    @EventListener
    public void handleConnectListener(SessionConnectedEvent sessionConnectedEvent){
        log.info("建立连接 -> {}", sessionConnectedEvent);
        Authentication authentication = (Authentication) sessionConnectedEvent.getUser();
        if (authentication != null){
            SysUser user = (SysUser) authentication.getPrincipal();
            // 更新状态为在线
            redisUtil.setOnline(user.getId(), UserConstant.ONLINE);
            userService.userOnline(user.getId());
            log.info("用户 {} 上线了",user.getUsername());
            //广播系统通知消息
            simpMessagingTemplate.convertAndSend("/topic/online/"+user.getId(),"用户【"+user.getUsername()+"】上线了");
        }
    }

    /**
     * 断开连接监听
     *
     * @param sessionDisconnectEvent
     */
    @EventListener
    public void handleDisconnectListener(SessionDisconnectEvent sessionDisconnectEvent) {
        log.info("断开连接 -> {}", sessionDisconnectEvent);
        Authentication authentication = (Authentication) sessionDisconnectEvent.getUser();
        if (authentication != null){
            SysUser user = (SysUser) authentication.getPrincipal();
            // 更新用户状态为离线
            redisUtil.setOnline(user.getId(), UserConstant.OFFLINE);
            userService.userOffline(user.getId());
            log.info("用户 {} 下线了",user.getUsername());
            //广播系统通知消息
            simpMessagingTemplate.convertAndSend("/topic/online/"+user.getId(),"用户【"+user.getUsername()+"】下线了");
        }
    }

    /**
     * 订阅监听
     *
     * @param sessionSubscribeEvent
     */
    @EventListener
    public void handleSubscribeListener(SessionSubscribeEvent sessionSubscribeEvent){
        log.info("新的订阅 -> {}", sessionSubscribeEvent.getMessage().getHeaders().get("nativeHeaders"));
    }


}
