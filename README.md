# sponge-blog
> SpongeBlog是一个基于SpringBoot开发的单体的个人博客系统。适合于一些想学习SpringBoot的小伙伴用来练手使用，该系统只是将一个博客系统基本的功能进行了实现，还有很多不完善的地方，小伙伴们可以根据子级的需求和想法进行修改完善<br>
> 
> 如果本项目对您有帮助的话，还请点个star给予精神支持！谢谢！

### 项目架构
- SringBoot 2.1.10
- JDK 1.8
- Maven 3.5
- MySQL 5.7
- Alibaba-Druid 1.1.20
- Swagger2 2.9.2
- Bootstrap 4.x

### 包含功能
> 该系统包括 前台系统 与 后台系统<br>
> 前台系统主要用来展示信息<br>
> 后台系统主要用来管理资源等

#### 1. 前台系统主要功能
- 根据文章ID查询文章信息及对应的评论信息
- 按文章分类展示文章信息
- 按文章标签展示文章信息
- 按年度归档文章
- 文章评论功能及留言板留言功能

#### 2. 后台系统主要功能
- 发布文章
- 文章管理
- 分类管理
- 标签管理
- 评论留言信息管理

#### 3. Swagger2 API
```
启动项目后可以通过此格式进行访问swaggerAPI
如：http://localhost:9999/swagger-ui.html
```

#### 4. Druid监控
```
启动项目后可以通过以下地址进行访问，并且可以在application.yml配置文件配置druid登录账号及密码
如：http://localhost:9999/druid
```

### 启动项目
> 下载完本项目后，需要现在自己电脑上进行下准备工作，然后再启动项目

#### 1. 创建数据库及对应的表
> 项目中所需的建库建表SQL脚本存放在 [ docs --> sql ]
- 权限认证所需表
```
sec_user 用户数据表
sec_user_role 用户角色表
sec_role 角色表
sec_parm 权限表
sec_role_parm 角色权限表
```
- 文章信息表
```
sk_blog 
```
- 评论留言信息表
```
sk_comment 
```
- 分类信息表
```
sk_type
```
- 标签信息表
```
sk_label
```