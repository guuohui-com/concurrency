package com.ysu.concurrency.thread.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @功能职责:
 * @描述：
 *
 * 1，线程执行时，先发短信 还是先打电话 ？ 答案：先发短信 后打电话
 * 2，线程执行时， sendSms 延迟四秒 先发短信 还是先打电话 ？ 答案：先发短信 后打电话
 * 原因: phone 的两个方法都有synchronized，synchronized锁的是方法的调用者，即 phone ，
 * 而main方法中是同意个phone，所以只有一把锁，先拿到的线程先执行
 * 3, main 方法执行时，先发短信还是先玩游戏？ 答案: 先玩游戏
 * 原因： game方法没有锁，不受锁的影响
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class EightLock {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{phone.sendSms();}).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{phone.call();}).start();
        new Thread(() -> {phone.game();}).start();
    }

}
class Phone{
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
