package com.ysu.concurrency.thread.juc.JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @功能职责: 测试Conditiojn 做到精准唤醒
 * @描述：
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class TestCondition {
    public static void main(String[] args) {
        JucConditionSource source = new JucConditionSource();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                source.printA();
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                source.printB();
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                source.printC();
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                source.printD();
            }
        }).start();
    }
}

class JucConditionSource{
     private int num = 1;

     Lock lock = new ReentrantLock();
     Condition conditionA = lock.newCondition();
     Condition conditionB = lock.newCondition();
     Condition conditionC = lock.newCondition();
     Condition conditionD = lock.newCondition();

     public void printA(){
         lock.lock();
         try {
             while(num !=1) conditionA.await();
             System.out.println("AAAA=>"+num);
             num=2;
             conditionB.signal();
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             lock.unlock();
         }
     }

     public void printB(){
         lock.lock();
         try {
             while (num != 2) conditionB.await();
             System.out.println("BBBB=>"+num);
             num = 3;
             conditionC.signal();
         }catch (Exception e){
             e.printStackTrace();
         }finally {
             lock.unlock();
         }

     }

     public void printC(){
         lock.lock();
         try {
             while (num != 3) conditionC.await();
             System.out.println("CCCC=>"+num);
             num = 4;
             conditionD.signal();
         }catch (Exception e){
             e.printStackTrace();
         }finally {
             lock.unlock();
         }
     }

    public void printD(){
        lock.lock();
        try {
            while (num != 4) conditionD.await();
            System.out.println("DDDD=>"+num);
            num = 1;
            conditionA.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
