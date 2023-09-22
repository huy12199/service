package com.ngv.api.v1.oracleaq;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OracleAQReceiverService {

  @Autowired
  private OracleAQConnect oracleAQConnect;
  private final ExecutorService threadPool = Executors.newFixedThreadPool(10);

  @PostConstruct
  public void loadCached() {
    CompletableFuture.runAsync(() -> oracleAQConnect.consumeMessage(), threadPool);
  }

  @PreDestroy
  private void shutdown() {
    oracleAQConnect.isTheadStoped = true;
    log.info("oracleAQ consumer is shutdowned.");
  }

//  public class StartOracleThread extends Thread {
//
//    public void run() {
//      oracleAQConnect.consumeMessage();
//    }
//  }
}
