package com.pijingzhanji._9_concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author : 披荆斩棘
 * @date : 2017/1/7
 */
public class CountDownLatchDemo {

    public static void main ( String[] args ) {
        CountDownLatch countDownLatch = new CountDownLatch( 2 );

        new Thread( () -> {
            System.err.println( "开始进行初始化" );
            try {
                System.err.println( "主方法初始化完成,等待其他线程初始化完成." );
                countDownLatch.await();
                System.err.println( "全部完成,开始做其他事情" );
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
        } ).start();


        new Thread( () -> {
            System.err.println( "线程2进行初始化" );
            try {
                Thread.sleep( 3000 );
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
            System.err.println( "线程2初始化完成." );
            countDownLatch.countDown();
        } ).start();

        new Thread( () -> {
            System.err.println( "线程3进行初始化" );
            try {
                Thread.sleep( 3000 );
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
            System.err.println( "线程3初始化完成." );
            countDownLatch.countDown();
        } ).start();
    }

}
