package com.ysu.concurrency.thread.lambda;

/**
 * @功能职责:
 * @描述：
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class LearnLambda {

    //静态内部类
    static class Like2 implements Fun{
        @Override
        public void lambda() {
            System.out.println("====Like2====");
        }
    }

    public static void main(String[] args) {
        Fun like;
        like = new Like1();
        like.lambda();

        like = new Like2();
        like.lambda();

        //局部内部类
        class Like3 implements Fun{
            @Override
            public void lambda() {
                System.out.println("====Like3====");
            }
        }
        like = new Like3();
        like.lambda();

        //匿名内部类
        like = new Fun() {
            @Override
            public void lambda() {
                System.out.println("======Like4=====");
            }
        };
        like.lambda();

        //lambda表达式
        like = ()->{
            System.out.println("=====Like5===");
        };
        like.lambda();
    }
}

//函数式接口
interface Fun{
    void lambda();
}

class Like1 implements Fun{
    @Override
    public void lambda() {
        System.out.println("====Like1====");
    }
}