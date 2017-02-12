package com.pijingzhanji._10_disruptor;

/**
 * @author : 披荆斩棘
 * @date : 2017/1/8
 */
public class LongEvent {

    private long value;

    @Override
    public String toString () {
        return "LongEvent{" +
                "value=" + value +
                '}';
    }

    public void set ( long value ) {
        this.value = value;
    }
}
