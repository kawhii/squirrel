/*
 * 版权所有.(c)2008-2018.广州市森锐科技股份有限公司
 */

/*
 * 版权所有.(c)2008-2018.广州市森锐科技股份有限公司
 */

package com.kawhii.workflow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * aip配置，开发模式，测试模式才进行开放api
 *
 * @author Carl
 * @version 创建时间：2018/1/4
 * @since 1.0.0
 */
@Profile({"dev", "test"})
@Configuration
@EnableSwagger2
public class Swagger2Config {
    //http://springfox.github.io/springfox/docs/current/
    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .tags(new Tag("Pet Service", "All apis relating to pets"))
                ;
    }
}
