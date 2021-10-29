package com.ysu.concurrency.thread.unsafeCollection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @功能职责:
 * @描述： 非线程安全的List 解决方案
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class UnsafeSet {
    public static void main(String[] args) {
        /**
         * 线程不安全
         * */
        Set<String> unsafeSet = new HashSet<>();


        /**
         * 解决方案 1,Collection.Synchroniex***
         * */
        Set<String> ts = Collections.synchronizedSet(unsafeSet);

        /**
         * 解决方案 2, CopyOnWriteList
         * 加了
         * */
        CopyOnWriteArraySet<String> copyOnWriteArrayList = new CopyOnWriteArraySet(unsafeSet);


        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                unsafeSet.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(unsafeSet);
            },String.valueOf(i));
        }
    }
}
