# NeptuneBlog

基于Spring Cloud Netflix的MicroBlog, 前端部分使用Angular 10开发.

## 项目地址

- 前端 <https://github.com/liuweigeek/NeptuneBlog-frontend>
- 后端 <https://github.com/liuweigeek/NeptuneBlog-backend>
- iOS端 <https://github.com/liuweigeek/NeptuneBlog-iOS>

## 项目截图

#### 主页

![主页](./docs/images/homepage.png)

#### 个人资料页

![个人资料页](./docs/images/user-profile.png)

#### 移动端

![移动端](./docs/images/ios-screenshot.jpg)

## 部署步骤

#### 中间件部署

打开`docs/docker/services`
中对应版本的Docker Compose配置文件, 修改`volumes`为自己的本地路径, 统一部署中间件

#### 数据库初始化

执行`docs/script/DDL.sql`中的数据库初始化脚本

#### 启动项目

- 启动`eureka-server`, 然后依次启动`config`、`authentication`、`user`、`tweet`、`search`、`api-gateway`服务
- 启动前端项目 [NeptuneBlog-frontend](https://github.com/liuweigeek/NeptuneBlog-frontend)
  , 然后在浏览器中访问<http://localhost:4200>

## 功能模块

#### common

通用代码模块

#### eureka-server

注册中心, 使用Eureka Server

#### config-server

配置中心, 使用Spring Cloud Config

#### api-gateway

网关模块, 使用Ribbon、Zuul和Hystrix

#### authentication

授权中心模块, 使用Eureka Client、Feign、Spring Security、JWT

#### user

用户模块, 使用Eureka Client、Spring Data JPA、MySQL

#### tweet

推文模块, 使用Eureka Client、Feign、Spring Data JPA、MySQL

#### search

搜索模块, 使用Eureka Client、Feign

## ❤️ Contributing

<a href="https://www.buymeacoffee.com/liuweigeek" target="_blank"><img src="https://www.buymeacoffee.com/assets/img/custom_images/yellow_img.png" alt="Buy Us A Coffee" style="height: auto !important;width: auto !important;" ></a>