package com.ngv.base.securiryRequest;


public abstract class AbstractSecurityRequest {

  public abstract String encryptData(String decryptStr);

  public abstract String decryptData(String encryptStr);
}
