一、DDD模式

领域模型（Domain）处理大部分逻辑，Service只做少部分逻辑 --> 数据逻辑融合 --> 面向对象编程

1.Controller + VO
2.Service 
    Service 类负责与 Dao 交流将Entity转化为Domain，由 Domain（领域模型）完成大部分业务逻辑
    Service 类负责跨领域模型的业务聚合功能
    Service 类负责一些非功能性及与三方系统交互的工作。比如幂等、事务、发邮件、发消 息、记录日志、调用其他系统的 RPC 接口等，都可以放到 Service 类中
3.领域模型domain
4.Dao + Entity  Entity主要用于数据库字段映射