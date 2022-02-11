package org.igavin.microsvc.web.controller;


import java.util.Random;
import javax.annotation.Resource;
import org.igavin.microsvc.web.monitor.PrometheusCustomMonitor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

  @Resource
  private PrometheusCustomMonitor monitor;


  @RequestMapping("/order")
  public String order(@RequestParam(defaultValue = "0") String flag) throws Exception {
    // 统计下单次数
    monitor.getOrderCount().increment();
    if ("1".equals(flag)) {
      throw new Exception("出错啦");
    }
    Random random = new Random();
    int amount = random.nextInt(100);
    // 统计金额
    monitor.getAmountSum().record(amount);
    return "下单成功, 金额: " + amount;
  }
}