package com.pijingzhanji._6_future.jdk;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author : 披荆斩棘
 * @date : 2017/1/2
 */
public class Main {
    public static void main ( String[] args ) throws ExecutionException, InterruptedException {
        FutureTask task = new FutureTask(new RealService("10001"));
        ExecutorService service = Executors.newFixedThreadPool( 1 );
        service.submit( task );
        System.err.println( "请求完毕" );
        // 其实在获取真实数据的时候还是需要等待,那么为什么还要这样做? 
        // 因为这是多线程操作的,这个时候可以同时多多个事情
        // 1. A操作
        // 2. B操作
        // 3. C操作
        // .. ... 这样就用空间换时间,加快了处理速度
        System.err.println( "处理结果 = " + task.get() );
        service.shutdown();

    }
}
