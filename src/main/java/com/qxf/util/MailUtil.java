package com.qxf.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @ClassName MailUtil
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2021/2/5 0:08
 **/
@Component
public class MailUtil {

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Value("${spring.mail.username}")
    private String mailFromUsername;

    @Async
    public void sendMailAfterRegister(String toMailAddress,String title,String content){
        //简单邮件
        SimpleMailMessage message = new SimpleMailMessage();
        //邮件标题
        message.setSubject(title);
        //邮件内容
        message.setText(content);
        //发送者
        message.setFrom(mailFromUsername);
        //接收者
        message.setTo(toMailAddress);
        //发送邮件
        mailSender.send(message);
    }

    @Async
    public void sendFeedback(String fromEmailAddress,String content){
        //简单邮件
        SimpleMailMessage message = new SimpleMailMessage();
        //邮件标题
        message.setSubject("用户反馈");
        //邮件内容
        message.setText(content);
        //发送者
        message.setFrom(fromEmailAddress);
        //接收者
        message.setTo(mailFromUsername);
        //发送邮件
        mailSender.send(message);
    }

}
