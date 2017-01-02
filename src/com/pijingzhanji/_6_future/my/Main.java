package com.pijingzhanji._6_future.my;

/**
 * @author : 披荆斩棘
 * @date : 2017/1/2
 * 系统启动,调用Client发出请求
 */
public class Main {

    public static void main ( String[] args ) throws InterruptedException {
        Client client = new Client();
        // 这里会立即返回,因为得到的是FutureData而不是RealData
        Data data = client.request( "name" );
        System.err.println( "请求完毕" );
        
        // 这里可以用一个sleep代替对其他业务逻辑的处理
        // 在处理这些业务逻辑的过程中,RealData被创建,从而充分利用了等待的时间
        // TimeUnit.SECONDS.sleep( 2 );
        
        // 使用真实的数据
        System.err.println( "准备得到得到使用真实的数据" );
        System.err.println( "真实的数据 = " + data.getResult() );
    }
}
