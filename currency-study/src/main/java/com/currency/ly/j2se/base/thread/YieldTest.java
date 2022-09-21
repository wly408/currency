package com.currency.ly.j2se.base.thread;

/**
 * Thread的静态变量
 * 暂停当前线程，以便其他线程有机会执行，不过不能指定暂停的时间，并且也不能保证当前线程马上停止
 * yield()虽然可以让线程由“运行状态”进入到“就绪状态”；但是，它不一定会让其它线程获取CPU执行权(即，其它线程进入到“运行状态”)，即使这个“其它线程”与当前调用yield()的线程具有相同的优先级。
 * @Author wuliangyong
 * @Date 2022/9/21 14:21
 */
public class YieldTest {

    public static void main(String[] args){
        ThreadA t1 = new ThreadA("t1");
        ThreadA t2 = new ThreadA("t2");
        t1.start();
        t2.start();
    }
}

class ThreadA extends Thread{
    public ThreadA(String name){
        super(name);
    }
    public synchronized void run(){
        for(int i=0; i <10; i++){
            System.out.printf("%s [%d]:%d\n", this.getName(), this.getPriority(), i);
            // i整除4时，调用yield
            if (i%4 == 0)
                Thread.yield();
        }
    }
}


