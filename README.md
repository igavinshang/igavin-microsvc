# 工程简介

### 项目端口号

- microsvc-web 9101
- microsvc-canal-job 9201
- microsvc-trace-web 9301
- microsvc-zookeeper 9401

-----------------------
### 基础建设

##### microsvc-docker-compose
	--elasticsearch
	--mysql

    --canal
      --README.md
      
    --elk
      --kibana 
	  --logstash
	  --filebeat
	  --elasticsearch
	  --README.md

    --grafana
      --grafana
	  --prometheus
	    --yaml
	        --kafka-exporter
	        --microsvc-web
	  
	--kafka
	  --zookeeper
	  --kafka
	  --kafka-manager
	  --kafka-exporter  #Kafka exporter for Prometheus

    --skywalking
	  --skywalking-oap-server
      --skywalking-ui
      --elasticsearch

    --sentinel
    

 
        
-----------------------
### 项目技术栈

#### microsvc-trace-web
    skywalking
    openfegin
   
#### microsvc-canal-job
    canal
    
#### microsvc-web
    actuator
      -- yml配置-运行监控指标写入 prometueus
    prometueus & grafana
      -- maven引入 & yml配置
    sentinel
      -- maven引入
    shell
      -- skywalking启动
#### microsvc-zookeeper
    Apache Curator zookeeper客户端
    Apache Curator是为ZooKeeper开发的一套Java客户端类库
----------------------
### 场景
#### microsvc-canal 
    -- 接收binlog消息
    -- 同步到mysql
    -- 同步到elasticsearch
    
 
#### microsvc-elasticsearch
    -- 索引构建
    -- 索引CURD
    
#### microsvc-zookeeper
    -- 分布式锁实现




