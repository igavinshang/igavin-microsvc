package org.igavin.microsvc.web.service.Impl;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.igavin.microsvc.web.service.EchoService;
import org.springframework.stereotype.Service;

@Service
public class EchoServiceImpl implements EchoService {


  /*
  * 方法定义成一个资源，通过service服务实现，调用方调用服务方法，达到限流降级效果生效
  * 如果方法资源直接和调用方在一个类里面实现，限流降级效果没生效，不知为何。
  * */
  @SentinelResource(value = "echoHello", blockHandler = "helloBlockHandler", fallback = "helloFallback")
  public String hello(Integer id){
    if (id == 1) {
      return String.format("echo: hello at %d", id);
    } else {
      System.out.println("echo: call hello error");
      throw new RuntimeException("echo: call hello error");
    }
  }

  /*
   * 【注意】blockHandler 函数会在原方法被限流/降级/系统保护的时候调用，而 fallback 函数会针对所有类型的异常
   * Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
   * */
  public String helloBlockHandler(Integer id, BlockException ex) {
    // Do some log here.
    ex.printStackTrace();
    return "echo: hello block," + ex.getClass().getName();
  }

  public String helloFallback(Integer id) {
    return String.format("echo: hello fallback %d", id);
  }


  @Override
  public String sayHello(Integer id) {
    String resourceName = "echoSayHello";
    Entry entry = null;
    try {
      // 若需要配置例外项，则传入的参数只支持基本类型。
      // EntryType 代表流量类型，其中系统规则只对 IN 类型的埋点生效
      // count 大多数情况都填 1，代表统计为一次调用。
      entry = SphU.entry(resourceName, EntryType.IN, 1, id);
      // Your logic here.
      if (id == 1) {
        return String.format("echo: sayHello at %d", id);
      } else {
        System.out.println("echo: call sayHello error");
        throw new RuntimeException("echo: call sayHello error");
      }
    } catch (BlockException ex) {
      // Handle request rejection.
      ex.printStackTrace();
      return "echo: sayHello block," + ex.getClass().getName();
    } catch (Exception ex) {
      // 若需要配置降级规则，需要通过这种方式记录业务异常
      Tracer.traceEntry(ex, entry);
      return String.format("echo: sayHello fallback %d", id);
    } finally {
      // 注意：exit 的时候也一定要带上对应的参数，否则可能会有统计错误。
      if (entry != null) {
        entry.exit(1, id);
      }
    }
  }

}
