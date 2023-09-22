package com.ngv.api.v1.oracleaq;

import com.ngv.api.v1.event.Event;
import com.ngv.base.event.EventInfo;
import java.util.Properties;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;
import oracle.jms.AQjmsFactory;
import oracle.jms.AQjmsSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OracleAQConnect {

  private final int timeout = 50000;
  private QueueConnection QCon = null;
  public boolean isTheadStoped = false;

  @Autowired
  protected ApplicationEventPublisher applicationEventPublisher;
  @Value("${oracle-queue.queueName}")
  public String queueName;
  @Value("${spring.datasource.url}")
  public String url;
  @Value("${spring.datasource.username}")
  public String username;
  @Value("${spring.datasource.password}")
  public String password;

  private QueueConnection getConnection() {
    Properties info = new Properties();
    info.put("user", username);
    info.put("password", password);
    info.put("internal_logon", "normal");
    QueueConnectionFactory QFac;
    try {
      log.info("Start get connection queue");
      QFac = AQjmsFactory.getQueueConnectionFactory(url, info);
      // create connection
      QCon = QFac.createQueueConnection();
      log.info("End get connection queue SUCCESS");
    } catch (Exception e) {
      log.error("Exception Get conection queue");
      e.printStackTrace();
    }
    return QCon;
  }

  public void consumeMessage() {
    Queue queue;
    Session session = null;
    MessageConsumer consumer = null;
    QueueConnection QConnect = null;
    try {
      QConnect = getConnection();
      session = QConnect.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
      QConnect.start();
      queue = ((AQjmsSession) session).getQueue(username, queueName);
      consumer = session.createConsumer(queue);
      while (!isTheadStoped) {
        String msg = receiverMessage(consumer);
        if (StringUtils.isEmpty(msg)) {
          continue;
        }
        // do something with msg text
        log.info("received message from oracleaq {}", msg);
        String[] data = msg.split(":");
        applicationEventPublisher.publishEvent(new EventInfo(msg, Event.valueOf(data[0])));

      }
    } catch (Exception e) {
      log.error("Exception consumeMessage");
      e.printStackTrace();
    } finally {
      try {
        consumer.close();
        session.close();
        QConnect.close();
      } catch (JMSException e) {
        e.printStackTrace();
      }
    }
  }

  public String receiverMessage(MessageConsumer consumer) {
    try {
      TextMessage msg = (TextMessage) consumer.receive(timeout);
      String msgContent = null;
      if (msg != null) {
        msgContent = msg.getText();
      }
      return msgContent;
    } catch (Exception e) {
      log.error("Exception receiverMessage");
      e.printStackTrace();
    }
    return null;

  }

}