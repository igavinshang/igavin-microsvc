##  docker-compose 启动基础服务


### 注意事项


1. 在microsvc-docker-compose目录下启动基础服务

``` 启动命令
docker-compose - f docker-compose-networks.yml -f ./基础服务目录/docker-compose.yml up -d

```
2. docker基础服务外挂路径在/data下面，相对路径../data



### 基础服务列表


|  服务           				|   服务名         |  端口      | 备注                           |
|------------------------------|-----------------|-----------|--------------------------------|
|  数据库         				|   mysql         |  3306     | 支持canal配置                   |
|  数据库         				|   mysql         |  3307     | 支持nacos配                     |
|  搜索引擎中间件  				|   elasticsearch |  9200     | 支持elk,skywalking              | 
|  消息中间件     				|   kafka         |  9092     | 一揽子组件包括 zookeeper & kafka & kafka-manager & kafka-exporter  |
|  注册与配置中心  				|   nacos         |  8848     | [启动文档](./nacos/README.md)  |
|  日志收集中间件  				|   elk           |  5601     | 一揽子组件包括 es & logstash & kibana & filebeat [启动文档](./elk/README.md)   |
|  数据可视化工具(收集/监控/告警)	|   grafana       |  3000     | 一揽子组件包括 grafana & prometheus & alertmanager & dingtalk  [使用文档](./grafana/README.md)    |
|  链路追踪工具    				|   skywalking    |  18080    | 应用实践场景见microsvc-web中shell/start.sh [agent注入脚本](../microsvc-start/microsvc-web/src/main/shell/start.sh) |
|  流量限流熔断    				|   sentinel      |  8858     | 应用实践场景见microsvc-web，[使用文档](../microsvc-start/microsvc-web/README-sentinel.md)                     |
|  分布式事务      				|   seata         |  8091     |                             |
|  任务调度       				|   xxl-job       |  28888    |  管理后台地址：http://localhost:28888/xxl-job-admin/     |
|  数据同步       				|   canal         |  NULL     |                             |