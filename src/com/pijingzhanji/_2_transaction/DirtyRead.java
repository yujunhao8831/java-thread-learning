package com.pijingzhanji._2_transaction;

import java.math.BigDecimal;

/**
 * @author : 披荆斩棘
 * @date : 2016/12/18
 */
public class DirtyRead {
    // 脏读
    private String     username      = "张雨绮";
    private BigDecimal currentAmount = new BigDecimal( "100" );

    public static void main ( String[] args ) throws InterruptedException {

        DirtyRead dirtyRead = new DirtyRead();

        new Thread( () -> {
            dirtyRead.setValue( "披荆斩棘", BigDecimal.ONE );
        } ).start();

        Thread.sleep( 1000l );
        dirtyRead.getValue();

    }


    @Override
    public String toString () {
        return "DirtyRead{" +
                "username='" + username + '\'' +
                ", currentAmount=" + currentAmount +
                '}';
    }


    public synchronized void setValue ( String username, BigDecimal currentAmount ) {
        this.username = username;

        try {
            Thread.sleep( 2000 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
        this.currentAmount = currentAmount;

        System.err.println( "dirtyRead.setValue = " + this );
    }


    public void getValue () {
        System.err.println( "dirtyRead.getValue = " + this );
    }

}
