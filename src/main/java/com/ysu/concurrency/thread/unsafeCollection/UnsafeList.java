package com.ysu.concurrency.thread.unsafeCollection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @功能职责:
 * @描述： 非线程安全的List 解决方案
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class UnsafeList {
    public static void main(String[] args) {
        /**
         * 线程不安全
         * */
        List<String> unsafeList = new ArrayList<>();

        /**
         * 解决方案 1,Vector
         * Vector 加了synchronized 关键字，性能差
         * */
        List<String> vector = new Vector<>(unsafeList);

        /**
         * 解决方案 2,Collection.Synchroniex***
         * */
        List<String> safeList = Collections.synchronizedList(unsafeList);

        /**
         * 解决方案 3, CopyOnWriteList
         * 加了
         * */
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList(unsafeList);


        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                unsafeList.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(unsafeList);
            },String.valueOf(i));
        }
    }
}
