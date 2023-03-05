package com.sunflashtech.interview.configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String AUTH = "Authorization";  

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)//
        .select()//
        .apis(RequestHandlerSelectors.any())//
        .paths(Predicates.not(PathSelectors.regex("/error")))//
        .build()//
        .apiInfo(metadata())//
        .useDefaultResponseMessages(false)//
        .securitySchemes(Collections.singletonList(apiKey()))
        .securityContexts(Collections.singletonList(securityContext()))
        .tags(
          new Tag("user", "Basic user CRUD operations"),
          new Tag("job", "Basic Job CRUD operations"),
          new Tag("interview", "Basic interview CRUD operations")
        ).genericModelSubstitutes(Optional.class);

  }

  private ApiInfo metadata() {
    return new ApiInfoBuilder()//
        .title("Sancom API Documentation application")//
        .description("Basic Job searching and interviewing application")//
        .version("1.0.0")//
        .license("MIT License").licenseUrl("http://opensource.org/licenses/MIT")//
        .contact(new Contact(null, null, "ryananyangu@gmail.com"))//
        .build();
  }
  
  private ApiKey apiKey() {
    return new ApiKey(AUTH, AUTH, "header");
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder()
        .securityReferences(defaultAuth())
        .forPaths(PathSelectors.any())
        .build();
  }

  private List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Arrays.asList(new SecurityReference(AUTH, authorizationScopes));
  }

}
