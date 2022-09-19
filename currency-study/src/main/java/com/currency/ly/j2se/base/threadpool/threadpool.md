一、创建线程池

线程池的创建⽅式总共包含以下 7 种（其中 6 种是通过 Executors 创建的， 1 种是通过 ThreadPoolExecutor 创建的）：

 1. Executors.newFixedThreadPool：创建⼀个固定⼤⼩的线程池，可控制并发的线程数，超出的线程会在队列中等待；有坑的，它默认是无界的阻塞队列
        2. Executors.newCachedThreadPool：创建⼀个可缓存的线程池，若线程数超过处理所需，缓存⼀段时间后会回收，若线程数不够，则新建线程；
        3. Executors.newSingleThreadExecutor：创建单个线程数的线程池，它可以保证先进先出的执⾏顺序；
        4. Executors.newScheduledThreadPool：创建⼀个可以执⾏延迟任务的线程池；
        5. Executors.newSingleThreadScheduledExecutor：创建⼀个单线程的可以执⾏延迟任务的线程池；
        6. Executors.newWorkStealingPool：创建⼀个抢占式执⾏的线程池（任务执⾏顺序不确定）【JDK1.8 添加】。
        7. ThreadPoolExecutor：最原始的创建线程池的⽅式，它包含了 7 个参数可供设置，后⾯会详细讲。
二、ThreadPoolExecutor的参数

1.corePoolSize:线程池中的常驻核心线程数即使这些线程处理空闲状态，他们也不会被销毁，除非设置了allowCoreThreadTimeOut。
2.maxinumPoolSize:线程池中能够容纳同时执行的最大线程数，此值必须大于等于一
3.keepAliveTime:多余的空闲线程的存活时间。
当前线程池数量超过corePoolSize时，当空闲时间达到keepAliveTime时，多余空闲线程会被销毁直到只剩下corePoolSize个线程为止。
4.unit:keepAliveTime的单位
5.workQueue:任务队列，被提交但是尚未被执行的任务。
6.threadFactory:表示生成线程池中工作线程的线程工厂，用于创建线程一般用默认的即可。
7.handler:拒绝策略，表示当队列满了并且工作线程-大于等于线程池的数量最大线程数（maxinumPoolSize）时如何来拒绝请求执行的runnable的策略。

workQueue：
1. ArrayBlockingQueue（数组的有界阻塞队列）
2. LinkedBlockingQueue（链表的无界阻塞队列）
3.SynchronousQueue（一个不缓存任务的阻塞队列）
4. PriorityBlockingQueue（具有优先级的无界阻塞队列）
5. DelayQueue（这是一个无界阻塞延迟队列）


handler 拒绝策略

bortPolicy：丢弃任务并抛出 RejectedExecutionException 异常。（默认这种）
DiscardPolicy：丢弃任务，但是不抛出异常
DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程） 。也就是当任务被拒绝添加时，会抛弃任务队列中最旧的任务也就是最先加入队列的，再把这个新任务从队尾添加进去，等待执行。
CallerRunsPolicy：谁调用，谁处理。由调用线程（即提交任务给线程池的线程）处理该任务，如果线程池已经被shutdown则直接丢弃

如果线程池拒绝策略设置不合理，就容易有坑。我们把拒绝策略设置为DiscardPolicy或DiscardOldestPolicy并且在被拒绝的任务，Future对象调用get()方法,那么调用线程会一直被阻塞。
spring的@Async时，要避开这个坑，自己再定义一个线程池。正例如下：

@Bean(name = "threadPoolTaskExecutor")
public Executor threadPoolTaskExecutor() {
    ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(5);
    executor.setMaxPoolSize(10);
    executor.setThreadNamePrefix("tianluo-%d");
    // 其他参数设置
    return new ThreadPoolTaskExecutor();}


