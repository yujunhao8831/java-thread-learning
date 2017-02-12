package com.pijingzhanji._6_future.jdk;

import java.util.concurrent.*;

/**
 * @author : 披荆斩棘
 * @date : 2017/1/2
 */
public class Main {
    public static void main ( String[] args ) throws ExecutionException, InterruptedException {
        FutureTask        task    = new FutureTask( new RealService( "10001" ) );
        FutureTask        task2    = new FutureTask( () -> {
            System.err.println( "task2开始执行" );
            return "哈哈哈哈";
        } );
        ExecutorService   service = Executors.newFixedThreadPool( 2 );
        final Future< ? > submit  = service.submit( task );
        service.submit( task2 );
        System.err.println( "请求完毕" );
        // 其实在获取真实数据的时候还是需要等待,那么为什么还要这样做? 
        // 因为这是多线程操作的,这个时候可以同时多多个事情
        // 1. A操作
        // 2. B操作
        // 3. C操作
        // .. ... 这样就用空间换时间,加快了处理速度
        System.err.println( "处理结果 = " + task.get() );
        System.err.println( "处理结果2 = " + task2.get() );
        while ( true ) {
            if ( submit.get() == null ) {
                System.err.println( "任务执行完毕" );
                break;
            }
        }
        
        service.shutdown();

       
        
    }
}
