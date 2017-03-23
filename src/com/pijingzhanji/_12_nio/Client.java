package com.pijingzhanji._12_nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author : 披荆斩棘
 * @date : 2017/3/22
 */
public class Client {

    private Selector selector;

    public static void main ( String[] args ) {

        // 创建链接地址
        InetSocketAddress address = new InetSocketAddress( "127.0.0.1", 8888 );
        // 建立缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate( 1024 );

        SocketChannel clientChannel = null;
        try {
            // 声明通道
            clientChannel = SocketChannel.open();
            // 连接
            clientChannel.connect( address );

            while ( true ) {
                byte[] bytes = new byte[1024];
                System.in.read( bytes );
                // 数据放入到缓冲区
                byteBuffer.put( bytes );
                // 对缓冲区进行复位
                byteBuffer.flip();
                // 写出数据
                clientChannel.write( byteBuffer );
                byteBuffer.clear();


            }
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            if ( null != clientChannel ) {
                try {
                    clientChannel.close();
                } catch ( IOException e ) {
                    e.printStackTrace();
                }
            }
        }

    }
}
