package com.hgz.concurrent;

import java.util.concurrent.Semaphore;

/**
 * @author hgz
 * @version 1.0
 * @date 2021/11/1 21:27
 */
//三个线程 a、b、c 并发运行，b,c 需要 a 线程的数据怎么实现
public class ThreadCommunication {


    /**
     * 定义一个信号量，该类内部维持了多个线程锁，可以阻塞多个线程，释放多个线程，
     * 线程的阻塞和释放是通过 permit 概念来实现的
     * 线程通过 semaphore.acquire()方法获取 permit，如果当前 semaphore 有 permit 则分配给该线程，
     * 如果没有则阻塞该线程直到 semaphore
     * 调用 release（）方法释放 permit。
     * 构造函数中参数：permit（允许） 个数，
     */

    private static int num;

    private static Semaphore semaphore=new Semaphore(0);

    public static void main(String[] args) {

        new Thread(() ->
        {
            try {
                Thread.sleep(10000);
                num = 9;
                semaphore.release(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName()+"获取到 num 的值为："+num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();


        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName()+"获取到 num 的值为："+num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"C").start();
    }


}
