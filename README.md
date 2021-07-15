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
    prometueus & grafana
    sentinel
    
    