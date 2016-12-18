package com.pijingzhanji.transaction;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : 披荆斩棘
 * @date : 2016/12/18
 */
public class DirtyRead3 {
    // 脏读
    private static volatile int           count         = 0;
    private static volatile AtomicInteger atomicInteger = new AtomicInteger( 0 );


    public static void main ( String[] args ) throws InterruptedException {
        Thread threads[] = new Thread[10];

        for ( int i = 0 ; i < threads.length ; i++ ) {
            threads[i] = new Thread( () -> {
                for ( int j = 0 ; j < 10000 ; j++ ) {
                    DirtyRead3.count++;
                    atomicInteger.incrementAndGet();
                }
                System.err.println( "count = " + DirtyRead3.count );
                System.err.println( "atomicInteger = " + atomicInteger );
            } );
        }

        for ( int i = 0 ; i < threads.length ; i++ ) {
            threads[i].start();
        }
    }


}
