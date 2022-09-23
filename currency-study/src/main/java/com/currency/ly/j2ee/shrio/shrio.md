一、介绍
Shiro 能做什么
1、认证：验证用户的身份

2、授权：对用户执行访问控制：判断用户是否被允许做某事

3、会话管理：在任何环境下使用 Session API，即使没有 Web 或EJB 容器。

4、加密：以更简洁易用的方式使用加密功能，保护或隐藏数据防止被偷窥

5、Realms：聚集一个或多个用户安全数据的数据源

6、单点登录（SSO）功能。

7、为没有关联到登录的用户启用 “Remember Me“ 服务
二、四大核心部分

Authentication(身份验证)：简称为“登录”，即证明用户是谁。
Authorization(授权)：访问控制的过程，即决定是否有权限去访问受保护的资源。
Session Management(会话管理)：管理用户特定的会话，即使在非 Web 或 EJB 应用程序。
Cryptography(加密)：通过使用加密算法保持数据安全

三、Shiro 架构 3 个核心组件

 Subject ：正与系统进行交互的人，或某一个第三方服务。所有 Subject 实例都被绑定到（且这是必须的）一个SecurityManager 上。

- SecurityManager：Shiro 架构的心脏，用来协调内部各安全组件，管理内部组件实例，并通过它来提供安全管理的各种服务。当 Shiro 与一个 Subject 进行交互时，实质上是幕后的 SecurityManager 处理所有繁重的 Subject 安全操作。

- Realms ：本质上是一个特定安全的 DAO。当配置 Shiro 时，必须指定至少一个 Realm 用来进行身份验证和/或授权。Shiro 提供了多种可用的 Realms 来获取安全相关的数据。如关系数据库(JDBC)，INI 及属性文件等。可以定义自己 Realm 实现来代表自定义的数据源。
