package com.ysu.concurrency.thread.poxy;

/**
 * @功能职责: 测试守护线程
 * @描述：
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class DaemonThread {
    public static void main(String[] args) {
        God god = new God();
        Your your = new Your();

        Thread thread = new Thread(god);
        thread.setDaemon(true);
        thread.start();

        new Thread(your ).start();
    }
}

class Your implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("你一生都开心地活着");
        }
        System.out.println("goodBy World");
    }
}

class God implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("上帝保佑你");
        }
    }
}