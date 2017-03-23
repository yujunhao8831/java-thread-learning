package com.pijingzhanji._12_nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author : 披荆斩棘
 * @date : 2017/3/19
 * 服务器端(NIO)
 */
public class Server implements Runnable {

    // 多路复用器(用于管理所有的通道(channel))
    private Selector selector;
    // 缓冲区
    private ByteBuffer readBuffer = ByteBuffer.allocate( 1024 );

    private ByteBuffer writeBuffer = ByteBuffer.allocate( 1024 );

    public Server ( int port ) {
        try {
            // 打开多路复用器
            this.selector = Selector.open();
            // 打开服务器通道
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // 设置为非阻塞
            serverSocketChannel.configureBlocking( false );
            // 绑定地址
            serverSocketChannel.bind( new InetSocketAddress( port ) );
            // 把服务器通道注册到多路复用器上,并监听阻塞时间(这里是服务器端的ServerSocketChannel注册到Selector上)
            serverSocketChannel.register( this.selector, SelectionKey.OP_ACCEPT );
            System.err.println( "服务启动成功,端口号为 : " + port );

        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    public static void main ( String[] args ) {
        new Thread( new Server( 8888 ) ).start();
    }

    @Override
    public void run () {
        while ( true ) {
            try {
                // 多路复用器开始监听(必须)
                this.selector.select();
                // 返回多路复用器已选择的结果集
                final Iterator< SelectionKey > keys = this.selector.selectedKeys().iterator();
                while ( keys.hasNext() ) {
                    // 获取一个选择的元素
                    final SelectionKey key = keys.next();
                    // 直接删除这个
                    keys.remove();
                    // 如果有效
                    if ( key.isValid() ) {
                        // 如果是可以接受的,等待客户端过来
                        if ( key.isAcceptable() ) {
                            // 开始接收,对客户端进行注册,SocketChannel
                            this.accept( key );
                        }
                        // 读状态
                        if ( key.isReadable() ) {
                            this.read( key );
                        }
                        // 写状态
                        if ( key.isWritable() ) {
                            this.write( key );
                        }
                    }
                }
            } catch ( IOException e ) {
                e.printStackTrace();
            }

        }
    }

    private void write ( SelectionKey key ) throws IOException {
        // 清空缓冲区老的数据
        this.readBuffer.clear();
        // 获取之前注册的socket通道对象
        ServerSocketChannel serverSocketChannel = ( ServerSocketChannel ) key.channel();
        
    }

    private void read ( SelectionKey key ) throws IOException {
        // 清空缓冲区老的数据
        this.readBuffer.clear();
        // 获取之前注册的socket通道对象
        SocketChannel socketChannel = ( SocketChannel ) key.channel();

        // 读取数据,到buff中
        if ( socketChannel.read( this.readBuffer ) == - 1 ) {
            key.channel().close();
            key.channel();
            return;
        }
        // 读取数据之前进行复位操作
        this.readBuffer.flip();
        // 接收缓冲区数据
        byte[] bytes = new byte[this.readBuffer.remaining()];
        // 接受缓冲区数据,把readBuffer中的数据放入到bytes中
        this.readBuffer.get( bytes );
        // 结果打印
        String body = new String( bytes ).trim();
        System.err.println( "server : " + body );
    }

    /**
     * 这里肯定是server先进来,因为server先启动,才会有服务
     *
     * @param key
     * @throws IOException
     */
    private void accept ( SelectionKey key ) throws IOException {
        // 得到服务通道
        final ServerSocketChannel serverChannel = ( ServerSocketChannel ) key.channel();
        // 服务端调用接收方法,得到客户端的SocketChannel
        final SocketChannel clientChannel = serverChannel.accept();
        System.err.println( "已经接收到客户端请求,客户端地址:" + clientChannel.getRemoteAddress() );
        clientChannel.configureBlocking( false );
        // 客户端的SocketChannel也需要注册到selector上
        clientChannel.register( this.selector, SelectionKey.OP_READ );
        System.err.println( "客户端Channel注册完成" );
    }
}






















