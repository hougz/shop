package com.hgz.concurrent;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author hgz
 * @version 1.0
 * @date 2021/10/31 20:11
 */
public class Thread1 {

    public static void main(String[] args) {
        new Thread(() -> System.out.println(Thread.currentThread().getId())).start();
        System.out.println(Thread.currentThread().getId());

        //线程池
       /* ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(()-> System.out.println(Thread.currentThread().getId()));
        System.out.println("主线程:"+Thread.currentThread().getId());
        executorService.shutdown();*/

        try {
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            //线程池开启异步线程（接收返回参数）
            Future<?> future = executorService.submit(() -> {
                System.out.println(Thread.currentThread().getId());
                int a = 3;
                return a;
            });
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
    }



}
