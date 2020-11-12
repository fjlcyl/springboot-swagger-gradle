package com.fjl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 通过createRestApi函数创建Docket的Bean之后，
 * apiInfo()用来创建该Api的基本信息（这些基本信息会展现在文档页面中）
 * select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现，
 * apis()函数扫描所有Controller中定义的API， 并产生文档内容（除了被@ApiIgnore指定的请求）
 * @return
 */

@Configuration//交给spring管理
@EnableSwagger2//swag配置类的初始化
public class SwaggerConfig {
 
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //是否开启 (true 开启  false隐藏。生产环境建议隐藏)
                .enable(true)
                .select()
                //扫描的路径包,设置basePackage会将包下的所有被@Api标记类的所有方法作为api
                .apis(RequestHandlerSelectors.basePackage("com"))
                //指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo(){
    	Contact contact=new Contact("fjl&cyl", "https://blog.csdn.net/weixin_44124385/", "192****293@qq.com");
        return new ApiInfoBuilder()
                //设置文档标题(API名称)
                .title("SpringBoot中使用Swagger2构建RESTful接口")
                //文档描述
                .description("接口说明")
                //服务条款URL
                //联系信息
                .contact(contact)
                //版本号
                .version("1.0.0")
                .build();
        
    }
}
