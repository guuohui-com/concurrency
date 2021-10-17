package com.ysu.concurrency.thread;

/**
 * @功能职责: 线程加入（插队）
 * @描述： 插队线程强制执行，其它 线程等待
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class JoinThread {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100 ; i++){
                    System.out.println("============大神来了，烦请让路==========="+i);
                }
            }
        });
        thread.start();

        for (int i = 0; i < 1000; i++) {
            if (i == 200){
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("main"+i);
        }
    }
}
