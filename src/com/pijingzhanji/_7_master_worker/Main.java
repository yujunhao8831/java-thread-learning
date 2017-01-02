package com.pijingzhanji._7_master_worker;

import java.util.Iterator;
import java.util.Map;

/**
 * @author : 披荆斩棘
 * @date : 2017/1/2
 */
public class Main {

    public static void main ( String[] args ) {
        Master master = new Master( new Worker(), 20 );
        for ( int i = 0 ; i < 100 ; i++ ) {
            master.submit( i );
        }
        master.execute();
        int  total = 0;
        long start = System.currentTimeMillis();
        while ( master.getResultMap().isEmpty() && ! master.isComplete() ) {
            // 不需要等待所有Worker都执行完,就可以开始下一步操作
            // 开始汇总
            Iterator< Map.Entry< String, Object > > iterator = master.getResultMap().entrySet().iterator();
            while ( iterator.hasNext() ) {
                Map.Entry< String, Object > next = iterator.next();
                total += ( int ) next.getValue();
                iterator.remove();
            }
        }
        while ( true ) {
            if ( master.isComplete() ) {
                long end = System.currentTimeMillis() - start;
                System.err.println( "total = " + total + ",耗时 : " + end );
                break;
            }
        }
    }

}
