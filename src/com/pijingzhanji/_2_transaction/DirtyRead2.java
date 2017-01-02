package com.pijingzhanji._2_transaction;

import java.math.BigDecimal;

/**
 * @author : 披荆斩棘
 * @date : 2016/12/18
 */
public class DirtyRead2 {
    // 脏读
    private volatile boolean    state         = true;
    private BigDecimal currentAmount = new BigDecimal( "100000" );


    public static void main ( String[] args ) throws InterruptedException {

        DirtyRead2 dirtyRead = new DirtyRead2();

        new Thread( () -> {
            System.err.println( "start run" );
            while ( dirtyRead.isState() ) {
//                dirtyRead.setCurrentAmount( dirtyRead.getCurrentAmount().subtract( BigDecimal.ONE ) );
//                System.err.println( "currentAmount = " + dirtyRead.getCurrentAmount() );
            }
            System.err.println( "end run" );
            System.err.println( "state = " + dirtyRead.isState() );
        } ).start();

        Thread.sleep( 3000 );
        dirtyRead.setState( false );
        System.err.println( "dirtyRead.state = " + dirtyRead.isState() );


    }

    public boolean isState () {
        return state;
    }

    public DirtyRead2 setState ( boolean state ) {
        this.state = state;
        return this;
    }

    public BigDecimal getCurrentAmount () {
        return currentAmount;
    }

    public DirtyRead2 setCurrentAmount ( BigDecimal currentAmount ) {
        this.currentAmount = currentAmount;
        return this;
    }
}
