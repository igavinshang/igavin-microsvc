package org.igavin.microsvc.trace.web.common;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DefaultThreadPool {


  @Bean(name = "defaultPool")
  public ExecutorService defaultPool(){
    ExecutorService pool = new ThreadPoolExecutor(
        2,
        3,
        1000,
        TimeUnit.MILLISECONDS,
        new ArrayBlockingQueue<Runnable>(10),
        Executors.defaultThreadFactory(),
        new ThreadPoolExecutor.AbortPolicy());
    return pool;
  }

}
