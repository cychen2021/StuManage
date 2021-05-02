# StuManage

本分支是《软件体系结构》的第3次课程作业。在本次作业中，我们将原本的学生管理系统更改为RESTful风格，并使用Vue为其开发了简易的前端界面。

项目的构建、运行与使用方法见master分支的README文件，此外，请确保您使用的浏览器支持HTML 5与Vue.js。

本分支大部分内容与master分支相同，只有两点值得一提的区别。

## RESTful API

我们将原本的控制器`HomeController`更改为RESTful风格的控制器`MainController`。该控制器接受`GET /students?name=<name>&id=<id>`请求用于根据学号和姓名查询学生信息，`POST /student`用于添加一条学生信息，`PUT /student`用于更新某条学生信息，`DELETE /student?real_id=<internal_id>`用于根据内部ID删除某条学生信息。此外，还有用于载入单条待编辑学生信息的`GET /student?real_id=<internal_id>`请求，这一请求使用内部ID定位待编辑学生的信息——而不是使用学号和姓名——这是因为不同学生的学号和姓名有可能重复，而内部ID是唯一的。

上述控制器只返回JSON或XML等数据交换格式的对象给客户端，因此只能作为后端API使用。为了让客户端能够获取作为前端界面的HTML网页，我们还编写了一个普通的`PageController`，用于接受对不同网页（包括搜索学生的页面、编辑学生信息的页面和添加学生信息的页面）的请求、并返回对应网页。这一控制器对于实现前端界面是必须的，且结构十分简单，不影响系统整体的RESTful风格。

## 前端

我们使用[Vue.js](https://vuejs.org)及其插件[vue-resource](https://github.com/pagekit/vue-resource)和[vuejs-dialog](https://github.com/Godofbrowser/vuejs-dialog)为学生管理系统实现了简易的前端界面。这一前端可以使用前述RESTful API从后端系统获取相关信息，处理后显示在界面上，并可在输入（例如添加、编辑学生）时校验输入内容的合法性，避免不合法输入导致的错误。

除了上述两点比较重要的区别外，我们还向数据库添加了十条初始数据以方便演示，另外还对之前的程序进行了些微修改以适应新的API。