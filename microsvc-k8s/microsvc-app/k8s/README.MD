先执行 -- kubectl apply -f microsvc-app-db.yaml 

在执行 -- kubectl describe svc microsvc-mysql -n ns-microsvc-app

得到 clusterIP 加上定义的3306端口就可以正常访问我们这个mysql服务

------------

将microsvc-app通过ingress暴露给外部使用
安装ingress-nginx通过ingress-nginx-mandatory.yaml
然后通过service-nodeport.yaml将ingress暴露给外部
然后写microsvc-app-ingress.yaml实现ingress路由规则
