package com.pijingzhanji._6_future.jdk;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author : 披荆斩棘
 * @date : 2017/1/2
 */
public class RealService implements Callable {
    private RealDao realDao = new RealDao();
    private String  id;

    public RealService ( String id ) {
        this.id = id;
    }

    @Override
    public Object call () throws Exception {
        // 一些琐碎的业务逻辑,比如判断是否存在缓存中等等.
        TimeUnit.SECONDS.sleep( 2 );
        return this.realDao.find( this.id );
    }

    private class RealDao {
        private Map< String, String > realMap = new ConcurrentHashMap<>();

        {
            realMap.put( "10001", "大哥张" );
            realMap.put( "10002", "李美琪" );
            realMap.put( "10003", "美女2号" );
            realMap.put( "10006", "张雨绮" );
        }


        public Object find ( String id ) {
            return this.realMap.get( id );
        }
    }
}
