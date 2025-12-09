package com.sara.basta.threading;

public class DownloadTask {


    public static void main(String[] args) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    long currentTime = System.currentTimeMillis();
                    System.out.println("thread"+ Thread.currentThread().getName()+ "running : " + i);
                    try {
                        Thread.sleep(2000);
                        long diff = System.currentTimeMillis()-currentTime;
                        System.out.println("Time it took : "+ diff);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);
        Thread t4 = new Thread(runnable);
        Thread t5 = new Thread(runnable);
        Thread t6 = new Thread(runnable);
        Thread t7 = new Thread(runnable);
        Thread t8 = new Thread(runnable);
        Thread t9 = new Thread(runnable);
        Thread t10 = new Thread(runnable);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
    }
}
