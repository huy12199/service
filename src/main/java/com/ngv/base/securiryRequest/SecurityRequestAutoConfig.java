package com.ngv.base.securiryRequest;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(SecurityRequestProperties.class)
public class SecurityRequestAutoConfig {}
