package com.ysu.concurrency.thread.poxy;

/**
 * @功能职责: 静态代理学习
 * @描述： 静态代理模式总结： 真是对象和代理对象都要实现同一个接口，代理对象要代理真实角色
 *          代理对象可以做很多真实对象做不了的事情，真实对象专注自己的事情
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class StaticPoxy {
    public static void main(String[] args) {
        new Thread(()->System.out.println("love you")).start();
        new WeddingCompany(new You()).happyMarry();
    }
}

interface Marry{
    void happyMarry();
}

//真实角色
class You implements Marry{

    @Override
    public void happyMarry() {
        System.out.println(Thread.currentThread().getName()+"+++++++++ happyMarry +++++++++");
    }
}

//代理角色
class WeddingCompany implements Marry{
    //代理的真实目标角色
    private Marry target;

    public WeddingCompany(Marry target){
        this.target = target;
    }

    @Override
    public void happyMarry() {
        before();
        this.target.happyMarry();
        after();
    }

    private void before(){
        System.out.println("before");
    }

    private void after(){
        System.out.println("after");
    }
}