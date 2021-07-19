# 工程简介

### 项目端口号

- microsvc-web 9101
- microsvc-canal-job 9201
- microsvc-trace-web 9301

-----------------------
### 基础建设

##### microsvc-docker-compose
	--elasticsearch

    --elk
      --kibana 
	  --logstash
	  --filebeat
	  --elasticsearch

    --grafana
      --grafana
	  --prometheus

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
    
----------------------
TODO
microsvc-canal 
    --接收binlog消息
    --同步到mysql
    --同步到elasticsearch
    
microsvc-es
    --索引自动构建
    --索引同步
    --索引查询
    

