package com.simon.generator.configuration;

import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.resource.ContentVersionStrategy;
import org.springframework.web.servlet.resource.VersionResourceResolver;

/**
 * 静态资源配置
 * Created by guoshuai on 2018/8/26 0026.
 */

public class ResourceConfig extends WebMvcConfigurationSupport{

    /**
     * 解决跨域问题
     * @param registry
     */
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
    }

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
    }

    /**
     * 添加视图解析器
     * @param registry
     */
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
    }

    /**
     * 配置内容裁决的一些选项
     * @param configurer
     */
    @Override
    protected void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        super.configureContentNegotiation(configurer);
    }

    /**
     * 静态资源处理
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        VersionResourceResolver versionResourceResolver = new VersionResourceResolver();
        versionResourceResolver.addVersionStrategy(
                new ContentVersionStrategy(), "/static/**");
        super.addResourceHandlers(registry);
    }


}
