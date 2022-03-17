# 工程简介

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
	

