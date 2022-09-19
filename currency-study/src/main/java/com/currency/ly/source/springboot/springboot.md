一、spring boot 的主要类介绍
1.SpringApplication
    主要属性：List<ApplicationContextInitializer>、List<ApplicationListener>、ConfigurableEnvironment environment、Map<String, Object> defaultProperties
2.SpringBootServletInitializer
    方法：onStartup
3.WebApplicationInitializer
    
2.ApplicationContextInitializer
    就一个方法：initialize(ConfigurableApplicationContext applicationContext)主要对ConfigurableApplicationContext进行设置
3.ApplicationListener
    方法：onApplicationEvent(SpringApplicationEvent event)
    ApplicationStartingEvent：除了基础的注册监听和初始化之外，在开始运行时做任何处理动作之前发送
    ApplicationEnvironmentPreparedEvent 在上下文中使用的环境已知，但是Context尚未创建之前发送
    ApplicationPreparedEvent 在Spring刷新Context开始之前，而仅当加载bean定义之后发送
    ApplicationStartedEvent 在刷新上下文之后，但在调用任何应用程序（ApplicationRunner）和命令行运行程序（CommandLineRunner）之前发送
    ApplicationReadyEvent 在调用应用程序和命令行运行程序后发送。 它表示应用程序已准备好为请求提供服务。
    ApplicationFailedEvent 在启动过程中出现异常
  
4.ConfigurableEnvironment
    ConfigurableEnvironment不仅提供了配置文件解析的数据，以及配置文件名称，还提供了PropertySource数据
    void setActiveProfiles(String... var1);
    void addActiveProfile(String var1);
    void setDefaultProfiles(String... var1);
    MutablePropertySources getPropertySources();
    Map<String, Object> getSystemProperties();
    Map<String, Object> getSystemEnvironment();
    void merge(ConfigurableEnvironment var1);
5.DefaultListableBeanFactory
    
6.ConfigurableApplicationContext
    继承： ApplicationContext, Lifecycle
    ApplicationContext：
    Lifecycle：void start();void stop();boolean isRunning();
7.ApplicationContext
    7.1ApplicationContext extends EnvironmentCapable, ListableBeanFactory, HierarchicalBeanFactory,
    		MessageSource, ApplicationEventPublisher, ResourcePatternResolver
    7.2

8.SpringApplicationRunListener
    SpringApplicationRunListeners类和SpringApplicationRunListener类是SpringBoot中新增的类。SpringApplication类 中使用它们来间接调用ApplicationListener。
    SpringApplicationRunListeners包含了多个SpringApplicationRunListener
    SpringApplicationRunListener接口规定了SpringBoot的生命周期，在各个生命周期广播相应的事件，调用实际的ApplicationListener类。
  
  接口定义如下：
  
  public interface SpringApplicationRunListener {
  
       //刚执行run方法时
      void started();
       //环境建立好时候
      void environmentPrepared(ConfigurableEnvironment environment);
       //上下文建立好的时候
      void contextPrepared(ConfigurableApplicationContext context);
      //上下文载入配置时候
      void contextLoaded(ConfigurableApplicationContext context);
      //上下文刷新完成后，run方法执行完之前
      void finished(ConfigurableApplicationContext context, Throwable exception);
  
  }

  SpringApplication类实际使用的是SpringApplicationRunListeners类，它可以包含多个SpringApplicationRunListener实例，与SpringApplicationRunListener生命周期相同，调用每个周期的各个SpringApplicationRunListener。然后广播相应的事件到ApplicationListener


7.SpringApplicationRunListeners
11.ApplicationContextAware