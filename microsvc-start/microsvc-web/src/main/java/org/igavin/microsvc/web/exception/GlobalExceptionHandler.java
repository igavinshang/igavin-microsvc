package org.igavin.microsvc.web.exception;

import javax.annotation.Resource;
import org.igavin.microsvc.web.monitor.PrometheusCustomMonitor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

  @Resource
  private PrometheusCustomMonitor monitor;

  @ResponseBody
  @ExceptionHandler(value = Exception.class)
  public String handle(Exception e) {
    monitor.getRequestErrorCount().increment();
    return "error, message: " + e.getMessage();
  }
}