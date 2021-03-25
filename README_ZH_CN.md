# StuManage

[English Version](README.md)

本项目为南京大学2021年春季课程“软件体系结构”的第一次课程作业。它实现了一个具有增删查改功能的简单学生信息管理系统。

## 构建与运行

本项目的构建与运行需要Gradle 6.8.3（或更新版本）与JDK 11（或更新版本）。

使用如下命令

```shell
./gradlew bootRun
```

以自动下载合适版本的Gradle，构建项目，并启动构建完成的程序。这一命令的可用性已在Windows（PowerShell）与Linux（Bash）下测试过，但未在macOS中测试过。然而，在中国大陆，使用这一命令下载Gradle可能会非常缓慢，因此，如果你已经安装了Gradle，请使用如下命令：

```shell
gradle bootRun
```

在构建过程中，依赖也将被自动下载，因此无需关注。

启动程序后，使用浏览器访问 [http://localhost:8080](http://localhost:8080) 来进入学生管理系统。
