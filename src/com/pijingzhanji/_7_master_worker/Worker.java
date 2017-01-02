package com.pijingzhanji._7_master_worker;

import java.util.Map;
import java.util.Queue;
import java.util.Random;

/**
 * @author : 披荆斩棘
 * @date : 2017/1/2
 */
public class Worker implements Runnable {
    // 任务队列
    private Queue                 workQueue;
    // 子任务处理结果集
    private Map< String, Object > resultMap;


    @Override
    public void run () {
        while ( true ) {
            // 获取子任务 
            Object work = this.workQueue.poll();
            if ( null == work ) {
                break;
            }
            // 处理子任务
            Object result = this.handle( work );
            // 将处理结果写入结果集
            this.resultMap.put( Integer.toString( work.hashCode() ), result );

        }
    }

    // 子任务具体如何处理
    public Object handle ( Object work ) {
        try {
            Thread.sleep( 2000 ); // 比如处理一个需要2秒
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
        return ( int ) work * new Random( 100 ).nextInt();
    }


    public Queue getWorkQueue () {
        return workQueue;
    }

    public Worker setWorkQueue ( Queue workQueue ) {
        this.workQueue = workQueue;
        return this;
    }

    public Map< String, Object > getResultMap () {
        return resultMap;
    }

    public Worker setResultMap ( Map< String, Object > resultMap ) {
        this.resultMap = resultMap;
        return this;
    }


}

