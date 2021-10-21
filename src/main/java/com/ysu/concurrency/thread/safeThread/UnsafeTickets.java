package com.ysu.concurrency.thread.safeThread;

/**
 * @功能职责:
 * @描述：
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class UnsafeTickets {
    public static void main(String[] args) {
        new Thread(new BuyTickets(),"小明").start();
        new Thread(new BuyTickets(),"小李").start();
        new Thread(new BuyTickets(),"小张").start();
    }
}
class BuyTickets implements Runnable{

    private int tickets = 10;
    boolean flag = true;
    @Override
    public void run() {
        while (flag){
            if (tickets <=0 ){
                flag = false;
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"拿到"+tickets--);
        }
    }
}
