package com.pijingzhanji.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : 披荆斩棘
 * @date : 2016/12/11
 */
public class SynchronizedWhy {


    private              int    count       = 0;
    private final static String A           = "A";
    private final static String B           = "B";
    private              Object object      = new Object();
    private final static Object LOCK_STATIC = new Object();
    private              Lock   lock        = new ReentrantLock();
    private final static Lock   LOCK        = new ReentrantLock();


    private void print ( String input ) {
        try {
            if ( A.equalsIgnoreCase( input ) ) {
                count = 999;
                System.err.println( "A set count " );
                Thread.sleep( 1000 );
            } else {
                count = 111;
                System.err.println( "B set count " );
            }
            System.err.println( "input = " + input + ",count = " + count );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // 正确输出结果
    // A set count
    // input = A,count = 999
    // B set count
    // input = B,count = 111
    ///////////////////////////////////////////////////////////////////////////


    /**
     * 该方法输出结果(错误)
     * A set count
     * B set count
     * input = B,count = 111
     * input = A,count = 999
     *
     * @param input
     */
    private synchronized void printCount ( String input ) {
        print( input );
    }

    /**
     * 该方法输出结果(错误)
     * A set count
     * B set count
     * input = B,count = 111
     * input = A,count = 999
     *
     * @param input
     */
    private void printCount2 ( String input ) {
        synchronized ( this ) {
            print( input );
        }
    }

    /**
     * 该方法输出结果(错误)
     * A set count
     * B set count
     * input = B,count = 111
     * input = A,count = 999
     *
     * @param input
     */
    private void printCount3 ( String input ) {
        // lock 非静态字段,不同对象不会共享
        synchronized ( object ) {
            print( input );
        }
    }

    /**
     * 该方法输出结果(正确)
     * A set count
     * input = B,count = 111
     * B set count
     * input = A,count = 999
     *
     * @param input
     */
    private void printCount4 ( String input ) {
        // LOCK_STATIC 静态字段,不同对象共享一个
        synchronized ( LOCK_STATIC ) {
            print( input );
        }
    }


    /**
     * 该方法输出结果(正确)
     * A set count
     * input = B,count = 111
     * B set count
     * input = A,count = 999
     *
     * @param input
     */
    private void printCount5 ( String input ) {
        try {
            LOCK.lock();
            print( input );
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }

    /**
     * 该方法输出结果(错误)
     * A set count
     * B set count
     * input = B,count = 111
     * input = A,count = 999
     *
     * @param input
     */
    private void printCount6 ( String input ) {
        Lock lock = new ReentrantLock();
        try {
            // 错误写法,这样是自欺欺人
            lock.lock();
            print( input );
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 该方法输出结果(错误)
     * A set count
     * B set count
     * input = B,count = 111
     * input = A,count = 999
     *
     * @param input
     */
    private void printCount7 ( String input ) {
        try {
            // 错误写法,这lock不是static
            lock.lock();
            print( input );
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    // 还有就是 synchronized static 这样输出也是正确的
    private synchronized static void printCount8 ( String input ) {

    }


    public static void main ( String[] args ) {
        SynchronizedWhy s1 = new SynchronizedWhy();
        SynchronizedWhy s2 = new SynchronizedWhy();

        Thread thread1 = new Thread( ( ( Runnable ) () -> {
            s1.printCount7( A );
        } ) );

        Thread thread2 = new Thread( ( ( Runnable ) () -> {
            s2.printCount7( B );
        } ) );
        thread1.start();
        thread2.start();
    }


    ///////////////////////////////////////////////////////////////////////////
    // 正确输出结果
    // A set count
    // input = A,count = 999
    // B set count
    // input = B,count = 111
    // 要想使printCount这个方法同时只有一个线程进去,则需要多个线程共享一把锁
    // 结论 : 
    //  1. 一个对象一把锁
    //  2. 要想不发生并发,那就需要多个线程使用一把锁
    ///////////////////////////////////////////////////////////////////////////

}
