package com.pijingzhanji;

import java.lang.reflect.InvocationTargetException;

/**
 * @author : 披荆斩棘
 * @date : 2017/2/20
 */
public class Test {
    public static void main ( String[] args ) throws NoSuchMethodException, IllegalAccessException,
                                                     InvocationTargetException, InstantiationException {

        new Thread( () -> {

            for ( int i = 1 ; i <= 100 ; i++ ) {
                try {
                    Thread.sleep( 1000l );
                    System.err.println( "i = " + i );
                    if ( i % 3 == 0 ) {
                        System.err.println( 0 / 0 );
                    }
                } catch ( InterruptedException e ) {
                    e.printStackTrace();
                }
            }


        } ).start();


    }
}
