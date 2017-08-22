package com.wyx.myDisruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Created by Administrator on 2017/8/19.
 * 2.由于需要让Disruptor为我们创建事件，我们同时还声明了一个EventFactory来实例化Event对象。
 */
public class LongEventFactory implements EventFactory{
    @Override
    public Object newInstance() {
        return new LongEvent();
    }
}
