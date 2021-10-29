package com.ysu.concurrency.thread.unsafeCollection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;

/**
 * @功能职责:
 * @描述： 非线程安全的List 解决方案
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class UnsafeMap {
    public static void main(String[] args) {
        /**
         * 线程不安全
         * */
        Map<String,String> unsafeMap = new HashMap<>();

        for (int i = 0; i < 50; i++) {
            new Thread(()->{
                unsafeMap.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,5));
                System.out.println(unsafeMap);
            },String.valueOf(i)).start();
        }


        /**
         * 解决方案 1,Hashtable
         * */
        Map<String, String> hashtable = new Hashtable<>(unsafeMap);

        /**
         * 解决方案 2,Collection.Synchroniex***
         * */
        Map<String, String> map = Collections.synchronizedMap(unsafeMap);

        /**
         * 解决方案 3,juc ConcurrentHashMap
         * */
        Map<String, String> concurrentHashMap = new ConcurrentHashMap<>(unsafeMap);
    }
}
