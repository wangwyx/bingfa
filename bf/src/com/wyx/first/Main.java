package com.wyx.first;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

/**
 * Created by Administrator on 2016/11/8.
 */
public class Main {
    public static void main(String[] args) {
        Thread[] thread = new Thread[10];
//      Thread thread1[] = new Thread[10];
        Thread.State[] states = new Thread.State[10];
        for (int i=0;i<10;i++) {
            thread[i]=new Thread(new Calculator(i));
            if ((i % 2)==0)
                thread[i].setPriority(Thread.MAX_PRIORITY);
            else thread[i].setPriority(Thread.MIN_PRIORITY);
            thread[i].setName("Thread "+i);
        }

        try {
            FileWriter file = new FileWriter(filepath()+"\\bf\\data\\log.txt");
            PrintWriter pw = new PrintWriter(file);
            for (int i=0;i<10;i++) {
                pw.println("Main : Status of Thread "+i+" : " +thread[i].getState());
                states[i]=thread[i].getState();
            }
            for (int i=0;i<10;i++) {
                thread[i].start();
            }
            boolean finish = false;
            while (!finish) {
                for (int i=0;i<10;i++) {
                    if (thread[i].getState() != states[i]) {
                        writeThreadInfo(pw,thread[i],states[i]);
                        states[i] = thread[i].getState();
                    }
                }
                finish = true;
                for (int i=0;i<10;i++) {
                    finish = finish && (thread[i].getState() == Thread.State.TERMINATED);
                }
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }

    }

    private static void writeThreadInfo(PrintWriter pw, Thread thread, Thread.State state) {
        pw.printf("Main : Id %d - %s\n",thread.getId(),thread.getName());
        pw.printf("Main : Priority: %d\n",thread.getPriority());
        pw.printf("Main : Old State: %s\n",state);
        pw.printf("Main : New State: %s\n",thread.getState());
        pw.printf("Main : ************************************\n");
    }

    /**
     *
     * @return
     */
    private static String filepath(){
        String className = Main.class.getName();
        String classNamePath = className.replace(".", "/") + ".class";
        URL is = Main.class.getClassLoader().getResource(classNamePath);
        String path = "";
        //System.out.println(path);
        //System.out.println(System.getProperty("com.wyx.first"));
        try {
            File directory = new File("");
            System.out.println(directory.getAbsolutePath());
            System.out.println(directory.getCanonicalPath());
            path = directory.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

}
