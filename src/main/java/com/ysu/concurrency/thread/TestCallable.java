package com.ysu.concurrency.thread;

import java.util.concurrent.*;

/**
 * @功能职责:
 * @描述：
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class TestCallable implements Callable<Boolean> {
    @Override
    public Boolean call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return null;
    }

    public static void main(String[] args) {
        Callable<Boolean> callable1 = new TestCallable();
        Callable<Boolean> callable2 = new TestCallable();
        Callable<Boolean> callable3 = new TestCallable();

        //创建执行服务
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        //提交执行
        Future<Boolean> submit1 = executorService.submit(callable1);
        Future<Boolean> submit2 = executorService.submit(callable2);
        Future<Boolean> submit3 = executorService.submit(callable3);

        //获取执行结果
        try {
            Boolean aBoolean1 = submit1.get();
            Boolean aBoolean2 = submit2.get();
            Boolean aBoolean3 = submit3.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //关闭服务
        executorService.shutdown();

    }
}
