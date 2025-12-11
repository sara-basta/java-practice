package com.sara.basta.threading;

public class DownloadTask {


    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    long currentTime = System.currentTimeMillis();
                    System.out.println("thread"+ Thread.currentThread().getName()+ "running : " + i);
                    try {
                        Thread.sleep(2000);
                        long diff = System.currentTimeMillis()-currentTime;
                        System.out.println("Time it took : (sequential) "+ diff);
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
        long threadStartTime = System.currentTimeMillis();
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

        try {
            t1.join();
            System.out.println("t1 finished!");
            t2.join();
            System.out.println("t2 finished!");
            t3.join();
            System.out.println("t3 finished!");
            t4.join();
            System.out.println("t4 finished!");
            t5.join();
            System.out.println("t5 finished!");
            t6.join();
            System.out.println("t6 finished!");
            t7.join();
            System.out.println("t7 finished!");
            t8.join();
            System.out.println("t8 finished!");
            t9.join();
            System.out.println("t9 finished!");
            t10.join();
            System.out.println("t10 finished!");
            long threadEndTime = System.currentTimeMillis();
            long threadDiff = threadEndTime - threadStartTime;
            System.out.println("time the main thread took (concurrency): "+ threadDiff );
        }
        catch(InterruptedException ie){
            System.out.println("Problem happened "+ie);
        }
    }
}
