package com.pijingzhanji._6_future.my;

/**
 * @author : 披荆斩棘
 * @date : 2017/1/2
 */
public class Client {

    public Data request ( String input ) {
        final FutureDate futureDate = new FutureDate();

        new Thread( () -> {
            System.err.println( "开启新的线程进行处理真实的数据" );
            // RealData的构建很慢,所以在单独的线程中进行
            RealData realData = new RealData( input );
            futureDate.setRealData( realData );
        } ).start();

        return futureDate; // 立即返回
    }


}
