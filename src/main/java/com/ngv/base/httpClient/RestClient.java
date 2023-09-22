package com.ngv.base.httpClient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class RestClient {
    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    public <T> T execute(String urlTemplate, HttpMethod method, HttpEntity<Object> eHeader, Class<T> ofClass) {
        String logPrefix = "getAPI";
        ResponseEntity<T> responseEntity = restTemplate.exchange(urlTemplate, method, eHeader, ofClass);
        log.info(logPrefix + "|Execute API SUCCESS result|" + responseEntity.getBody());
        return responseEntity.getBody();
    }

}
