一、run
try {
			ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
			ConfigurableEnvironment environment = prepareEnvironment(listeners, applicationArguments);
			configureIgnoreBeanInfo(environment);
			Banner printedBanner = printBanner(environment);
			context = createApplicationContext();
			exceptionReporters = getSpringFactoriesInstances(SpringBootExceptionReporter.class,
					new Class[] { ConfigurableApplicationContext.class }, context);
			prepareContext(context, environment, listeners, applicationArguments, printedBanner);
			refreshContext(context);
			afterRefresh(context, applicationArguments);
			stopWatch.stop();
			if (this.logStartupInfo) {
				new StartupInfoLogger(this.mainApplicationClass).logStarted(getApplicationLog(), stopWatch);
			}
			listeners.started(context);
			callRunners(context, applicationArguments);
		}
		catch (Throwable ex) {
			handleRunFailure(context, ex, exceptionReporters, listeners);
			throw new IllegalStateException(ex);
		}
二、prepareEnvironment-查找并设置配置文件信息
//SpringApplication：
private ConfigurableEnvironment prepareEnvironment(SpringApplicationRunListeners listeners, ApplicationArguments applicationArguments) {
    //根据应用类型，创建应用环境：如得到系统的参数、JVM及Servlet等参数，等
    ConfigurableEnvironment environment = this.getOrCreateEnvironment();
    //将 defaultProperties、commandLine及active-prifiles 属性加载到环境中
    //commandLine 在 args 中配置
    //其它参数可在如下4个路径中配置：servletConfigInitParams、servletContextInitParams、systemProperties、systemEnvironment
    this.configureEnvironment((ConfigurableEnvironment)environment, applicationArguments.getSourceArgs());
    //1.1 将 spirng.config 配置文件加载到环境中
    listeners.environmentPrepared((ConfigurableEnvironment)environment);
    this.bindToSpringApplication((ConfigurableEnvironment)environment);
    if (!this.isCustomEnvironment) {
        environment = (new EnvironmentConverter(this.getClassLoader())).convertEnvironmentIfNecessary((ConfigurableEnvironment)environment, this.deduceEnvironmentClass());
    }
	//加一个ConfigurationPropertySources
    ConfigurationPropertySources.attach((Environment)environment);
    return (ConfigurableEnvironment)environment;
}
三、environmentPrepared-spirng.config 配置文件加载到环境中
运行一些7个监听器，主要是ConfigFileApplicationListener
//SpringApplicationRunListeners：
//这里之后，environment中的PropertySources中已经包含了所有的配置文件了
//这里的listeners就一个：EventPublishingRunListener
public void environmentPrepared(ConfigurableEnvironment environment) {
    Iterator var2 = this.listeners.iterator();

    while(var2.hasNext()) {
        SpringApplicationRunListener listener = (SpringApplicationRunListener)var2.next();
        listener.environmentPrepared(environment);
    }
}
//EventPublishingRunListener：
public void environmentPrepared(ConfigurableEnvironment environment) {
    //initialMulticaster 为 SimpleApplicationEventMulticaster 实例
    //一般有7个监听事件：ConfigFileAppLst、AnsiOutputAppLst、LoggingAppLst、ClasspathLoggingAppLst、BackgroundPreinitializer、DelegatingAppLst、FileEncodingAppLst
    //1.1.1 关键是通过调用 ConfigFileApplicationListener#onApplicationEnvironmentPreparedEvent() 方法来读取配置文件！
    this.initialMulticaster.multicastEvent(new ApplicationEnvironmentPreparedEvent(this.application, this.args, environment));
}
四、prepareContext
    准备上下文,发送ApplicationPreparedEvent事件等
    // 1. 上下文后置处理，包括向BeanFactory中注册BeanNameGenerator和ConversionService
        postProcessApplicationContext(context);
        // 2. SpringApplication构造器中初始化了各种ApplicationContextInitializer，循环调用他们的initialize方法
        applyInitializers(context);
        // 3. 发送ApplicationContextInitializedEvent事件
        listeners.contextPrepared(context);
