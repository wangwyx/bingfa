package com.wyx.myDisruptor;

/**
 * Created by Administrator on 2017/8/19.
 * 首先声明一个Event来包含需要传递的数据
 */
public class LongEvent {
    private long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
