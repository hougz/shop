package com.hgz.concurrent;

/**
 * @author hgz
 * @version 1.0
 * @date 2021/11/1 21:05
 */

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * 如何控制某个方法允许并发访问线程的个数
 * Semaphore 通常我们叫它信号量， 可以用来控制同时访问特定资源的线程数量，通过协调各个线程，以保证合理的使用资源
 */
public class SemaphoreThread {


    /*tryAcquire() 尝试获得令牌，返回获取令牌成功或失败，不阻塞线程。​
            /* 业务场景 ：
            1、停车场容纳总停车量10。
            2、当一辆车进入停车场后，显示牌的剩余车位数响应的减1.
            3、每有一辆车驶出停车场后，显示牌的剩余车位数响应的加1。
            4、停车场剩余车位不足时，车辆只能在外面等待。*/

    private static Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    System.out.println("====" + Thread.currentThread().getName() + "来到停车场");
                    if (semaphore.availablePermits() == 0) {//返回可用的令牌数量
                        System.out.println("车位不足，请等待");
                    }
                    semaphore.acquire();//获取一个令牌，在获取到令牌、或者被其他线程调用中断之前线程一直处于阻塞状态
                    System.out.println(Thread.currentThread().getName()+"成功进入停车场");
                    Thread.sleep(new Random().nextInt(10000));//模拟车辆在停车场停留的时间
                    System.out.println(Thread.currentThread().getName()+"驶出停车场");
                    semaphore.release();//释放令牌，腾出停车场车位
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, i + "号车").start();
        }

    }



}
