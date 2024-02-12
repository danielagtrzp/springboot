package com.dan.coursespringboot.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


// import springfox.documentation.builders.PathSelectors;
// import springfox.documentation.builders.RequestHandlerSelectors;
// import springfox.documentation.spi.DocumentationType;
// import springfox.documentation.spring.web.plugins.Docket;
// import springfox.documentation.swagger2.annotations.EnableSwagger2;

 @Configuration
 public class SwaggerConfig {

//     @Bean
//     public Docket api(){
    
//         return new Docket(DocumentationType.SWAGGER_2)
//                         .select()
//                         .apis(RequestHandlerSelectors.any())
//                         .paths(PathSelectors.any())
//                         .build();

//         //Swagger Metadata: http://localhost:8080/v2/api-docs
//         //Swagger Uri URL: http://localhost:8080/swagger-ui.html
//     }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("course")
                //.pathsToMatch("/**") //para todos
                .pathsToMatch("/users/**")//solo users y orders
                .build();
                //http://localhost:8080/swagger-ui/index.html
                //http://localhost:8080/v3/api-docs
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                .title("Course API")
                .description("Spring boot fundamentals application")
                .version("v0.0.1")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                .description("SpringShop Wiki Documentation")
                .url("https://springshop.wiki.github.org/docs"));
    }
  
}
