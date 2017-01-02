package com.pijingzhanji._1_sync;

/**
 * @author : 披荆斩棘
 * @date : 2016/12/11
 */
public enum SynchronizedWhy2 {

    INSTANCE;

    ///////////////////////////////////////////////////////////////////////////

    private synchronized void printMessage1 () {
        System.err.println( Thread.currentThread().getName() );
        try {
            Thread.sleep( 3000 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }

    // 这两个方法同时打印,printMessage1和printMessage2同时被执行

    private void printMessage2 () {
        System.err.println( Thread.currentThread().getName() );
    }

    ///////////////////////////////////////////////////////////////////////////


    ///////////////////////////////////////////////////////////////////////////

    private synchronized void printMessage3 () {
        System.err.println( Thread.currentThread().getName() );
        try {
            Thread.sleep( 3000 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }

    // printMessage3 和printMessage4 会等待其中一个先被调用的方法执行完后才会执行,因为都加入了锁

    private synchronized void printMessage4 () {
        System.err.println( Thread.currentThread().getName() );
    }

    ///////////////////////////////////////////////////////////////////////////


    public static void main ( String[] args ) {
        
        new Thread( ( ( Runnable ) () -> {
            SynchronizedWhy2.INSTANCE.printMessage3();
        } ) ).start();

        new Thread( ( ( Runnable ) () -> {
            SynchronizedWhy2.INSTANCE.printMessage4();
        } ) ).start();
    }


}
