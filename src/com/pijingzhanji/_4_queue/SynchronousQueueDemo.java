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
            try {
                System.err.println( "queue =  " + queue.take() );
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
        } ).start();

        new Thread( () -> {
            try {
                queue.put( "111" );
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
            System.err.println( "queue =  " + queue );
        } ).start();


       


    }
}
