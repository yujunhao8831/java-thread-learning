package com.pijingzhanji._1_sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : 披荆斩棘
 * @date : 2016/12/11
 */
public class SynchronizedWhy3 {


    private final static Object LOCK_STATIC = new Object();
    private final static Lock   LOCK        = new ReentrantLock();
    private              Object object      = new Object();
    private              Lock   lock        = new ReentrantLock();

    
    public void test () {
        synchronized ( object ) {

        }
        synchronized ( LOCK_STATIC ) {

        }
        
        
        
    } 
    
    public static void main ( String[] args ) {
        
    }


}
