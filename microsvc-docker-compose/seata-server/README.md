docker-compose部署文档
https://seata.io/zh-cn/docs/ops/deploy-by-docker-compose.html

本实例采用 无注册中心，DB存储

db模式需要在数据库创建对应的表结构，[建表脚本] 见 ./db/mysql.sql

（1）准备file.conf配置文件 路径 ./config/file.conf

（2）准备registry.conf文件 路径 ./config/registry.conf

（3）准备docker-compose.yaml文件
