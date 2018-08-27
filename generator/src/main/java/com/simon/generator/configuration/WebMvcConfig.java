package com.simon.generator.configuration;

import com.simon.common.Constant.Constant;
import com.simon.generator.interceptor.GlobalInterceptor;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * 静态资源配置
 * Created by guoshuai on 2018/8/26 0026.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport{

    /**
     * 视图解析器
     * @return
     */
    @Bean
    public InternalResourceViewResolver initViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/page/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }

    /**
     * 过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        // 字符编码
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding(Constant.ENCODING_UTF8);
        // 请求和响应都强制utf-8编码
        characterEncodingFilter.setForceEncoding(true);

        FilterRegistrationBean registration = new FilterRegistrationBean();
        // 注入过滤器
        registration.setFilter(characterEncodingFilter);
        // 拦截规则
        registration.addUrlPatterns("/*");
        // 过滤器名称
        registration.setName("CharacterEncodingFilter");
        // 过滤器顺序
        registration.setOrder(1);
        return registration;
    }


    /**
     * 解决跨域问题
     * @param registry
     */
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods(
                        RequestMethod.GET.name(),
                        RequestMethod.HEAD.name(),
                        RequestMethod.POST.name(),
                        RequestMethod.PUT.name(),
                        RequestMethod.PATCH.name(),
                        RequestMethod.DELETE.name(),
                        RequestMethod.OPTIONS.name(),
                        RequestMethod.TRACE.name())
                .maxAge(3600);
    }

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 全局拦截器
        registry.addInterceptor(new GlobalInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    /**
     * 静态资源处理
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }


}
