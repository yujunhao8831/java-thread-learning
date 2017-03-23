package com.pijingzhanji._11_多线程数据共享._02;

/**
 * @author : 披荆斩棘
 * @date : 2017/2/21
 */
public class Account {
    private volatile long amount = 100;

    public static void main ( String[] args ) {
        Account account = new Account();
        
        // 方式1
        new Thread( new AddRunnable( account ) ).start();
        new Thread( new SubtractRunnable( account ) ).start();
        // 方式2
        new Thread( () -> {
            account.add(); // 匿名内部类,操作同一个对象
        } ).start();

        new Thread( () -> {
            account.subtract(); // 匿名内部类,操作同一个对象
        } ).start();
    }

    public void add () {
        amount++;
        System.out.println( Thread.currentThread().getName() + ",amount增加,余额为:" + amount );
    }

    public void subtract () {
        if ( amount > 0 ) {
            amount--;
        }
        System.out.println( Thread.currentThread().getName() + ",amount减少,余额为:" + amount );
    }
}

class AddRunnable implements Runnable {
    private Account account; // 成员变量

    public AddRunnable ( Account account ) { // 这里接收
        this.account = account;
    }

    @Override
    public void run () {
        account.add(); // 这里接收的是一样的引用,那么操作的就是同一个对象,这样就做到了数据共享
    }
}

class SubtractRunnable implements Runnable {
    private Account account; // 成员变量

    public SubtractRunnable ( Account account ) { // 这里接收
        this.account = account;
    }

    @Override
    public void run () {
        account.subtract(); // 这里接收的是一样的引用,那么操作的就是同一个对象,这样就做到了数据共享
    }
}