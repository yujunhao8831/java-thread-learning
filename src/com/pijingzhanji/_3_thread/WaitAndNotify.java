package com.pijingzhanji._3_thread;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author : 披荆斩棘
 * @date : 2017/1/1
 */
public class WaitAndNotify {
    private static volatile List< String > arrays = new ArrayList<>();

    ///////////////////////////////////////////////////////////////////////////
    // 这样在工作中可以做什么?
    // 比如 : 一个客服系统,100个人同一个时刻进行咨询服务,但是系统只能同时提供10人的服务
    // 那么这个时候就可以用到这种技术了
    ///////////////////////////////////////////////////////////////////////////
    

    public static void main ( String[] args ) {
        WaitAndNotify waitAndNotify = new WaitAndNotify();

        Object lock = new Object();

        Thread thread1 = new Thread( () -> {
            synchronized ( lock ) {
                for ( int i = 0 ; i < 10 ; i++ ) {
                    waitAndNotify.add();
                    System.err.println( Thread.currentThread().getName() + ":add()" );
                    try {
                        Thread.sleep( 500 );
                        if ( waitAndNotify.size() == 5 ) {
                            System.err.println( "通知线程启动" );
                            lock.notify();
                        }
                    } catch ( InterruptedException e ) {
                        e.printStackTrace();
                    }
                }
            }
        }, "线程1" );

        
        Thread thread2 = new Thread( () -> {
            synchronized ( lock ) {
                if ( waitAndNotify.size() != 5 ) {
                    try {
                        System.err.println( "wait ing" ); // 此时 lock 已经配释放,而该线程在等待中
                        lock.wait();
                    } catch ( InterruptedException e ) {
                        e.printStackTrace();
                    }
                }
                System.err.println( "当前线程:" + Thread.currentThread().getName() + "收到命令,终止线程" );
                throw new RuntimeException();
            }
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
