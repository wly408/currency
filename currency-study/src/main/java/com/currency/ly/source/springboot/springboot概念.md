一、Application
    SpringBootApplication是注解
    SpringApplication spring应用
二、context
    ConfigurableApplicationContext:servlet->AnnotationConfigServletWebServerApplicationContext,有以下属性：
    AnnotatedBeanDefinitionReader reader;
    ClassPathBeanDefinitionScanner scanner;
    volatile WebServer webServer;
    ServletConfig servletConfig;
    DefaultListableBeanFactory beanFactory;
    ResourceLoader resourceLoader;
    
    
    而ApplicationContextInitializer在context准备中初始化
    
    context.addBeanFactoryPostProcessor(new LazyInitializationBeanFactoryPostProcessor());
    
    GenericApplicationContext 
    AbstractRefreshableApplicationContext

beanFactory
三、ConfigurableEnvironment
三、Initializer
    ApplicationContextInitializer


二、Listener
三、Aware
ResourceLoaderAware, BeanClassLoaderAware



四、Context
五、Processors
    BeanPostProcessor(后置处理器):
        postProcessBeforeInitialization:实例化、依赖注入完毕，在调用显示的初始化之前完成一些定制的初始化任务
        postProcessAfterInitialization:实例化、依赖注入、初始化完毕时执行
    流程：
    　BeanPostProcessor的运行流程如下：
  
    　　　　1）Spring IOC容器实例化Bean；
    
    　　　　2）调用BeanPostProcessor的postProcessBeforeInitialization方法；
    
    　　　　3）调用bean实例的初始化方法；
    
    　　　　4）调用BeanPostProcessor的postProcessAfterInitialization方法；
    
    BeanFactoryPostProcessor:
        postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory):允许我们在工厂里所有的bean被加载进来后但是还没初始化前，对所有bean的属性进行修改也可以add属性值
        
    BeanDefinitionRegistryPostProcessor：
    EnvironmentPostProcessor
        
Event
factrybena beanfactory
