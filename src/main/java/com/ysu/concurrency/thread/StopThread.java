package com.ysu.concurrency.thread;

/**
 * @功能职责: 测试stop
 * @描述： 1， 建议线程正常停止，利用次数，不建议死循环
 *          2，建议使用标志位
 *          3，不要使用stop()或者的destory()方法
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class StopThread implements Runnable{

    private boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while(flag){
            System.out.println("run------------Thread"+i++);
        }
    }

    //设置现成终止方法
    public void stop(){
        this.flag = false;
    }

    public static void main(String[] args) {
        StopThread stopThread = new StopThread();
        new Thread(stopThread).start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("=====main=====" + i);
            if (i==900){
                stopThread.stop();
                System.out.println("========线程终止=======");
            }
        }
    }
}
