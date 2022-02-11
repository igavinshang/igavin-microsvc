### 项目加入prafana进行监控

1.springboot项目，例如microsvc-web，需要引入actuator，参见印象笔记
2.中间件，例如kafka,引入kafka-exporter，收集metrics，地址http://localhost:9308/metrics

prafana中添加监控看板
kafka-exporter统计模板，好像有问题，通过修改Variables中的项
    -job(label_values(kafka_brokers, job))
    -instance(label_values(kafka_brokers, instance))
    -topic(label_values(kafka_topic_partition_current_offset{instance='$instance',topic!='__consumer_offsets',topic!='--kafka'}, topic))
然后看到统计数据


------------------------
### dingtalk监控告警

Prometheus负责收集数据，Grafana负责展示数据。其中采用Prometheus 中的 Exporter含：
grafana：负责展示数据
prometheus：负责收集和存储时间序列数据
node-exporter：负责暴露主机 metrics 数据给 prometheus，收集 host 硬件和操作系统数据。它将以容器方式运行在所有 host 上
cAdvisor：负责收集容器数据。它将以容器方式运行在所有 host 上
alertmanager：定义更详细的告警规则，告警后将告警信息转发给 prometheus-webhook-dingtalk 的web服务
prometheus-webhook-dingtalk：负责告警消息的美化及从Prometheus AlertManager WebHooks生成DingTalk通知

