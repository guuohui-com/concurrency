package com.ysu.concurrency.thread.juc;



/**
 * @功能职责: 生产者消费者模型-----》管程法
 * @描述：
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class PorductConsumer {
    public static void main(String[] args) {
        SynContainer container = new SynContainer();
        new Prodcutor(container).start();
        new Consumer(container).start();
    }
}

//生产者
class Prodcutor extends Thread{
    SynContainer container;

    public Prodcutor(SynContainer container){
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 1; i<= 100; i++){
            System.out.println("生产"+i+"只鸡");
            container.push(new Chicken(i));
        }
    }
}
//消费者
class Consumer extends Thread{
    SynContainer container;

    public Consumer(SynContainer container){
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 1; i<= 100; i++){
            System.out.println("消费"+container.pop().id+"只鸡");
        }
    }
}
//产品
class Chicken{
    int id;//产品编号
    public Chicken(int id){
        this.id = id;
    }
}

//缓冲区
class SynContainer{
    //定义容器大小
    Chicken[] chickens = new Chicken[10];
    //容器中的·1产品计数器
    int count = 0;
    //生产者放入产品
    public synchronized void push(Chicken chicken){
        //如果容器满了，等待消费者进行消费
        if (count == chickens.length){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //如果没有满，将产品放入缓冲区
        chickens[count] = chicken;
        count++;

        //生产完了，通知消费者消费
        this.notify();
    }

    public synchronized Chicken pop(){
        //判断能否消费
        if (count == 0){
            //等待生产者消费
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //如果可以消费
        count--;
        Chicken chicken = chickens[count];

        //吃完了，通知生产者生产
        this.notify();
        return chicken;
    }
}
