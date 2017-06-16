package com.wyx.first;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/11/8.
 */
public class FileSearch implements Runnable{
    private String initPath;
    private String fileName;

    public FileSearch(String initPath, String fileName) {
        this.initPath = initPath;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        File file = new File(initPath);
        if (file.isDirectory()) {
            try {
                directoryProcess(file);
            } catch (InterruptedException e) {
                System.out.printf("%s: The search has been interrupted",Thread.currentThread().getName());
            }
        }
    }

    /**
     * 获取文件夹的文件和子文件夹并处理他们
     * @param file
     * @throws InterruptedException
     */
    private void directoryProcess(File file) throws
            InterruptedException {
        File list[] = file.listFiles();
        if (list != null) {
            for (int i = 0; i < list.length; i++) {
                if (list[i].isDirectory()) {
                    directoryProcess(list[i]);
                } else {
                    fileProcess(list[i]);
                }
            }
        }
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
    }

    /**
     * 比较文件的名字与我们要搜索的文件名
     * @param file
     * @throws InterruptedException
     */
    private void fileProcess(File file) throws InterruptedException{
        if (file.getName().equals(fileName)) {
            System.out.printf("%s : %s\n",Thread.currentThread().getName() ,file.getAbsolutePath());
        }
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
    }
    public static void main(String args[]){
        FileSearch searcher=new FileSearch("E:\\wyx","autoexec.bat");
        Thread thread=new Thread(searcher);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
