package com.ysu.concurrency.thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @功能职责: 多线程下载网络图片
 * @描述：
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class DownloadPictures extends Thread {
    private String url;
    private String name;

    public DownloadPictures(String url,String name){
        this.name = name;
        this.url = url;
    }

    @Override
    public void run(){
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(this.url,this.name);
        System.out.println("下载了文件名为："+this.name);
    }

    public static void main(String[] args) {
        Thread thread1 = new DownloadPictures("https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF","1.jpg");
        Thread thread2 = new DownloadPictures("https://t7.baidu.com/it/u=4198287529,2774471735&fm=193&f=GIF","2.jpg");
        Thread thread3 = new DownloadPictures("https://t7.baidu.com/it/u=1956604245,3662848045&fm=193&f=GIF","3.jpg");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

//下载器
class WebDownloader{
    //下载方法
    public void downloader(String url, String name){
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常,downloader方法出现问题");
        }
    }
}
