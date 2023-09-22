package com.ngv.base.exception;

import static com.ngv.base.utils.JsonUtils.toJson;

import com.ngv.base.data.ResponseUtils;
import com.ngv.base.logging.LoggingProperties;
import com.ngv.base.logging.LoggingUtil;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

  private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationEntryPoint.class);

  private final LoggingProperties loggingProperties;

  @Value("${spring.application.name}")
  private String serviceName;

  @Autowired
  public CustomAuthenticationEntryPoint(LoggingProperties loggingProperties) {
    this.loggingProperties = loggingProperties;
  }

  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException)
      throws IOException {
    LoggingUtil.logRequest(request, serviceName, loggingProperties);
    LOGGER.warn("You need to login first in order to perform this action.");
    var errorCode = CommonErrorCode.FORBIDDEN;
    response.setStatus(errorCode.getHttpStatus().value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response
        .getWriter()
        .print(
            toJson(
                ResponseUtils.getResponseDataError(errorCode.getCode(), errorCode.getMessage(), null)));
    LoggingUtil.logResponse(request, response, loggingProperties);
  }
}
