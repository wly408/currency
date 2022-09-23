一、介绍
JWT的全称是：Json-web-Token，从字面是上我们不难理解，即web端的一个json类型的token，各方之间的安全json访问对象
二、三部分

1.head
         head里面的内容是json串，有两个参数，type：jwt  alg:RSA或者ASE等，这里是代表需要用什么算法进行加密解密
2Payload 
        payload也是json串，这里用来写一些注册信息，公开以及私有的一些方法
3Signature 
      Signature 签名，这一步部分是head+payload里的信息整合进行签名，具体是将head的json进行加密、
      将payload的json进行加密，这两部信息中间用点(.)进行分割,然后再同一将整合的内容进行再次加密，得到的就是最终的token
三、JWT使用过程
  
  client端向认证服务器发起认证授权请求，认证通过后，会向client客户端发送一个token串，
  客户端拿到token串后再去访问服务器即可   
四、优缺点
 优点：
 认证通过的情况下，资源服务器不必再次做校检token正确与否，只要资源服务器能将token串解密开就可以了，可以减轻认证授权中心的压力
 缺点：
 JWT的token串里的内容多，会很影响性能，安全性能比较弱，一般设置过期的时间不宜太长，而且这个token串在client端是需要存储的