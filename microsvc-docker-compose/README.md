docker的目录外挂路径在 microsvc-docker-compose/data下面
各个应用的docker-compose.yml的路径都用的相对路径./data
启动从microsvc-docker-compose下启动
启动命令：
docker-compose - f docker-compose-networks.yml -f ./应用/docker-compose.yml up -d
这样，各个应用的外挂路径都会映射到./data中
