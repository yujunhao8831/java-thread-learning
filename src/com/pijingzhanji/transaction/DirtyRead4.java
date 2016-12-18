package com.pijingzhanji.transaction;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : 披荆斩棘
 * @date : 2016/12/18
 */
public class DirtyRead4 {
    private final static    Object        LOCK          = new Object();
    private static volatile AtomicInteger atomicInteger = new AtomicInteger( 0 );


    public static void main ( String[] args ) throws InterruptedException {
        Thread threads[] = new Thread[10];

        for ( int i = 0 ; i < threads.length ; i++ ) {
            threads[i] = new Thread( () -> {
                /*for ( int j = 0 ; j < 100 ; j++ ) { // 这样只能保证结果,但是不能保证每次是整数想加
                    atomicInteger.addAndGet(1); // 如果只有一条addAndGet 那是没问题的,如果多个的话还想保证每次整数相加,就得加 synchronized 
                    atomicInteger.addAndGet(2);
                    atomicInteger.addAndGet(3);
                    atomicInteger.addAndGet(4);
                }
                System.err.println( "atomicInteger = " + atomicInteger );
                */

                synchronized ( LOCK ) { // 加锁后,这样就才能保证每次是整数倍
                    for ( int j = 0 ; j < 1000 ; j++ ) {
                        atomicInteger.addAndGet( 1 );
                        atomicInteger.addAndGet( 2 );
                        atomicInteger.addAndGet( 3 );
                        atomicInteger.addAndGet( 4 ); // 这样就能保证是整数倍
                    }
                    System.err.println( "atomicInteger = " + atomicInteger );
                }

            } );
        }

        for ( int i = 0 ; i < threads.length ; i++ ) {
            threads[i].start();
        }
    }


}
