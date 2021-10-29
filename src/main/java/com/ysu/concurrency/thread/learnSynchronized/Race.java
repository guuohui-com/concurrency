package com.ysu.concurrency.thread.learnSynchronized;

/**
 * @功能职责:
 * @描述：
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class Race implements Runnable {

    //胜利者
    private static String winner;

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            boolean flag = isGameOver(i);
            if (flag == true){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"跑了"+i+"米");
        }
    }

    //判断是否完成比赛
    private boolean isGameOver(int steps){
        if (winner != null){
            return true;
        }
        {
            if (steps == 100){
                winner = Thread.currentThread().getName();
                System.out.println(winner+"赢了");
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Race race = new Race();
        Thread thread1 = new Thread(race,"rabbit");
        Thread thread2 = new Thread(race,"tortoise");
        thread1.start();
        thread2.start();
    }
}
