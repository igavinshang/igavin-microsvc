package org.igavin.microsvc.trace.web.controller;

import java.util.concurrent.ExecutorService;
import javax.annotation.Resource;
import org.apache.skywalking.apm.toolkit.trace.RunnableWrapper;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Tags;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.igavin.microsvc.trace.web.api.WebApi;
import org.igavin.microsvc.trace.web.domain.ThreadInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  final static Logger logger = LoggerFactory.getLogger(HomeController.class);

  @Autowired
  private WebApi webApi;

  @Resource(name = "defaultPool")
  private ExecutorService defaultPool;

  @RequestMapping("/username")
  public String getUserName(@RequestParam("id") Long id) {

    String traceId = TraceContext.traceId();
    logger.info("microsvc-trace-web/username:traceId="+traceId);
    return webApi.getUserName(id);
  }

  @RequestMapping("/echo")
  public String echo() {
    logger.info("microsvc-trace-web/echo");
    return webApi.echo();
  }


  @RequestMapping("async/echo")
  public String asyncEcho(){

    for(int i=0;i<5;i++) {

      // 【追踪子线程的信息】
      // 使用@TraceCrossThread注解或使用SupplierWrapper/RunnableWrapper/TraceCrossThread
      defaultPool.execute(
          new RunnableWrapper(
              () -> {
                myTrace(Thread.currentThread().getName());
                logger.info("microsvc-trace-web/async/echo");
                webApi.echo();
              }));
    }
    return "ok";
  }

  @Trace(operationName = "添加自定义的方法")
  @Tags({
      @Tag(key = "从方法参数中获取值", value = "arg[0]"),
      @Tag(key = "从返回值中获取值", value = "returnedObj.name")
  })
  private ThreadInfo myTrace(String name) {
    logger.info("如果此方法没有被SkyWalking收集，但是又需要被收集到，可以加上@Trace注解");
    ThreadInfo threadInfo = new ThreadInfo();
    threadInfo.setName(name);
    return threadInfo;
  }
}