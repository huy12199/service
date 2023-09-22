package com.ngv.base.logging;

import com.ngv.base.constant.RequestConstant;
import java.util.HashSet;
import java.util.Set;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "core.logging")
public class LoggingProperties {

  private int requestMaxPayloadLength = 10000;

  private int responseMaxPayloadLength = 1000;

  private boolean defaultIgnoreLogUri = true;

  private Set<String> ignoreLogUri = new HashSet<>();

  private boolean excludeResponseBody = true;

  public int getRequestMaxPayloadLength() {
    return requestMaxPayloadLength;
  }

  public void setRequestMaxPayloadLength(int requestMaxPayloadLength) {
    this.requestMaxPayloadLength = requestMaxPayloadLength;
  }

  public int getResponseMaxPayloadLength() {
    return responseMaxPayloadLength;
  }

  public void setResponseMaxPayloadLength(int responseMaxPayloadLength) {
    this.responseMaxPayloadLength = responseMaxPayloadLength;
  }

  public boolean isDefaultIgnoreLogUri() {
    return defaultIgnoreLogUri;
  }

  public void setDefaultIgnoreLogUri(boolean defaultIgnoreLogUri) {
    this.defaultIgnoreLogUri = defaultIgnoreLogUri;
  }

  public Set<String> getIgnoreLogUri() {
    if (defaultIgnoreLogUri) {
      ignoreLogUri.addAll(RequestConstant.getWhiteListRequest());
    }
    return ignoreLogUri;
  }

  public void setIgnoreLogUri(Set<String> ignoreLogUri) {
    this.ignoreLogUri = ignoreLogUri;
  }

  public boolean isExcludeResponseBody() {
    return excludeResponseBody;
  }

  public void setExcludeResponseBody(boolean excludeResponseBody) {
    this.excludeResponseBody = excludeResponseBody;
  }
}
