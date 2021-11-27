package com.hgz.concurrent;

import static java.lang.Thread.sleep;

/**
 * @author hgz
 * @version 1.0
 * @date 2021/11/1 21:42
 */
public class Station extends Thread{

    public Station(String name) {
        super(name);// 给线程名字赋值
    }

    // 为了保持票数的一致，票数要静态
    static int tick = 20;

    private static Object ob = "aa";//值是任意的


    @Override
    public void run() {
        while (tick > 0) {
            synchronized (ob) {// 这个很重要，必须使用一个锁，
                // 进去的人会把钥匙拿在手上，出来后才把钥匙拿让出来
                if (tick > 0) {
                    System.out.println(getName() + "卖出了第" + tick + "张票");
                    tick--;
                } else {
                    System.out.println("票卖完了");
                }
            }
            try {
                sleep(1000);//休息一秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        Station station1=new Station("窗口1");
        Station station2=new Station("窗口2");
        Station station3=new Station("窗口3");
        // 让每一个站台对象各自开始工作
        station1.start();
        station2.start();
        station3.start();

    }
}
