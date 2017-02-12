package com.pijingzhanji._9_concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author : 披荆斩棘
 * @date : 2017/1/7
 */
public class SemaphoreDemo {
    public static void main ( String[] args ) {
        // 线程池 
        ExecutorService service = Executors.newCachedThreadPool();
        // 只能5个线程同时访问 
        final Semaphore semaphore = new Semaphore( 5 );
        // 模拟20个客户端访问 
        for ( int index = 0 ; index < 20 ; index++ ) {
            final int NO = index;
            Runnable run = () -> {
                try {
                    // 获取许可 
                    semaphore.acquire();
                    System.out.println( "Accessing: " + NO );
                    Thread.sleep( ( long ) ( Math.random() * 10000 ) );
                    // 访问完后，释放 ，如果屏蔽下面的语句，则在控制台只能打印5条记录，之后线程一直阻塞
                    semaphore.release();
                } catch ( InterruptedException e ) {
                }
            };
            service.execute( run );
        }
        // 退出线程池 
        service.shutdown();

    }
}
