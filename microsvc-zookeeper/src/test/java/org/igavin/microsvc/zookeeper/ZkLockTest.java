package org.igavin.microsvc.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.igavin.microsvc.zookeeper.annotation.LockKeyParam;
import org.igavin.microsvc.zookeeper.annotation.ZooLock;
import org.igavin.microsvc.zookeeper.aspectj.ZooLockAspect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ZkLockTest {

  public Integer getCount() {
    return count;
  }

  private Integer count = 10000;
  private ExecutorService executorService = Executors.newFixedThreadPool(1000);

  @Autowired private CuratorFramework zkClient;

  /** 不使用分布式锁，程序结束查看count的值是否为0 */
  @Test
  public void test() throws InterruptedException {
    IntStream.range(0, 10000).forEach(i -> executorService.execute(()->doBuy(i)));
    TimeUnit.MINUTES.sleep(1);
    log.error("count值为{}", count);
  }

  /** 测试AOP分布式锁 */
  @Test
  public void testAopLock() throws InterruptedException {
    // 测试类中使用AOP需要手动代理
    ZkLockTest target = new ZkLockTest();
    AspectJProxyFactory factory = new AspectJProxyFactory(target);
    ZooLockAspect aspect = new ZooLockAspect(zkClient);
    factory.addAspect(aspect);
    ZkLockTest proxy = factory.getProxy();
    IntStream.range(0, 10000).forEach(i -> executorService.execute(() -> proxy.aopBuy(i)));
    TimeUnit.MINUTES.sleep(1);
    log.error("count值为{}", proxy.getCount());
  }

  /** 测试手动加锁 */
  @Test
  public void testManualLock() throws InterruptedException {
    IntStream.range(0, 10000).forEach(i -> executorService.execute(()->manualBuy(i)));
    TimeUnit.MINUTES.sleep(1);
    log.error("count值为{}", count);
  }

  @ZooLock(key = "buy", timeout = 1, timeUnit = TimeUnit.MINUTES)
  public void aopBuy(int userId) {
    log.info("{} 正在出库。。。", userId);
    doBuy(userId);
    log.info("{} 扣库存成功。。。", userId);
  }

  public void manualBuy(int userid) {
    String lockPath = "/buy";
    log.info("userid = {} try to buy sth.",userid);
    try {
      InterProcessMutex lock = new InterProcessMutex(zkClient, lockPath);
      try {
        if (lock.acquire(1, TimeUnit.MINUTES)) {
          doBuy(userid);
          log.info("userid = {} buy successfully!",userid);
        }
      } finally {
        lock.release();
      }
    } catch (Exception e) {
      log.error("userid = {} zk error:{}",userid ,e.getMessage());
    }
  }

  public void doBuy(int userid) {
    count--;
    log.info("userid = {},count值为{}",userid, count);
  }
}
