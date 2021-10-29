package com.ysu.concurrency.thread.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @功能职责: Callable 接口
 * @描述：
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class TestCallable {
    public static void main(String[] args) {

        MyThread thread = new MyThread();
        //为了适配Runnable，使用FutureTask中转
        FutureTask futureTask = new FutureTask(thread);
        new Thread(futureTask).start();
        new Thread(futureTask).start();
        String result = null;
        try {
            result = (String)futureTask.get(); // 可能会产生阻塞，因为callable接口的实现中可能有阻塞，一旦阻塞，get就会等待
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }
}

class MyThread implements Callable<String>{

    @Override
    public String  call() throws Exception {
        System.out.println("callable");
        return "1024";
    }
}