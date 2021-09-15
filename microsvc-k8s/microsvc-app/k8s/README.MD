先执行 -- kubectl apply -f microsvc-app-db.yaml 

在执行 -- kubectl describe svc microsvc-mysql -n ns-microsvc-app

得到 clusterIP 加上定义的3306端口就可以正常访问我们这个mysql服务

------------

TODO 
日志路径有问题

