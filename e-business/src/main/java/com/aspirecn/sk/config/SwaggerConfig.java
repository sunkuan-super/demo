package com.aspirecn.sk.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
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
 * @Author:liujiangtao
 * @Description:swagger文档配置类
 * @Date:2019/10/18
 * @Version:1.0
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {

    private ApiInfo apiInfo() {
        Contact contact = new Contact("孙宽", "http:localhost:80", "sunkuan@aspirecn.com");
        return new ApiInfoBuilder()
                .title("招行重构项目——数据报表模块构建RESTful API")
                .description("招行重构-数据报表")
                .contact(contact)
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }

    @Bean
    public Docket createRestApi4() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("前端--报表统计--接口")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.aspirecn.sk.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
