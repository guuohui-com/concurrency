package com.ysu.concurrency.thread.learnSynchronized.safeThread;

/**
 * @功能职责:
 * @描述：
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */

public class UnsafeBank {
    public static void main(String[] args) {
        Account account = new Account(100,"结婚基金");
        Drawing you = new Drawing(account,50,"你");
        Drawing she = new Drawing(account,100,"她");
        you.start();
        she.start();
    }
}

class Account{
    int money;//钱
    String name;//卡名

    public Account(int money, String name){
        this.money= money;
        this.name = name;
    }
}

class Drawing extends Thread{
    Account account;
    int drawingMoney;
    int nowMoney;
    public Drawing(Account account, int drawingMoney, String name){
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        if (account.money - drawingMoney < 0){
            System.out.println("钱不够了，取不了");
            return;
        }

        //模拟网络延迟
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //卡内余额 = 余额 - 要取走的钱
        account.money = account.money - drawingMoney;
        //手里的钱 = 手里的钱 + 取出的钱
        nowMoney = nowMoney + drawingMoney;
        System.out.println(account.name + "的钱为：" + account.money);
        System.out.println(this.getName() + "手里的钱是："+nowMoney);
    }
}