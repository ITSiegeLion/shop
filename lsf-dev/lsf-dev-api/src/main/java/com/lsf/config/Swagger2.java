package com.lsf.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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

@Configuration
@EnableSwagger2
@ConditionalOnProperty(prefix = "swagger2", value = {"enable"}, havingValue = "true")
public class Swagger2 {
    //请求地址：http://localhost:8088/swagger-ui.html
    //请求地址：http://localhost:8088/doc.html

    //swagger2核心配置 docket
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2) //指定api类型为swagger2
                .apiInfo(getApiInfo()) //用于定义api文档汇总信息
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lsf.controller")) //指定controller
                .paths(PathSelectors.any()) //所有controller
                .build();
    }

    private ApiInfo getApiInfo(){
        return new ApiInfoBuilder()
                .title("电商平台接口api")
                .contact(new Contact("lsf", //联系人信息
                        "https://www.lsf.com",
                        "1443153006@qq.com"))
                .description("为xxx电商平台提供的api文档")
                .version("1.0.1")
                .termsOfServiceUrl("https://") //网站地址
                .build();
    }

}
