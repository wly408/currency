一、Lock和syncronized的区别
synchronized是Java语言的关键字。Lock是一个接口。
synchronized不需要用户去手动释放锁，发生异常或者线程结束时自动释放锁;Lock则必须要用户去手动释放锁，如果没有主动释放锁，就有可能导致出现死锁现象。
lock可以配置公平策略,实现线程按照先后顺序获取锁。
提供了trylock方法 可以试图获取锁，获取到或获取不到时，返回不同的返回值 让程序可以灵活处理。
lock()和unlock()可以在不同的方法中执行,可以实现同一个线程在上一个方法中lock()在后续的其他方法中unlock(),比syncronized灵活的多。
二、Lock接口抽象方法 
void lock()：获取锁，如果锁不可用，则出于线程调度的目的，当前线程将被禁用，并且在获取锁之前处于休眠状态。
boolean tryLock()：如果锁可用立即返回true，如果锁不可用立即返回false；
boolean tryLock(long time, TimeUnit unit) throws InterruptedException：如果锁可用，则此方法立即返回true
void unlock()：释放锁
三、ReentrantLock
重入锁也叫做递归锁，指的是同一线程 外层函数获得锁之后 ，内层递归函数仍然有获取该锁的代码，但不受影响。避免死锁问题的,synchronized也可重入。
同一个线程，首先在set方法中获取锁，然后调用get方法，get方法中重复获取同一个锁。两个方法都执行成功。
四、ReentrantReadWriteLock
读写锁，可以分别获取读锁或写锁。也就是说将数据的读写操作分开，分成2个锁来分配给线程，从而使得多个线程可以同时进行读操作
读锁使用共享模式；写锁使用独占模式
五、公平锁与非公平锁

公平锁：就是很公平，在并发环境中，每个线程在获取锁时会先查看此锁维护的等待队列，如果为空，或者当前线程线程是等待队列的第一个，就占有锁，否则就会加入到等待队列中，以后会按照FIFO的规则从队列中取到自己
非公平锁：比较粗鲁，上来就直接尝试占有锁，如果尝试失败，就再采用类似公平锁那种方式
————————————————
版权声明：本文为CSDN博主「向上的狼」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/m0_50370837/article/details/124471888
六、Condition的使用
当满足一定条件时，调用Condition的await()方法使当前线程进入休眠状态进行等待。调用Condition的signalAll()方法唤醒因await()进入休眠的线程。
