package com.ngv.base.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ngv.base.httpClient.RestClient;
import com.ngv.base.utils.JsonUtils;
import java.util.List;
import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public abstract class BaseService {

  /**
   * Common logger for use in subclasses.
   */
  protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

  @Autowired
  protected ApplicationContext applicationContext;

  @Autowired
  protected ApplicationEventPublisher applicationEventPublisher;

  @Autowired
  protected ThreadPoolTaskExecutor taskExecutor;

  @Autowired
  protected RestClient restClient;
  ;
  @Autowired
  protected EntityManager entityManager;

  protected ObjectMapper objectMapper = JsonUtils.getMapper();

  protected HttpHeaders getHttpHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));
    return headers;
  }

}
