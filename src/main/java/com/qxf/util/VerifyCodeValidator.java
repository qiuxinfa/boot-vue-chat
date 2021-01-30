package com.qxf.util;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @ClassName VerifyCodeValidator
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2021/1/21 21:39
 **/
public class VerifyCodeValidator {
    public static boolean validate(HttpServletRequest request,String code){
        LocalDateTime createTime = (LocalDateTime) request.getSession().getAttribute("verify_code_time");
        LocalDateTime now = LocalDateTime.now();
        // 验证码2分钟内有效
        if (Duration.between(createTime,now).getSeconds() > 120){
            request.getSession().removeAttribute("verify_code");
            return false;
        }
        //session中保存的验证码
        String verify_code = (String) request.getSession().getAttribute("verify_code");
        if (code == null || verify_code == null){
            return false;
        }
        //验证是否相同
        if (!code.toLowerCase().equals(verify_code.toLowerCase())){
            return false;
        }
        return true;
    }
}