二、refreshContext-调用refresh方法-重点
refresh方法是bean配置读取加载入口,也是SpringBoot启动的核心方法。
@Override
public void refresh() throws BeansException, IllegalStateException {
    synchronized (this.startupShutdownMonitor) {
        // 准备工作，记录下容器的启动时间、标记“已启动”状态、检验配置文件格式
        prepareRefresh();
        // 获取 Spring 容器
        ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();
        // 设置 BeanFactory 的类加载器，添加几个 BeanPostProcessor，手动注册几个特殊的 bean 等
        prepareBeanFactory(beanFactory);
        try {
            // BeanFactory 准备工作完成后进行的后置处理工作，子类可以自定义实现，Spring Boot 中是个空方法
            postProcessBeanFactory(beanFactory);
            //=======以上是 BeanFactory 的预准备工作=======
            // 调用 BeanFactoryPostProcessor 各个实现类的 postProcessBeanFactory(factory) 方法
            // SpringBoot 会在这里扫描 @Component 注解和进行自动配置
            invokeBeanFactoryPostProcessors(beanFactory);
            // 注册和创建 BeanPostProcessor 的实现类（注意和之前的 BeanFactoryPostProcessor 的区别）
            registerBeanPostProcessors(beanFactory);
            // 初始化 MessageSource 组件（做国际化功能；消息绑定，消息解析）
            initMessageSource();
            // 初始化当前 ApplicationContext 的事件广播器
            initApplicationEventMulticaster();
            // 具体的子类可以在这里初始化一些特殊的 Bean（在初始化 singleton beans 之前），Spring Boot 中默认没有定义
            onRefresh();
            // 注册事件监听器，监听器需要实现 ApplicationListener 接口
            registerListeners();
            // 初始化所有的 singleton beans（lazy-init 的除外）
            finishBeanFactoryInitialization(beanFactory);
            // 容器刷新完成操作
            finishRefresh();
        }
        catch (BeansException ex) {
            if (logger.isWarnEnabled()) {
                logger.warn("Exception encountered during context initialization - " +
                        "cancelling refresh attempt: " + ex);
            }
            destroyBeans();
            cancelRefresh(ex);
            throw ex;
        }
        finally {
            resetCommonCaches();
        }
    }
}
1.prepareBeanFactory
设置beanFactory一些属性
添加后置处理器
设置忽略的自动装配接口
注册一些组件
2.postProcessBeanFactory-前置事件bean级别的处理，针对某个具体的bean进行处理
子类重写以在BeanFactory完成创建后做进─步设置
在web环境中用来添加web特有的作用域scope，例如REQUEST、SESSION
3.invokeBeanFactoryPostProcessors-执行BeanFactoryPostProcessor;BeanFactoryPostProcessor是BeanFactory的后置处理器(bean的后置) 在BeanFactory标准初始化之后调用,用来定制和修改BeanFactory的内容；
调用BeanDefinitionRegistryPostProcessor实现向容器内添加bean的定义
调用BeanFactoryPostProcessor实现向容器内bean的定义的添加属性
protected void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
    // 1.getBeanFactoryPostProcessors(): 拿到当前应用上下文beanFactoryPostProcessors变量中的值
    // 2.invokeBeanFactoryPostProcessors: 实例化并调用所有已注册的BeanFactoryPostProcessor
    PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors(beanFactory, getBeanFactoryPostProcessors());
 
    // Detect a LoadTimeWeaver and prepare for weaving, if found in the meantime
    // (e.g. through an @Bean method registered by ConfigurationClassPostProcessor)
    if (beanFactory.getTempClassLoader() == null && beanFactory.containsBean(LOAD_TIME_WEAVER_BEAN_NAME)) {
        beanFactory.addBeanPostProcessor(new LoadTimeWeaverAwareProcessor(beanFactory));
        beanFactory.setTempClassLoader(new ContextTypeMatchClassLoader(beanFactory.getBeanClassLoader()));
    }
}
整个 invokeBeanFactoryPostProcessors 方法围绕两个接口，BeanDefinitionRegistryPostProcessor 和 BeanFactoryPostProcessor，其中 BeanDefinitionRegistryPostProcessor 继承了 BeanFactoryPostProcessor 。

BeanDefinitionRegistryPostProcessor 主要用来在常规 BeanFactoryPostProcessor 检测开始之前注册其他 Bean 定义。
BeanFactoryPostProcessor可以在spring的bean创建之前，修改bean的定义属性。也就是说，Spring允许BeanFactoryPostProcessor在容器实例化任何其它bean之前读取配置元数据，并可以根据需要进行修改，例如可以把bean的scope从singleton改为prototype，也可以把property的值给修改掉。
—



二、afterRefresh