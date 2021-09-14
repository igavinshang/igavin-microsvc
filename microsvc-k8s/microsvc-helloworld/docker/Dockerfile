FROM java:alpine
VOLUME /tmp
WORKDIR /igavinshang/app
# ADD 命令从build当前上下文中进行查找，所需文件应该添加进来。你不能添加../something / something，因为docker构建的第一步是将上下文目录（和子目录）发送到docker守护进程
ADD microsvc-helloworld-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 18080
#如果指定了WORKDIR,则ENTRYPOINT命令中的app.jar路径应该是相对路径app.jar，app.jar会存放在warkdir中；如果没有指定workdir,默认app.jar会在/ 根目录下，则下面app.jar的路径应该是/app.jar
ENTRYPOINT ["java","-jar","app.jar"]
