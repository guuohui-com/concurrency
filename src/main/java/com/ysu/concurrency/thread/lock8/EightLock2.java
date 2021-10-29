package com.ysu.concurrency.thread.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @功能职责:
 * @描述：
 * 4，单号main线程执行时，先发短信，还是先打电话 ？ 答案：先打电话
 * 原因：两个线程 操作的两个对象，互不影响，由于发短信方法延迟四秒，所以后打印
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class EightLock2 {
    public static void main(String[] args) {
        Phone2 phone1 = new Phone2();
        Phone2 phone2 = new Phone2();
        new Thread(()->{phone1.sendSms();}).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{phone2.call();}).start();
    }

}

class Phone2{
    public synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public synchronized void call(){
        System.out.println("打电话");
    }
    public void game(){
        System.out.println("玩游戏");
    }
}
