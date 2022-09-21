JavaWeb 的三大组件分别是：Servlet 程序、Listener 监听器、Filter 过滤器
一、作用域对应的类
1、application（ServletContext）
生命周期：当Web应用被加载进容器时创建代表整个web应用的application对象，当服务器关闭或Web应用被移除时，application对象跟着销毁。  
作用范围：整个Web应用。
作用：   
  application.setAttribute(“key”,Object value):存储整个web应用公用的数据
  在不同Servlet 之间转发（不常用） 
2、session 域 (HttpSession)
　　HttpSession 在服务器中，为浏览器创建独一无二的内存空间，在其中保存会话相关的信息。
　　生命周期：在第一次调用 request.getSession() 方法时，服务器会检查是否已经有对应的session,如果没有就在内存中创建一个session并返回。   
当一段时间内session没有被使用（默认为30分钟），则服务器会销毁该session。 如果服务器非正常关闭（强行关闭），没有到期的session也会跟着销毁。 如果调用session提
供的invalidate（） ，可以立即销毁session。
　　注意：服务器正常关闭，再启动，Session对象会进行钝化和活化操作。同时如果服务器钝化的时间在session 默认销毁时间之内，则活化后session还是存在的。否则Session
不存在。如果JavaBean 数据在session钝化时，没有实现Serializable 则当Session活化时，会消失。
　 作用范围：一次会话。  
   作用：保存登录的用户信息、购物车信息等
3、request域  --(HttpServletRequest) 
　生命周期：在service 方法调用前由服务器创建，传入service方法。整个请求结束，request生命结束。  
　作用范围：整个请求链（请求转发也存在）。  
　作用：  在整个请求链中共享数据。最常用到：在Servlet 中处理好的数据交给Jsp显示，此时参数就可以放置在Request域中带过去。
4、pageContext域—(PageContext)
　生命周期：当对JSP的请求时开始，当响应结束时销毁。  
　作用范围：整个JSP页面，是四大作用域中最小的一个。  
  作用：   
  （1）获取其它八大隐式对象，可以认为是一个入口对象。   
  （2）获取其所有域中的数据  
二、九大内置对象对应的类
1、request对象
request 对象是 javax.servlet.httpServletRequest类型的对象。 该对象代表了客户端的请求信息，主要用于接受通过HTTP协议传送到服务器的数据。（包括头信息、系统信息、请求方式以及请求参数等）。request对象的作用域为一次请求。

2、response对象
response 代表的是对客户端的响应，主要是将JSP容器处理过的对象传回到客户端。response对象也具有作用域，它只在JSP页面内有效。

3、session对象
session 对象是由服务器自动创建的与用户请求相关的对象。服务器为每个用户都生成一个session对象，用于保存该用户的信息，跟踪用户的操作状态。session对象内部使用Map类来保存数据，因此保存数据的格式为 “Key/value”。 session对象的value可以使复杂的对象类型，而不仅仅局限于字符串类型。

4、application对象
application 对象可将信息保存在服务器中，直到服务器关闭，否则application对象中保存的信息会在整个应用中都有效。与session对象相比，application对象生命周期更长，类似于系统的“全局变量”。

5、out 对象
out 对象用于在Web浏览器内输出信息，并且管理应用服务器上的输出缓冲区。在使用 out 对象输出数据时，可以对数据缓冲区进行操作，及时清除缓冲区中的残余数据，为其他的输出让出缓冲空间。待数据输出完毕后，要及时关闭输出流。

6、pageContext 对象
pageContext 对象的作用是取得任何范围的参数，通过它可以获取 JSP页面的out、request、reponse、session、application 等对象。pageContext对象的创建和初始化都是由容器来完成的，在JSP页面中可以直接使用 pageContext对象。

7、config 对象
config 对象的主要作用是取得服务器的配置信息。通过 pageConext对象的 getServletConfig() 方法可以获取一个config对象。当一个Servlet 初始化时，容器把某些信息通过 config对象传递给这个 Servlet。 开发者可以在web.xml 文件中为应用程序环境中的Servlet程序和JSP页面提供初始化参数。

8、page 对象
page 对象代表JSP本身，只有在JSP页面内才是合法的。 page隐含对象本质上包含当前 Servlet接口引用的变量，类似于Java编程中的 this 指针。

9、exception 对象
exception 对象的作用是显示异常信息，只有在包含 isErrorPage=“true” 的页面中才可以被使用，在一般的JSP页面中使用该对象将无法编译JSP文件。excepation对象和Java的所有对象一样，都具有系统提供的继承结构。exception 对象几乎定义了所有异常情况。在Java程序中，可以使用try/catch关键字来处理异常情况； 如果在JSP页面中出现没有捕获到的异常，就会生成 exception 对象，并把 exception 对象传送到在page指令中设定的错误页面中，然后在错误页面中处理相应的 exception 对象。



三、监听

①、ServletContextListener：监听器可以监听ServletContext域对象的创建和销毁，即可以监听服务器的启动和关闭
②、HttpSessionListener：监听器可以监听HttpSession域对象的创建和销毁
③、ServletRequestListener
④、ServletContextAttributeListener
⑤、HttpSessionAttributeListener
⑥、ServletRequestAttributeListener
①、HttpSessionBindingListener：不需要在web.xml中进行配置，每当session中存入该对象时候HttpSessionBindingListener的绑定事件就会运行，而每当session中的该对象移除时候解绑事件就会运行
②、HttpSessionActivationListener：不需要在web.xml中进行配置,当用户长时间登陆网站不进行操作的时候就可以将这个用户的session对象进行钝化）想要自己设置钝化时间操作时，要在项目的META-INF下新建context.xml文件在这个里面进行配置


四、过滤器
Filter FilterConfig