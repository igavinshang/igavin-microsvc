## Sentinel接入Spring Cloud


1. **添加springcloud自动适配依赖 spring-cloud-starter-alibaba-sentinel**

> Spring Cloud Alibaba 默认为 Sentinel 整合了 Servlet、RestTemplate、FeignClient 和 Spring WebFlux。Sentinel 在 Spring Cloud 生态中，不仅补全了 Hystrix 在 Servlet 和 RestTemplate 这一块的空白，而且还完全兼容了 Hystrix 在 FeignClient 中限流降级的用法，并且支持运行时灵活地配置和调整限流降级规则。

2. **添加规则持久化依赖 sentinel-datasource-nacos**

在最佳实践中，生产环境推荐更常用的是 push 模式的数据源。对于 push 模式的数据源,如远程配置中心（ZooKeeper, Nacos, Apollo等等），本例使用nacos。

3. **配置文件yaml**

```
spring:
  application:
    name: microsvc-web
  cloud:
    sentinel:
      transport:
        dashboard: localhost:8858
      datasource:
        ds:
          nacos:
            server-addr: 172.24.29.87:8848
            username: nacos
            password: nacos
            groupId: DEFAULT_GROUP
            dataId: microsvc-web-sentinel
            namespace: dev_igavin
            rule-type: flow
```

4. **在nacos中新增限流规则配置，新建配置，选择json格式，Data ID 选择上面yaml配置中的dataId=microsvc-web-sentinel**
```
[
  {
    "resource": "helloworld",
    "limitApp": "default",
    "grade": 1,
    "count": 2,
    "strategy": 0,
    "controlBehavior": 0,
    "clusterMode": false
  },
   {
    "resource": "hello",
    "limitApp": "default",
    "grade": 1,
    "count": 1,
    "strategy": 0,
    "controlBehavior": 0,
    "clusterMode": false
  },
   {
    "resource": "/db/{id}",
    "limitApp": "default",
    "grade": 1,
    "count": 1,
    "strategy": 0,
    "controlBehavior": 0,
    "clusterMode": false
  }
]
```
5. **添加SentinelController 实验代码 ，包含资源名，限流，降级函数等**

 ```
@SentinelResource(value = "helloworld", blockHandler = "exceptionHandler", fallback = "helloFallback")
    @GetMapping("/helloworld")
    public String helloworld(long s) {
        return String.format("Helloworld at %d", s);
    }
```

