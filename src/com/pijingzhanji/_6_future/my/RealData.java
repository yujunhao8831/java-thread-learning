package com.pijingzhanji._6_future.my;

import java.util.concurrent.TimeUnit;

/**
 * @author : 披荆斩棘
 * @date : 2017/1/2
 */
public class RealData {
    private final String result;

    public  RealData ( String input ) {
        // RealData的构造可能很慢,需要用户等待很久
        StringBuffer buffer = new StringBuffer();
        for ( int i = 0 ; i < 10 ; i++ ) {
            buffer.append( input + "\t" );
            try {
                TimeUnit.SECONDS.sleep( 1 );
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
        }
        this.result = buffer.toString();
    }

    public String getResult () {
        return result;
    }
}
