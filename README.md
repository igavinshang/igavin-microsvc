# 工程简介

### about
```
基于docker,docker-compose部署目前微服务所需主流基础服务，包括 
日志收集组件elk(elasticsearch & logstash & kibana & filebeat),
链路追踪skywalking,
项目可视化组件【收集/监控/告警】（grafana & prometheus & alertmanager），
消息中间件kafka（kafka & kafka-manager），
分布式基础组件zookeeper，
注册与配置中心nacos，
搜索引擎中间件elasticsearch，
流量限流熔断sentinel，
分布式事务seata，
mysql数据同步canal，
任务调度xxl-job。
使用spring cloud最简单话实现组件使用示例。持续中...
```

### 项目

- microsvc-web 9101
- microsvc-canal-job 9201
- microsvc-trace-web 9301
- microsvc-zookeeper 9401

-----------------------
### 基础服务

##### microsvc-docker-compose  [启动文档](./microsvc-docker-compose/README.md)   
    --elk
      --elasticsearch
      --logstash
      --kibana 
	  --filebeat
	  	  	  
    --skywalking
	  --skywalking-oap-server
      --skywalking-ui
      --elasticsearch

    --grafana
      --grafana
      --alertmanager
	  --prometheus
	  
	  
	--kafka
	  --zookeeper
	  --kafka
	  --kafka-manager
	  --kafka-exporter  #Kafka exporter for Prometheus

    
    --mysql
    --nacos
    --seata
	--elasticsearch
    --canal
    --xxl-job
    --sentinel-dashboard
        

   
        
-----------------------
### 项目技术栈

#### microsvc-trace-web
    skywalking
    openfegin
   

    
#### microsvc-web
    actuator
      -- 引入actuator，收集运行监控指标到prometueus

    prometueus开发自定义指标
      -- 见OrderController

    sentinel
      -- maven引入
      -- nacos持久化限流熔断规则
      -- 项目接入流程参照/microsvc-web/README-microsvc-web-sentinel.md

    shell
      -- skywalking启动


#### microsvc-canal-job
    canal同步场景
    -- 接收binlog消息
    -- 同步到mysql
    -- 同步到elasticsearch
    
 
#### microsvc-elasticsearch
	全文检索示例
    -- 索引构建
    -- 索引CURD

#### microsvc-zookeeper
    -- 【分布式锁实现】
    -- Apache Curator zookeeper客户端
    -- Apache Curator是为ZooKeeper开发的一套Java客户端类库
 
---------------------


### K8s

#### docker-desktop-k8s-setup

	--k8s mac本地运行环境安装脚本

#### microsvc-k8s/microsvc-helloworld

	--k8s-helloworld

#### microsvc-k8s/microsvc-app

	--Namespace
	--Deployment
	--Service
	--Ingress
	

