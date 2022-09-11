# 图书馆管理系统

## 1. 需求分析

**图书馆管理系统功能**

- 图书管理
  - 图书借出
  - 图书归还
  - 新书录入
  - 未归还图书查询
- 用户管理
  - 管理员登录
  - 学生用户注册



## 2. 系统设计

### 图书馆应用结构设计

#### 视图

- 管理员登录界面
- 管理员注册界面
- 应用主界面
- 借出图书界面
- 归还图书界面
- 新书录入界面
- 学生管理界面
- 未归还图书管理界面

#### 模型

- 图书
- 图书操作事件
- 用户

#### 控制器

- 图书操作
- 用户操作
- 数据库操作



### 数据库表设计

- 管理员信息-`adminInfo`
  - `userName`
  - `userPWD`
  - `userORG`
  - `isAdmin`

- 学生信息-`stuInfo`
  - `stuID`
  - `stuName`
  - `stuClass`

- 图书信息-`bookInfo`
  - `bookID`
  - `bookName`
  - `bookSummary`

- 图书操作信息记录-`bookLog`
  - `actDate`
  - `actMode`
  - `bookID`
  - `bookName`
  - `stuID`
  - `stuName`

- 未归还图书-`outSideBook`
  - `outDate`
  - `bookID`
  - `bookName`
  - `stuID`
  - `stuName`




## 3. 注意事项

创建一个Linux虚拟机，或购买一台云服务器。安装好==MongoDB==，保持开启状态。**关闭防火墙。**

