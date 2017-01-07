package com.pijingzhanji._4_queue;

import java.util.concurrent.SynchronousQueue;

/**
 * @author : 披荆斩棘
 * @date : 2017/1/2
 */
public class SynchronousQueueDemo {
    public static void main ( String[] args ) throws InterruptedException {
        SynchronousQueue queue = new SynchronousQueue();


        new Thread( () -> {
            System.err.println( "1" );
            try {
                queue.put( "111" );
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
            System.err.println( "queue =  " + queue );
        } ).start();

        new Thread( () -> {
            System.err.println( "2" );
                System.err.println( "queue =  " + queue.poll() );
        } ).start();

       


       


    }
}
