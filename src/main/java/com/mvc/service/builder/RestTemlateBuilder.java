package com.mvc.service.builder;

import lombok.Builder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Builder
public class RestTemlateBuilder {

//    private RestTemlateBuilder restTemlateBuilder;
//
//    private RestTemplate restTemplate;
//    private String url;
//    private HttpMethod httpMethod;
//    private HttpEntity httpEntity;
//    private Class aClass;
//
//    public RestTemlateBuilder() {
//        restTemlateBuilder = new RestTemlateBuilder();
//    }
//
//    public RestTemlateBuilder withUrl(String url){
//        this.url = url;
//        return this.restTemlateBuilder;
//    }
//
//    public RestTemlateBuilder withHttpMethod(HttpMethod httpMethod){
//        this.httpMethod = httpMethod;
//        return this.restTemlateBuilder;
//    }
//
//    public RestTemlateBuilder withHttpEntity(HttpEntity httpEntity){
//        this.httpEntity = httpEntity;
//        return this.restTemlateBuilder;
//    }
//
//    public RestTemlateBuilder withClass(Class aClass){
//        this.aClass = aClass;
//        return this.restTemlateBuilder;
//    }
//
//    public ResponseEntity buildForExchange(){
//        restTemplate = new RestTemplate();
//        return restTemplate.exchange(
//                this.url,
//                this.httpMethod,
//                this.httpEntity,
//                this.aClass);
//    }
}
