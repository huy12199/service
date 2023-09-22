package com.ngv.base.event;

import com.ngv.base.exception.BaseException;
import com.ngv.base.constant.RequestConstant;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.TaskExecutor;


public abstract class DrivenEventListener {

  protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

  private final ApplicationContext applicationContext;

  private final TaskExecutor executor;

  protected DrivenEventListener(ApplicationContext applicationContext) {
    this(applicationContext, null);
  }

  protected DrivenEventListener(ApplicationContext applicationContext, TaskExecutor executor) {
    this.applicationContext = applicationContext;
    this.executor = executor;
  }

  protected abstract void processHandleErrorEventAsync(EventInfo eventInfo);

  protected abstract void processLogHandleEventAsync(EventInfo eventInfo);

  @EventListener
  public void handleEvent(EventInfo eventInfo) {
    if (!eventInfo.isSync()) {
      executor.execute(
          () -> {
            ThreadContext.put(RequestConstant.REQUEST_ID, eventInfo.getId());
            processLogHandleEventAsync(eventInfo);
            routerEventHandle(eventInfo);
            ThreadContext.clearAll();
          });
    } else {
      routerEventHandle(eventInfo);
    }
  }

  private void routerEventHandle(EventInfo eventInfo) {
    var event = eventInfo.getEvent();
    if (event == null) {
      LOGGER.warn("The event to be handled was not found");
      return;
    }
    LOGGER.info(
        String.format("Start handle event: %s id: %s", event.getEventName(), eventInfo.getId()));
    var handleEventClassName = event.getHandleEventBeanName();
    var handleEventFunctionName = event.getHandleEventFunctionName();
    try {
      Object obj = applicationContext.getBean(handleEventClassName);
      var opt =
          Arrays.stream(obj.getClass().getMethods())
              .filter(v -> v.getName().equals(handleEventFunctionName))
              .findFirst();
      if (opt.isPresent()) {
        invokeHandleMethod(0, eventInfo.getRetry() + 1, opt.get(), obj, eventInfo);
      } else {
        LOGGER.warn(String.format("Method %s not found", handleEventFunctionName));
      }
    } catch (BeansException e) {
      LOGGER.warn(String.format("Bean %s not found", handleEventClassName), e);
    }
    LOGGER.info(
        String.format("End handle event: %s id: %s", event.getEventName(), eventInfo.getId()));
  }

  private void invokeHandleMethod(
      int index, int totalNumberExec, Method m, Object obj, EventInfo eventInfo) {
    var event = eventInfo.getEvent();
    if (index >= totalNumberExec && !eventInfo.isSync()) {
      processHandleErrorEventAsync(eventInfo);
      return;
    }
    if (index > 0) {
      LOGGER.info(
          String.format("try the %sth event handling %s again", index, event.getEventName()));
    }
    try {
      m.invoke(obj, eventInfo.getWhat());
    } catch (InvocationTargetException e) {
      if (e.getTargetException() instanceof BaseException) {
        throw (BaseException) e.getTargetException();
      }
      LOGGER.error(String.format("handle event %s error", event.getEventName()), e);
      invokeHandleMethod(index + 1, totalNumberExec, m, obj, eventInfo);
    } catch (Exception e) {
      LOGGER.error(String.format("handle event %s error", event.getEventName()), e);
      invokeHandleMethod(index + 1, totalNumberExec, m, obj, eventInfo);
    }
  }
}
