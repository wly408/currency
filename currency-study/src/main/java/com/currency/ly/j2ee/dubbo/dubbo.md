https://blog.csdn.net/ShyTan/article/details/119725614?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_baidulandingword~default-4-119725614-blog-105901760.pc_relevant_multi_platform_whitelistv4&spm=1001.2101.3001.4242.3&utm_relevant_index=7
一、dubbo概念
Dubbo是基于RPC的SOA框架
作为RPC：支持各种传输协议，如dubbo,hession,json,fastjson，底层采用mina,netty长连接进行传输！典型的provider和cusomer模式!
作为SOA：具有服务治理功能，提供服务的注册和发现！用zookeeper实现注册中心！启动时候服务端会把所有接口注册到注册中心，并且订阅configurators,服务消费端订阅provide，configurators,routers,订阅变更时，zk会推送providers,configuators，routers,启动时注册长连接，进行通讯！proveider和provider启动后，后台启动定时器，发送统计数据到monitor（监控中心）！提供各种容错机制和负载均衡策略！！
二、Dubbo里面有哪几种节点角色

节点	        角色说明
Provider	暴露服务的服务提供方
Consumer	调用远程服务的服务消费方
Registry	服务注册与发现的注册中心
Monitor	    统计服务的调用次数和调用时间的监控中心
Container	服务运行容器

二、何为RPC

三、数据的传递
  public class RemoteTraceIdFilter implements Filter
  
  
  RpcContext.getContext().setAttachment(AbstractMyThreadContext.TRACE_ID,
                    AbstractMyThreadContext.getTraceId());
