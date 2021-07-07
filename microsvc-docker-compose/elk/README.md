
## elk收集log日志


#### 职责

- filebeat -- 负责收集日志 轻量级收集日志
- logstash -- 负责日志统一过滤
- elasticsearch -- 负责日志存储
- kibana -- 负责日志查看


#### 部署方式

- elasticsearch & logstash & kibana 通过docker-compose 进行部署
- filebeat通过mac包管理软件brew进行安装

#### filebeat好处

*如果只使用ELK，实际上是有缺陷的。
如果Logstash需要添加插件，那就全部服务器的Logstash都要添加插件，扩展性差。
所以就有了FileBeat，占用资源少，只负责采集日志，不做其他的事情，这样就轻量级，把Logstash抽出来，做一些滤处理之类的工作。*



#####  filebeat 安装通过brew安装启动流程

```
igavinshang@igavin-macpro filebeat % brew install elastic/tap/filebeat-full

igavinshang@igavin-macpro filebeat % brew services list
Name          Status  User Plist
filebeat-full stopped      
nginx         stopped      
igavinshang@igavin-macpro filebeat % cd /usr/local/etc/filebeat 
igavinshang@igavin-macpro filebeat % ls
filebeat.yml	modules.d
igavinshang@igavin-macpro filebeat % vi filebeat.yml 
igavinshang@igavin-macpro filebeat % /usr/local/bin/filebeat -e -c filebeat.yml 

```