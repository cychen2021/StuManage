# StuManage

[中文版本](README_ZH_CN.md)

This is the first course project for "Program Architecture" in the spring of 2021, at Nanjing University. It implements a simple student management system with functions of adding, deleting, searching, and editing student information.

## How to build and run

This project requires Gradle 6.8.3 (or a newer version) and JDK 11 (or a newer version) to build and run.

You can use the command

```shell
./gradlew bootRun
```

to automatically download the appropriate version of Gradle, build the project, and then launch it. This command has been tested on Windows (PowerShell) and Linux (Bash), but not on macOS. However, it may be very slow to use this to download Gradle in mainland China. If you have already installed Gradle, the following command is more recommended:

```shell
gradle bootRun
```

The dependencies will also be automatically downloaded during the process and require no concern.

After launching the application, visit [http://localhost:8080](http://localhost:8080) in your browser to access the student manage system.
