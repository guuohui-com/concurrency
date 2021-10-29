package com.ysu.concurrency.thread.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @功能职责:
 * @描述：
 * 5, Phone3 中的两个方法加了static 关键字，此时是先发短信，还是先打电话 答案： 先发短信，再打电话
 * 原因：static 修饰的静态方法，在类一加载就有了，synchronized锁的是class
 * 即：当方法被static 修饰后，方法的调用者就不再是new 出来的实例对象，而是Class对象
 * 而synchronized锁的是方法调用者，此时便是Phone3.Class对象
 * 所以只有一把锁，先拿到锁的先执行
 *
 * 注意此时再 将Phone3 new1出来两个实例对象进行调用static 方法时，锁的依然是同意个Class对象，只是有一把锁
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class EightLock3 {
    public static void main(String[] args) {
        Phone3 phone = new Phone3();
        new Thread(()->{phone.sendSms();}).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{phone.call();}).start();
    }

}

class Phone3{
    public static synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public static synchronized void call(){
        System.out.println("打电话");
    }
}
