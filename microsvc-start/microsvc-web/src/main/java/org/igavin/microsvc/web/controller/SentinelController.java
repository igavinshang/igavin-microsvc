package org.igavin.microsvc.web.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.igavin.microsvc.web.api.WebApi;
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

  @Autowired private WebApi webApi;

  // 原函数
  @SentinelResource(value = "hello", blockHandler = "helloBlockHandler", fallback = "helloFallback")
  @GetMapping("/hello/{id}")
  public String hello(@PathVariable("id") Integer id) throws Exception {
    if (id == 1) {
      return String.format("Hello at %d", id);
    } else {
      System.out.println("call hello error");
      throw new Exception("call hello error");
    }
  }

  /*
   * 【注意】blockHandler 函数会在原方法被限流/降级/系统保护的时候调用，而 fallback 函数会针对所有类型的异常
   * Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
   * */
  public String helloBlockHandler(Integer id, BlockException ex) {
    // Do some log here.
    ex.printStackTrace();
    return "hello block," + ex.getClass().getName();
  }

  public String helloFallback(Integer id) {
    return String.format("hello fallback %d", id);
  }

  public String helloMethod(Integer id) {
    String resourceName = "helloMethod";
    Entry entry = null;
    try {
      // 若需要配置例外项，则传入的参数只支持基本类型。
      // EntryType 代表流量类型，其中系统规则只对 IN 类型的埋点生效
      // count 大多数情况都填 1，代表统计为一次调用。
      entry = SphU.entry(resourceName, EntryType.IN, 1, id);
      // Your logic here.
      if (id == 1) {
        return String.format("HelloMethod at %d", id);
      } else {
        System.out.println("call helloMethod error");
        throw new RuntimeException("call helloMethod error");
      }
    } catch (BlockException ex) {
      // Handle request rejection.
      ex.printStackTrace();
      return "helloMethod block," + ex.getClass().getName();
    } catch (Exception ex) {
      // 若需要配置降级规则，需要通过这种方式记录业务异常
      Tracer.traceEntry(ex, entry);
      return String.format("helloMethod 降级 %d", id);
    } finally {
      // 注意：exit 的时候也一定要带上对应的参数，否则可能会有统计错误。
      if (entry != null) {
        entry.exit(1, id);
      }
    }
  }

  // 这里单独演示 blockHandlerClass 的配置.
  // 对应的 `handleException` 函数需要位于 `ExceptionUtil` 类中，并且必须为 public static 函数.
  @SentinelResource(value = "helloworld", blockHandler = "blockHandler", fallback = "fallback",
      entryType = EntryType.OUT, blockHandlerClass = {ExceptionUtil.class})
  @GetMapping("/helloworld/{id}")
  public String helloworld(@PathVariable("id") Integer id) {
    switch (id) {
      case 2:
        {
          return webApi.hello(id);
        }
      case 3:
        {
          return this.helloMethod(id);
        }
    }
    return webApi.hello(id);
  }

  public static class ExceptionUtil {
    public static String blockHandler(Integer id, BlockException ex) {
      // Do some log here.
      ex.printStackTrace();
      return "helloworld block," + ex.getClass().getName();
    }

    /**
     * 方法参数列表需要和原函数一致，或者可以额外多一个 Throwable 类型的参数用于接收对应的异常。 fallback
     * 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 fallbackClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
     *
     * @return
     */
    public static String fallback(Integer id) {
      return String.format("helloworld fallback %d", id);
    }
  }
}
