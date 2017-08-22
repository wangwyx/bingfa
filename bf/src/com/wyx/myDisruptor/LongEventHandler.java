package com.wyx.myDisruptor;

import com.lmax.disruptor.EventHandler;

/**
 * Created by Administrator on 2017/8/19.
 * 3.事件处理器
 */
public class LongEventHandler implements EventHandler<LongEvent>{
    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println(longEvent.getValue());
    }
}
