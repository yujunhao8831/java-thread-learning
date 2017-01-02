package com.pijingzhanji._3_thread;

/**
 * @author : 披荆斩棘
 * @date : 2017/1/1
 */
public class ThreadLocalDemo {

    private static ThreadLocal< String > threadLocal = new ThreadLocal<>();

    public static void main ( String[] args ) {


        new Thread( () -> {
            threadLocal.set( "1001" );
            System.err.println( Thread.currentThread().getName() + "|\t" + threadLocal.get() );
        }, "线程1" ).start();


        new Thread( () -> {
            System.err.println( Thread.currentThread().getName() + "|\t" + threadLocal.get() );
        }, "线程2" ).start();
    }

}
