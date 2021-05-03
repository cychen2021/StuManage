# StuManage

本分支是《软件体系结构》的第4次课程作业。在本作业中，我们使用Spring Batch读取了测试文件（位于data/test.xlsx）中存储的学生信息、进行处理后存入数据库内。

项目的构建、运行与使用方法见master分支的README文件，此外，请确保您使用的浏览器支持HTML 5与Vue.js。

本分支基于第3次作业的分支assignment3，大部分内容也与该分支相同，但做了如下两点改动。

## 增加Spring Batch数据处理模块

我们在本分支中增加了`xyz.cychen.stumanage.batch`包，该包内包含使用Spring Batch读取与处理测试文件中学生信息的代码。我们定义了`StudentRowMapper`用于将测试文件中不同列映射到`Student`对象的不同字段；还定义了`StudentProcessor`用于为读取的`Student`对象生成内部ID；此外，`BatchConfiguration`用于配置Batch处理任务。

## 更改学生信息条目的字段

之前几次作业要求系统能处理学生的姓名、学号、性别、籍贯、出生年月、院系信息，但本次作业提供的测试数据不包括学生的性别、出生年月和籍贯，而增加了联系电话信息。因此，我们对系统进行了相应的更改，删除了学生的性别、出生年月和籍贯信息，增加了联系电话信息，且前端会对联系电话的格式进行检查。
