package com.ysu.concurrency.thread.juc.JUC;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @功能职责: TestReadWriteLock
 * @描述：
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class TestReadWriteLock {
    public static void main(String[] args) {
        Mycache mycache = new Mycache();
        for (int i = 0; i < 10; i++) {
            final int tem = i;
            new Thread(()->{
                mycache.put(tem+"",tem+"");
            },String.valueOf(i)).start();
        }

        for (int i = 0; i < 10; i++) {
            final int tem = i;
            new Thread(()->{
                mycache.read(tem+"");
            },String.valueOf(i)).start();
        }
    }
}

class Mycache{
    private volatile Map<String,Object> map = new HashMap<>();

    //读写锁，更加细粒度地控制
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    //写，只能被一个线程写入
    public void put(String key, Object value){
        //加入写锁
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"写入"+key);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入OK");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放写锁
            readWriteLock.writeLock().unlock();
        }
    }

    //读，可以被多个线程读取
    public void read(String key){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"读取"+key);
            map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取OK");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }


}