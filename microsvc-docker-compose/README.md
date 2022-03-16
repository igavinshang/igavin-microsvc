docker的目录外挂路径在 microsvc-docker-compose/data下面
各个应用的docker-compose.yml的路径都用的相对路径./data
启动从microsvc-docker-compose下启动
启动命令：
docker-compose - f docker-compose-networks.yml -f ./应用/docker-compose.yml up -d
这样，各个应用的外挂路径都会映射到./data中


-- TODO
kafka-宿主host配置独立出来




|  服务           |   服务名         |  端口     | 备注                                 |
|----------------|-----------------|-----------|-------------------------------------|
|  数据库         |   mysql         |  3306     | 支持canal配置           |
|  数据库         |   mysql         |  3307     | 支持nacos配置           |
|  搜索引擎中间件  |   elasticsearch |  9200     |  支持elk,skywalking     |
|  消息中间件      |   kafka        |  5672     |  zookeeper & kafka & kafka-manager & kafka-exporter  |
|  数据同步       |   canal         |  3000     |             |
|  注册与配置中心  |   nacos         |  8848     |  [启动文档](./nacos/README.md) |
|  日志收集中间件  |   elk           |  5601     | es & logstash & kibana & filebeat    |
|  数据可视化工具  |   grafana       |  3000     |               |
|  数据收集工具    |   prometheus    |  3000     |               |
|  监控告警工具    |   alertmanager  |  3000     |               |
|  链路追踪工具    |   skywalking    |  3000     | 公用           |
|  流量限流熔断    |   sentinel      |  3000     |               |
|  分布式事务      |   seata         |  3000     |               |
|  任务调度       |   xxl-job       |  28888     |  管理后台地址：http://localhost:28888/xxl-job-admin/     |