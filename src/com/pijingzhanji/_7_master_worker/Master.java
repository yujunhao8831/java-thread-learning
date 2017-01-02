package com.pijingzhanji._7_master_worker;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author : 披荆斩棘
 * @date : 2017/1/2
 */
public class Master {

    // 任务队列
    private Queue                 workQueue = new ConcurrentLinkedDeque();
    // Worker进程队列
    private Map< String, Thread > threadMap = new HashMap<>();
    // 子任务处理结果集
    private Map< String, Object > resultMap = new ConcurrentHashMap<>();

    public Master ( Worker worker, int countWorker ) {
        worker.setResultMap( this.resultMap )
              .setWorkQueue( this.workQueue );
        for ( int i = 0 ; i < countWorker ; i++ ) {
            this.threadMap.put( Integer.toString( i ), new Thread( worker, Integer.toString( i ) ) );
        }
    }

    // 是否所有的子任务都都结束了
    public boolean isComplete () {
        for ( Map.Entry< String, Thread > entry : this.threadMap.entrySet() ) {
            if ( entry.getValue().getState() != Thread.State.TERMINATED ) {
                return false;
            }
        }
        return true;
    }

    // 提交一个任务
    public void submit ( Object job ) {
        this.workQueue.add( job );
    }

    // 启动所有worker进程,进处理
    public void execute () {
        this.threadMap.forEach( ( key, value ) -> {
            System.err.println( key + "开始" );
            value.start();
        } );
    }

    public Map< String, Object > getResultMap () {
        return resultMap;
    }

    public Master setResultMap ( Map< String, Object > resultMap ) {
        this.resultMap = resultMap;
        return this;
    }


}
