package com.ysu.concurrency.thread.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @功能职责:
 * @描述： 问题 6： 当Phone4中是一个static 同步方法，一个普通同步方法， 先发短信还是先打电话？ 答案：先打电话，后发短信
 * 原因：static synchronized 方法锁的是Class模板，而普通synchronized锁是调用者对象，这是两把锁，所以不存在竞争关系，
 * 但是由于模拟延迟的效果，所以，是先打电话，在发短信
 *
 * 注意：若此时，Phone4 new了两个对象，分别调用，此时将会存在三把锁，注意分析
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class EightLock4 {
    public static void main(String[] args) {
        Phone4 phone = new Phone4();
        new Thread(() -> {
            phone.sendSms();
        }).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            phone.call();
        }).start();
    }

}

class Phone4 {
    //静态同步方法 锁的是class 模板
    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    //普通同步方法，锁的是掉迎着对象
    public synchronized void call() {
        System.out.println("打电话");
    }
}
