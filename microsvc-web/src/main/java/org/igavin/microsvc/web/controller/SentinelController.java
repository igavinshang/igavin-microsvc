package org.igavin.microsvc.web.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sentinel")
public class SentinelController {

    // helloworld
    @SentinelResource(value = "helloworld", blockHandler = "exceptionHandler", fallback = "helloFallback")
    @GetMapping("/helloworld/{id}")
    public String helloworld(Integer id) {
        return String.format("Helloworld at %d", id);
    }

    // 原函数
    @SentinelResource(value = "hello", blockHandler = "exceptionHandler", fallback = "helloFallback")
    @GetMapping("/hello/{id}")
    public String hello(Integer id) {
        return String.format("Hello at %d", id);
    }

    // Fallback 函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
    public String helloFallback(Integer id) {
        return String.format("Halooooo %d", id);
    }

    // Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
    public String exceptionHandler(Integer id, BlockException ex) {
        // Do some log here.
        ex.printStackTrace();
        return "Oops, error occurred at " + id;
    }

    // 这里单独演示 blockHandlerClass 的配置.
    // 对应的 `handleException` 函数需要位于 `ExceptionUtil` 类中，并且必须为 public static 函数.
    @SentinelResource(value = "test", blockHandler = "handleException", blockHandlerClass = {ExceptionUtil.class})
    @GetMapping("/test")
    public void test() {
        System.out.println("Test");
    }


    public static class ExceptionUtil {
        public static String handleException(String s, BlockException ex) {
            // Do some log here.
            // ex.printStackTrace();
            System.out.println("被限流，无法访问接口");
            return "被限流，无法访问接口";
        }

        /**
         * 方法参数列表需要和原函数一致，或者可以额外多一个 Throwable 类型的参数用于接收对应的异常。
         * fallback 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 fallbackClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
         * @param s
         * @return
         */
        public static String helloFallback(String s) {
            System.out.println("熔断功能被开启");
            return "熔断功能被开启";
        }
    }

}

