package org.igavin.microsvc.web.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.igavin.microsvc.web.api.WebApi;
import org.igavin.microsvc.web.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 特别地，若 blockHandler 和 fallback 都进行了配置， 则被限流&降级而抛出 BlockException 时只会进入 blockHandler 处理逻辑。 若未配置
 * blockHandler、fallback 和 defaultFallback， 则被限流降级时会将 BlockException 直接抛出 （若方法本身未定义 throws
 * BlockException 则会被 JVM 包装一层 UndeclaredThrowableException）。
 */
@RestController
@RequestMapping("sentinel")
public class SentinelController {

  @Autowired
  private WebApi webApi;

  @Autowired
  private EchoService echoService;


  // 这里单独演示 blockHandlerClass 的配置.
  // 对应的 `handleException` 函数需要位于 `ExceptionUtil` 类中，并且必须为 public static 函数.
  @SentinelResource(value = "helloworld",
      blockHandler = "blockException", fallback = "fallbackException"
      , blockHandlerClass = {ExceptionUtil.class}
      , fallbackClass = {ExceptionUtil.class}
      )
  @GetMapping("/helloworld/{id}")
  public String helloworld(@PathVariable("id") Integer id){
    switch (id) {
      case 2:
        {
          return echoService.hello(id);
        }
      case 3:
        {
          return echoService.sayHello(id);
        }
    }
    return webApi.hello(id);
  }


  @SentinelResource(value = "hello",
      blockHandler = "blockException", fallback = "fallbackException"
      // block & fallback 分别是对应两个类都需要配置
      , blockHandlerClass = {ExceptionUtil.class}
      , fallbackClass = {ExceptionUtil.class}
      )
  @GetMapping("/hello/{id}")
  public String hello(@PathVariable("id") Integer id){
    if (id == 1) {
      return String.format("hello at %d", id);
    } else {
      System.out.println("call hello error");
      throw new RuntimeException("call hello error");

    }
  }


  public static class ExceptionUtil {
    public static String blockException(Integer id, BlockException ex) {
      // Do some log here.
      ex.printStackTrace();
      return "block," + ex.getClass().getName();
    }

    /**
     * 方法参数列表需要和原函数一致，或者可以额外多一个 Throwable 类型的参数用于接收对应的异常。 fallback
     * 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 fallbackClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
     *
     * @return
     */
    public static String fallbackException(Integer id) {
      return String.format("fallback at %d", id);
    }

  }
}
