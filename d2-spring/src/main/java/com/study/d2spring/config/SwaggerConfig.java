package com.study.d2spring.config;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.http.HttpStatus;
import org.springframework.plugin.core.SimplePluginRegistry;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));

    }

    @Bean
    public Docket apiDocket(){
      return new Docket(DocumentationType.SWAGGER_2)
      .select()
      .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
      .paths(PathSelectors.any())
      .build()
      .useDefaultResponseMessages(false)
      .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("STUDY D2 API")
                .version("1.0")
                .build();
    }

    private List<ResponseMessage> globalResponseCommonMessage() {
        return Lists.newArrayList(
                response400BadRequest(),
                response401Unauthorized(),
                response403Forbidden(),
                response404NotFound(),
                response405MethodNotAllowed(),
                response415UnsupportedMediaType(),
                response500InternalServerError());
    }

    private Parameter getHeaderParam(String name, String type, String description) {
        return new ParameterBuilder()
                .name(name)
                .parameterType("header")
                .modelRef(new ModelRef(type))
                .description(description)
                .required(true)
                .build();
    }

    private Parameter getHeaderOptionalParam(String name, String type, String description) {
        return new ParameterBuilder()
                .name(name)
                .parameterType("header")
                .modelRef(new ModelRef(type))
                .description(description)
                .required(false)
                .build();
    }

    private List<ResponseMessage> globalResponseMessageGet() {
        List<ResponseMessage> resultList = Lists.newArrayList(response200Ok());
        resultList.addAll(globalResponseCommonMessage());
        return resultList;
    }

    private List<ResponseMessage> globalResponseMessagePost() {
        List<ResponseMessage> resultList = Lists.newArrayList(response201Created());
        resultList.addAll(globalResponseCommonMessage());
        return resultList;
    }

    private List<ResponseMessage> globalResponseMessagePatch() {
        List<ResponseMessage> resultList = Lists.newArrayList(response204NoContent());
        resultList.addAll(globalResponseCommonMessage());
        return resultList;
    }

    private List<ResponseMessage> globalResponseMessagePut() {
        List<ResponseMessage> resultList = Lists.newArrayList(response204NoContent());
        resultList.addAll(globalResponseCommonMessage());
        return resultList;
    }

    private List<ResponseMessage> globalResponseMessageDelete() {
        List<ResponseMessage> resultList = Lists.newArrayList(response204NoContent());
        resultList.addAll(globalResponseCommonMessage());
        return resultList;
    }

    private ResponseMessage response200Ok() {
        return new ResponseMessageBuilder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.toString())
                .build();
    }

    private ResponseMessage response201Created() {
        return new ResponseMessageBuilder()
                .code(HttpStatus.CREATED.value())
                .message(HttpStatus.CREATED.toString())
                .build();
    }

    private ResponseMessage response204NoContent() {
        return new ResponseMessageBuilder()
                .code(HttpStatus.NO_CONTENT.value())
                .message(HttpStatus.NO_CONTENT.toString())
                .build();
    }

    private ResponseMessage response400BadRequest() {
        return new ResponseMessageBuilder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.toString())
                //			.responseModel(modelError())
                .build();
    }

    private ResponseMessage response401Unauthorized() {
        return new ResponseMessageBuilder()
                .code(HttpStatus.UNAUTHORIZED.value())
                .message(HttpStatus.UNAUTHORIZED.toString())
                .build();
    }

    private ResponseMessage response403Forbidden() {
        return new ResponseMessageBuilder()
                .code(HttpStatus.FORBIDDEN.value())
                .message(HttpStatus.FORBIDDEN.toString())
                .build();
    }

    private ResponseMessage response405MethodNotAllowed() {
        return new ResponseMessageBuilder()
                .code(HttpStatus.METHOD_NOT_ALLOWED.value())
                .message(HttpStatus.METHOD_NOT_ALLOWED.toString())
                .build();
    }

    private ResponseMessage response404NotFound() {
        return new ResponseMessageBuilder()
                .code(HttpStatus.NOT_FOUND.value())
                .message(HttpStatus.NOT_FOUND.toString())
                .build();
    }

    private ResponseMessage response409Conflict() {
        return new ResponseMessageBuilder()
                .code(HttpStatus.CONFLICT.value())
                .message(HttpStatus.CONFLICT.toString())
                //			.responseModel(modelError())
                .build();
    }

    private ResponseMessage response415UnsupportedMediaType() {
        return new ResponseMessageBuilder()
                .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .message(HttpStatus.UNSUPPORTED_MEDIA_TYPE.toString())
                .build();
    }

    private ResponseMessage response500InternalServerError() {
        return new ResponseMessageBuilder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                //			.responseModel(modelError())
                .build();
    }
}
