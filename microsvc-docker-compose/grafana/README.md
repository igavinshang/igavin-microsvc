### 项目加入prafana进行监控

1.springboot项目，例如microsvc-web，需要引入actuator，参见印象笔记
2.中间件，例如kafka,引入kafka-exporter，收集metrics，地址http://localhost:9308/metrics

prafana中添加监控看板
kafka-exporter统计模板，好像有问题，通过修改Variables中的项
    -job(label_values(kafka_brokers, job))
    -instance(label_values(kafka_brokers, instance))
    -topic(label_values(kafka_topic_partition_current_offset{instance='$instance',topic!='__consumer_offsets',topic!='--kafka'}, topic))
然后看到统计数据
