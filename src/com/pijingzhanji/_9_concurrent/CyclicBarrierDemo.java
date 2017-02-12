package com.pijingzhanji._9_concurrent;

import java.util.concurrent.*;

/**
 * @author : 披荆斩棘
 * @date : 2017/1/7
 */
public class CyclicBarrierDemo {

    ///////////////////////////////////////////////////////////////////////////
    // CyclicBarrier : 通过它可以实现让一组线程等待至某个状态之后再全部同时执行
    // 应用场景 : 
    // 抢购,比如某个商品进行抢购,需要有10个人进入后才能进行下单
    // 集齐10个人后,就可以进行下单,并且是同时进行的
    /////////////////////////////////////////////////////////////////////////

    public static void main ( String[] args ) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier( 3 );
        final ExecutorService service = Executors.newFixedThreadPool( 3 );
        service.submit( () -> {
            try {
                System.err.println( "1 开始准备" );
                Thread.sleep( 1000l );
                System.err.println( "1 准备完成" );
                cyclicBarrier.await();
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            } catch ( BrokenBarrierException e ) {
                e.printStackTrace();
            }
            System.err.println( "1 开始运行" );
        } );
        service.submit( () -> {
            try {
                System.err.println( "2 开始准备" );
                Thread.sleep( 2000l );
                System.err.println( "2 准备完成" );
                cyclicBarrier.await();
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            } catch ( BrokenBarrierException e ) {
                e.printStackTrace();
            }
            System.err.println( "2 开始运行" );
        } );
        service.submit( () -> {
            try {
                System.err.println( "3 开始准备" );
                Thread.sleep( 3000l );
                System.err.println( "3 准备完成" );
                cyclicBarrier.await();
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            } catch ( BrokenBarrierException e ) {
                e.printStackTrace();
            }
            System.err.println( "3 开始运行" );
        } );
        
        service.shutdown();

    }
}
