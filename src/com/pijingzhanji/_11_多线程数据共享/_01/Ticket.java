package com.pijingzhanji._11_多线程数据共享._01;

/**
 * @author : 披荆斩棘
 * @date : 2017/2/21
 */
public class Ticket implements Runnable {
    private long ticketCount = 10L;

    public static void main ( String[] args ) throws InterruptedException {
        Ticket ticket = new Ticket();

        Thread[] threads = new Thread[100];
        for ( int i = 0 ; i < threads.length ; i++ ) {
            threads[i] = new Thread( ticket );
        }
        for ( int i = 0 ; i < threads.length ; i++ ) {
            threads[i].start();
        }
    }

    @Override
    public synchronized void run () {
        if ( ticketCount > 0 ) {
            ticketCount--;
            System.err.println( "购买成功" );
        } else {
            System.err.println( "购买失败,余票不足" );
        }
        System.err.println( "目前余票:" + ticketCount );
    }

}

