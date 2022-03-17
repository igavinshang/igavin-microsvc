### 项目数据可视化


### 各组件职责
 
1. prometheus：负责收集和存储时间序列数据

2. grafana：负责展示数据

3. alertmanager：定义更详细的告警规则，告警后将告警信息转发给 prometheus-webhook-dingtalk 的web服务

4. prometheus-webhook-dingtalk：负责告警消息的美化及从Prometheus AlertManager WebHooks生成DingTalk通知

5. node-exporter：负责暴露主机 metrics 数据给 prometheus，收集 host 硬件和操作系统数据。它将以容器方式运行在所有 host 上

6. cAdvisor：负责收集容器数据。它将以容器方式运行在所有 host 上

7. kafka-exporter:负责收集kafka metrics,地址 `http://localhost:9308/metrics`


### 项目加入prafana进行监控告警

1. springboot项目，例如microsvc-web，需要引入actuator,收集metrics


2. 可开发收集信息，示例见microsvc-web项目中OrderController中统计三个指标，在.alertmanager-rule.yml中配置告警规则


```
requests_error_total：请求失败数
order_request_count：订单请求书
order_amount_sum：订单金额
```

3. 订单异常 & 服务下线 会触发告警规则，通过钉钉进行通知
 


#### kafka-exporter问题

```
kafka-exporter 统计模板，好像有问题，通过修改Variables中的项
    -job(label_values(kafka_brokers, job))
    -instance(label_values(kafka_brokers, instance))
    -topic(label_values(kafka_topic_partition_current_offset{instance='$instance',topic!='__consumer_offsets',topic!='--kafka'}, topic))

```

------------------------

