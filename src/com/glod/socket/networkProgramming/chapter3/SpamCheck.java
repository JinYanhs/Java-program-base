package com.glod.socket.networkProgramming.chapter3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @description: 读取IP地址，判断每个IP地址是否为垃圾邮件地址的任务交给线程池来处理。任务执行的结果被存放在一个Futeure对象中
 * @author: Glod
 * @date: 2021/1/10
 */
public class SpamCheck {
    private static final int POOL_SIXE = 4;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors() * POOL_SIXE);
        BufferedReader fileReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("address.txt")));
        String addr = null;
        // 存放所有任务执行结果的集合
        Set<Future<String>> futureResults = new HashSet<Future<String>>();

        while((addr = fileReader.readLine()) != null){
            LookupTask task = new LookupTask(addr);
            Future<String> future = executorService.submit(task);

            futureResults.add(future); // 任务结果存放在futureResult集合中
        }
       /* 遍历访问futureResult集合第一种方式 会阻塞
        for (Future<String> result : futureResults){
            // 如果任务还没完成，get()方法就会阻塞，知道任务完成，返回结果
            System.out.println(result.get());
        }

        */

       // 遍历访问futureResult集合的第2种方式 不会阻塞
        //采用轮询的方式，不断遍历访问结果集，打印任务已经完成的结果
        while(!futureResults.isEmpty()){
            Iterator<Future<String>> it = futureResults.iterator();
            while(it.hasNext()){
                Future<String> future = it.next();
                if (future.isDone()){
                    System.out.println(future.get());
                    it.remove();
                }
            }
        }
        executorService.shutdown();
    }
}
