package com.wyx.lock;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**自旋锁
 * Created by Administrator on 2017/8/9.
 * CLHlock是不停的查询前驱变量， 导致不适合在NUMA 架构下使用（在这种结构下，每个线程分布在不同的物理内存区域）
 * 从代码上 看，CLH 要比 MCS 更简单，

 CLH 的队列是隐式的队列，没有真实的后继结点属性。

 MCS 的队列是显式的队列，有真实的后继结点属性。

 JUC ReentrantLock 默认内部使用的锁 即是 CLH锁（有很多改进的地方，将自旋锁换成了阻塞锁等等）。
 */
public class CLHLock {
    public static class CLHNode {
        private volatile boolean isLocked = true;
    }
    @SuppressWarnings("unused")
    private volatile CLHNode tail;
    private static final ThreadLocal<CLHNode> LOCAL=new ThreadLocal<CLHNode>();
    private static final AtomicReferenceFieldUpdater<CLHLock,CLHNode> UPDATER = AtomicReferenceFieldUpdater.newUpdater(CLHLock.class, CLHNode.class, "tail");
    public void lock (){
        CLHNode node=new CLHNode();
        LOCAL.set(node);
        CLHNode preNode = UPDATER.getAndSet(this, node);
        if(preNode !=null){
            while(preNode.isLocked){
            }
            preNode = null;
            LOCAL.set(node);
        }
    }
    public void unlock(){
        CLHNode node = LOCAL.get();
        if(!UPDATER.compareAndSet(this, node, null)){
            node.isLocked = false;
        }
        node =null;
    }
}
