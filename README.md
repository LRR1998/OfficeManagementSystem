# OfficeManagementSystem
80%的功能基本完成了
1.使用技术：
主体架构： Spring+springMVC+Mybatis（处理业务技术）
Redis： 缓存（把我们经常使用的数据放入redis）减轻db压力（商城项目）
Shiro： 权限框架（根据用户登录角色完成菜单导航）处长  主任  科员。
Echars： 图表工具，数据统计.
Ueditor:图文混排  论坛【富文本】
阿里巴巴的druid数据源： 稳定 高效  世界上最好的数据源技术
页面主体：bootstarp.js(vue.js,react.js)

Quartz： 定时任务，消息的定时发送，

JavaMail: 发送邮件

Ztree插件：树插件

git: 版本控制

Maven：完成jar文件的管理


2.表结构分析：
	Attachment： 附件表（项目中需要文档类型规范：编码规范 注释规范  命名规范。。）
	
	Project： 项目表（公司经营的主体业务）
	Customer： 客户表（统计保存客户信息）
	
	Analysis： 需求表（当前的项目的编写过程中需求分析文档）
	
	Module: 模块（项目中的具体模块：人员管理 业务管理 仓储信息）
	
	Function： 功能表（一个模块下有多个功能）
	
	Task: 任务表 （那些功能分配给了具体人）
	
	Notice： 通知公告（任命，会议通知，考勤情况）
	
	Employee： 员工表（员工的基本信息）
	
	Role: 角色表
	
	Emp_role：员工和角色中间表 

	Role_sources: 角色和资源的中间表
	
	Sources： 资源表（能够去点击那些连接，能够去操作那些功能） 
	Email： 邮件表（发送邮件 查看发送的邮件）
	
	Comparsion: 对标管理的表（设定明年的目标，对比企业概况）
	
	Dept： 部门表 （it）
	Position： 职位表
	
	level 级别表：当前的职位对应的级别
	
	Msg： 消息表（定时发送一些剪短消息）
	
	Baoxiao: 员工报销的表格
	Dangan: 员工的档案表
	3. 搭建ssm环境：
   1.导入jar包【依赖】ssm、jstl、json、druid、mysql驱动、上传和下载
   
   2.web.xml配置spring、springmvc、两个filter
   3.创建springmvc、spring配置文件并配置他们
      springmvc.xml
	     扫描包
		 配置内部资源视图解析器
		 配置处理静态资源的标签
		 <mvc:annotation-driven>标签
      spring 配置文件
	    扫描包
		加载properties配置文件
		
		配置数据源
		配置数据源事务管理器
		开启基于注解的事务支持
		
		配置SqlSessionFactoryBean
		  配置数据源
		  加载mybatis的全局配置文件
		配置扫描MapperScannerConfigurer
		  basePackage:
		  
	4.MyBatis的配置文件
	   下划线转驼峰式命名
	   
