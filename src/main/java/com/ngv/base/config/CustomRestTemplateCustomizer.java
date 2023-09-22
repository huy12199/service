package com.ngv.base.config;

import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


public class CustomRestTemplateCustomizer implements RestTemplateCustomizer {

  @Override
  public void customize(RestTemplate restTemplate) {
    restTemplate.setRequestFactory(
        new BufferingClientHttpRequestFactory(restTemplate.getRequestFactory()));
    restTemplate.getInterceptors().add(new CustomClientHttpRequestInterceptor());
  }
}
