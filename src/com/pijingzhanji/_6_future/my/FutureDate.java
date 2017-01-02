package com.pijingzhanji._6_future.my;

/**
 * @author : 披荆斩棘
 * @date : 2017/1/2
 */
public class FutureDate implements Data {

    protected RealData realData;
    protected volatile boolean isReady = false;


    public synchronized void setRealData ( RealData realData ) {
        if ( this.isReady ) {
            return;
        }
        this.realData = realData;
        this.isReady = true;
        this.notifyAll(); // 此时,RealData已经被注入,那么进行通知
    }

    @Override
    public synchronized String getResult () {
        while ( ! this.isReady ) { 
            try {
                this.wait(); // 等待,知道RealData被注入
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
        }
        return this.realData.getResult(); // 由RealData实现
    }
}
