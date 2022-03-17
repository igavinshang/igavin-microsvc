## docker-compose 部署nacos

### 方式1：两个容器定义在一个docker-compose.yml中

### 方式2：两个独立的yml:需要链接的容器nacos&mysql没有定义在同一个docker-compose.yml（采用）

#### 方式2注意事项：

1. docker network create igavin-nacos-net 先创建网络

2. 然后mysql.yml和nacos.yml都指定使用外部网络，external: true

3. docker-compose-mysql.yml指定网络igavin-nacos-net

4. docker-compose-nacos.yml中 MYSQL_SERVICE_HOST=容器名 和 MYSQL_SERVICE_PORT=3306（在同一网络中,注意不是3307）

