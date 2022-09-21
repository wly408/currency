package com.currency.ly.j2se.base.thread;

/**
 *  1.wait表示让线程进入到阻塞状态，
 *  2.notify表示让阻塞的线程唤醒。
 *  3.wait和notify必然是成对出现的
 *  4.wait/notify必须要强制要求放在synchronized:
 *  为了防止多线程并发运行时，程序的执行混乱问题,如果 wait 和 notify/notifyAll 不强制和 synchronized 一起使用，那么在多线程执行时，
 *  就会出现 wait 执行了一半，然后又执行了添加数据和 notify 的操作，从而导致线程一直休眠的缺陷。​
 *  5.wait与sleep区别
 *  1）sleep是线程类（Thread）的方法；wait是Object类的方法
 *  2）sleep是使线程休眠，不会释放对象锁；wait是使线程等待，释放锁
 *   sleep让出的是cpu,如果此时代码是加锁的，那么即使让出了CPU,其他线程也无法运行，因为没有得到锁；wait是让自己暂时等待，放弃对象锁，进入等待此对象的等待锁定池，只有针对此对象发出notify方法（或notifyAll）后本线程才进入对象锁定池准备获得对象锁进入运行状态。
 *  3）调用sleep进入阻塞状态；调用wait进入就绪状态

 *
 * @Author wuliangyong
 * @Date 2022/9/21 11:36
 */
public class WaitNotifySleepTest {

    public static void main(String[] args) {
        Object key = new Object();
        new Thread(){
            @Override
            public void run() {
                //不通过同步块包住的话JVM会抛出IllegalMonitorStateException异常。
                synchronized (key){
                    System.out.println("start 1");
                    try {
                        key.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("end 1");
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                //不通过同步块包住的话JVM会抛出IllegalMonitorStateException异常。
                synchronized (key){
                    System.out.println("start 2");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("end 2");
                    key.notify();
                }
            }
        }.start();
    }

}
