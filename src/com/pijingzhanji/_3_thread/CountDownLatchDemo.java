package com.pijingzhanji._3_thread;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * @author : 披荆斩棘
 * @date : 2017/1/1
 */
public class CountDownLatchDemo {
    private static volatile List< String > arrays = new ArrayList<>();

    public static void main ( String[] args ) {

        CountDownLatchDemo waitAndNotify = new CountDownLatchDemo();

        CountDownLatch countDownLatch = new CountDownLatch( 1 ); // 这里如果是2

        Thread thread1 = new Thread( () -> {
            for ( int i = 0 ; i < 10 ; i++ ) {
                waitAndNotify.add();
                System.err.println( Thread.currentThread().getName() + ":add()" );
                try {
                    Thread.sleep( 500 );
                    if ( waitAndNotify.size() == 5 ) {
                        System.err.println( "通知线程启动" );
                        countDownLatch.countDown(); // 实时通知线程 , 那么这里 countDown() 就需要执行两次,为什么要这样?有个这样一个需求,需要成功完成两件事情(或者更多),才能去通知别的线程启动
                    }
                } catch ( InterruptedException e ) {
                    e.printStackTrace();
                }
            }
        }, "线程1" );

        // CountDownLatch countDownLatch
        Thread thread2 = new Thread( () -> {
            if ( waitAndNotify.size() != 5 ) {
                try {
                    System.err.println( "await ing" ); // 此时 lock 已经配释放,而该线程在等待中
                    countDownLatch.await();
                } catch ( InterruptedException e ) {
                    e.printStackTrace();
                }
            }
            System.err.println( "当前线程:" + Thread.currentThread().getName() + "收到命令,终止线程" );
            throw new RuntimeException();
        }, "线程2" );

        thread2.start();
        thread1.start();

    }

    public void add () {
        arrays.add( UUID.randomUUID().toString() );
    }

    public int size () {
        return arrays.size();
    }
}
