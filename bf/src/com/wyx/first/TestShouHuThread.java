package com.wyx.first;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Administrator on 2016/11/10.
 */
public class TestShouHuThread {
    public static void main(String args[]) {
        Deque<Event> deque=new ArrayDeque<Event>();
        WriterTask writer=new WriterTask(deque);
        for (int i=0; i<3; i++){
            Thread thread=new Thread(writer);
            thread.start();
        }
        CleanerTask cleaner=new CleanerTask(deque);
        cleaner.start();
    }
}
