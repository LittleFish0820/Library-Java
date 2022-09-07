# 图书馆管理系统

## 1. 需求分析

```mermaid
graph LR
	id0(图书馆管理系统功能) --> id1.1(图书管理)
	id0(图书馆管理系统功能) --> id1.2(用户管理)
	
	id1.1(图书管理) --> id2.1(图书借出) 
	id1.1(图书管理) --> id2.2(图书归还) 
	id1.1(图书管理) --> id2.3(新书录入) 
	id1.1(图书管理) --> id2.4(未归还图书查询)
    
    id1.2(用户管理) --> id2.5(管理员登录)
    id1.2(用户管理) --> id2.6(学生用户注册)
```

## 2. 系统设计

### 图书馆应用结构设计

```mermaid
graph TD
	id(视图) --> id1(管理员登陆界面)
	id(视图) --> id2(管理员注册界面)
	id(视图) --> id3(应用主界面)
	id(视图) --> id4(借出图书界面)
	id(视图) --> id5(归还图书界面)
	id(视图) --> id6(新书录入界面)
	id(视图) --> id7(学生管理界面)
	id(视图) --> id8(未归还图书管理界面)
```



```mermaid
graph TD
	id(模型) --> id1(图书)
    id(模型) --> id2(图书操作事件)
    id(模型) --> id3(用户)
```



```mermaid
graph TD
	id(控制器) --> id1(图书操作)
    id(控制器) --> id2(用户操作)
    id(控制器) --> id3(数据库操作)
```



### 数据库表设计

- 管理员信息-`adminInfo`
- 学生信息-`stuInfo`
- 图书信息-`bookInfo`
- 图书操作信息记录-`bookLog`
- 未归还图书-`outSideBook`



## 3. 注意事项

创建一个Linux虚拟机，或购买一台云服务器。安装好==MongoDB==，保持开启状态。**关闭防火墙。**

