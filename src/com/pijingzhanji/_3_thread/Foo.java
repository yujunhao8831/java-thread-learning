package com.pijingzhanji._3_thread;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author : 披荆斩棘
 * @date : 2017/1/1
 */
public class Foo {
    private static volatile List< String > arrays = new ArrayList<>();

    public static void main ( String[] args ) {

        Foo foo = new Foo();

        Thread thread1 = new Thread( () -> {
            for ( int i = 0 ; i < 10 ; i++ ) {
                foo.add();
                System.err.println( Thread.currentThread().getName() + ":add()" );
                try {
                    Thread.sleep( 500 );
                } catch ( InterruptedException e ) {
                    e.printStackTrace();
                }
            }
        }, "线程1" );

        Thread thread2 = new Thread( () -> {
            while ( true ) {
                if ( foo.size() == 5 ) {
                    System.err.println( "当前线程:" + Thread.currentThread().getName() + "收到命令,终止线程" );
                    throw new RuntimeException();
                }
            }
        }, "线程2" );

        thread1.start();
        thread2.start();

    }

    public void add () {
        arrays.add( UUID.randomUUID().toString() );
    }

    public int size () {
        return arrays.size();
    }

}
