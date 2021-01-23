package com.qxf.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qxf.util.ResultUtil;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @ClassName VerificationCodeFilter
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2021/1/21 21:39
 **/
@Component
public class VerificationCodeFilter extends GenericFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //如果是登录请求则拦截
        if ("POST".equals(request.getMethod())&&"/user/login".equals(request.getServletPath())){
            //用户输入的验证码
            String code = request.getParameter("code");
            //session中保存的验证码
            String verify_code = (String) request.getSession().getAttribute("verify_code");
            response.setContentType("application/json;charset=utf-8");
//            PrintWriter writer = response.getWriter();
            Writer writer= new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
            //验证session中保存是否存在
            try {
                //验证是否相同
                if (!code.toLowerCase().equals(verify_code.toLowerCase())){
                    //输出json
                    writer.write(new ObjectMapper().writeValueAsString( ResultUtil.error("验证码错误！")));
                    return;
                }else {
                    filterChain.doFilter(request,response);
                }
            }catch (NullPointerException e){
                writer.write(new ObjectMapper().writeValueAsString(ResultUtil.error("请求异常，请重新请求！")));
            }finally {
                writer.flush();
                writer.close();
            }
        }
        else {
            filterChain.doFilter(request,response);
        }
    }
}