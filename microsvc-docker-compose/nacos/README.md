# docker-compose 部署nacos

### 方式1：两个容器定义在一个docker-compose.yml中


### 方式2：两个独立的yml:需要链接的容器nacos&mysql没有定义在同一个docker-compose.yml

#### 注意事项：

1. docker-compose-mysql.yml指定网络igavin-nacos-net
1. docker-compose-nacos.yml中 MYSQL_SERVICE_HOST=容器名 和 MYSQL_SERVICE_PORT=3306（在同一网络中,注意不是3307）



```
version: '3.0'
services:
  mysql3307:
    image: mysql:5.7
    ports:
      - "3307:3306"
    container_name: igavin-mysql-nacos-3307
    environment:
      MYSQL_ROOT_PASSWORD: 123456
    volumes:
      - "./data/mysql3307/data:/var/lib/mysql"
    networks:
      - igavin-nacos-net


networks:
  igavin-nacos-net:
    external: false
```
docker-compose-nacos.yml

```
version: "3"
services:
  nacosstandalone:
    image: nacos/nacos-server:latest
    container_name: nacos-standalone-mysql

    volumes:
      - "./data/nacos/logs/:/home/nacos/logs/"
      - "./data/nacos/data/:/home/nacos/data/"
    environment:
      - MODE=standalone
      - SPRING_DATASOURCE_PLATFORM=mysql
      - MYSQL_SERVICE_HOST=igavin-mysql-nacos-3307
      - MYSQL_SERVICE_PORT=3306
      - MYSQL_SERVICE_DB_NAME=nacos
      - MYSQL_SERVICE_USER=root
      - MYSQL_SERVICE_PASSWORD=123456
      - SET_CONTAINER_TIMEZONE=true
      - CONTAINER_TIMEZONE=Asia/Shanghai
    ports:
      - "8848:8848"
    networks:
      - igavin-nacos-net

networks:
  igavin-nacos-net:
    external: false
```