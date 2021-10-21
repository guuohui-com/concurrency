package com.ysu.concurrency.thread.safeThread;

/**
 * @功能职责:
 * @描述：
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class DeadLock {

    public static void main(String[] args) {
        Makeup girl1 = new Makeup(0,"灰姑娘");
        Makeup girl2 = new Makeup(1,"白雪公主");
        girl1.start();
        girl2.start();
    }
}
class Lipstick{
}
class Mirror{
}
class Makeup extends Thread{
    static Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();

    int choice;
    String girlName;

    Makeup(int choice,String girlName){
        this.choice = choice;
        this.girlName = girlName;
    }

    @Override
    public void run() {
        makeup();
    }

    private void makeup(){
        if (choice == 0){
            synchronized (lipstick){
                System.out.println(this.girlName+"拿到了口红的锁");
                synchronized (mirror){
                    System.out.println(this.girlName+"拿到了镜子的锁");
                }
            }
        }else {
            synchronized (mirror){
                System.out.println(this.girlName+"拿到了镜子的锁");
                synchronized (lipstick){
                    System.out.println(this.girlName+"拿到了口红的锁");
                }
            }
        }
        System.out.println(this.girlName+"化妆完成");
    }
}
