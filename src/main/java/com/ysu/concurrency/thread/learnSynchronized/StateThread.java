package com.ysu.concurrency.thread.learnSynchronized;


/**
 * @功能职责:
 * @描述：
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class StateThread {
    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            for (int i = 0; i < 5; i++){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //观察线程创建状态---------NEW
        System.out.println(thread.getState());

        //观察线程启动-----------RUNNABLE
        thread.start();
        System.out.println(thread.getState());

        //线程等待-------------TIMED_WAITING
        while(thread.getState() != Thread.State.TERMINATED){//线程状态不是停止，就一直输出
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(thread.getState());
        }
        //线程停止------------TERMINATED
    }
}
