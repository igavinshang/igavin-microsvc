docker-compose部署文档
https://seata.io/zh-cn/docs/ops/deploy-by-docker-compose.html

本实例采用 nacos注册中心，DB存储

db模式需要在数据库创建对应的表结构，[建表脚本] 见 ./db/mysql.sql

（1）准备registry.conf文件 路径 ./config/registry.conf

（2）准备nacos配置中心配置,你需要在nacos新建配置，此处dataId为seataServer.properties,配置内容见文件

（3）准备docker-compose.yaml文件

备注：mac本机docker-compose部署seata-nacos注册中心时，出现一直连接nacos服务器地址172.24.29.87timeout的情况
应该是通过docker-compose部署时，之前生成了很多默认网络，通过docker network ls可查看
这些网络中的虚拟ip应该和nacos服务器有网段冲突，通过docker network rm 删除掉一些网络后，访问成功

