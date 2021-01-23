package com.qxf.config;

import com.qxf.util.FileConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName MyWebConfigurer
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2021/1/20 21:11
 **/
@Configuration
public class MyWebConfigurer implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置资源的虚拟路径映射，使得可以访问硬盘资源
        registry.addResourceHandler(FileConstant.VIRTUAL_PATH+"**").addResourceLocations("file:" + FileConstant.ROOT_PATH);
    }

}