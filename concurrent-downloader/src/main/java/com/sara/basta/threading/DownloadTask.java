package com.sara.basta.threading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class DownloadTask {


    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

     /*   Runnable task = new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
//                    long currentTime = System.currentTimeMillis();
                    System.out.println("thread"+ Thread.currentThread().getName()+ "running : " + i);
                    try {
                        Thread.sleep(2000);
//                        long diff = System.currentTimeMillis()-currentTime;
//                        System.out.println("Time it took : (sequential) "+ diff);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }; */

//        Thread t1 = new Thread(task);
//        Thread t2 = new Thread(task);
//        Thread t3 = new Thread(task);
//        Thread t4 = new Thread(task);
//        Thread t5 = new Thread(task);
//        Thread t6 = new Thread(task);
//        Thread t7 = new Thread(task);
//        Thread t8 = new Thread(task);
//        Thread t9 = new Thread(task);
//        Thread t10 = new Thread(task);
//        long threadStartTime = System.currentTimeMillis();
//        t1.start();
//        t2.start();
//        t3.start();
//        t4.start();
//        t5.start();
//        t6.start();
//        t7.start();
//        t8.start();
//        t9.start();
//        t10.start();

//        try {
//            t1.join();
//            System.out.println("t1 finished!");
//            t2.join();
//            System.out.println("t2 finished!");
//            t3.join();
//            System.out.println("t3 finished!");
//            t4.join();
//            System.out.println("t4 finished!");
//            t5.join();
//            System.out.println("t5 finished!");
//            t6.join();
//            System.out.println("t6 finished!");
//            t7.join();
//            System.out.println("t7 finished!");
//            t8.join();
//            System.out.println("t8 finished!");
//            t9.join();
//            System.out.println("t9 finished!");
//            t10.join();
//            System.out.println("t10 finished!");
//            long threadEndTime = System.currentTimeMillis();
//            long threadDiff = threadEndTime - threadStartTime;
//            System.out.println("time the main thread took (concurrency): "+ threadDiff );
//        }
//        catch(InterruptedException ie){
//            System.out.println("Problem happened "+ie);
//        }
        Callable<String> task = () -> {
            for (int i=1;i<=5;i++){
                final int taskId = i;
                System.out.println("Thread "+Thread.currentThread().getName()+" calling : "+i);
            }
            try {
                Thread.sleep(1000);
                return "File " + " downloaded successfully";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(3);
        long start = System.currentTimeMillis();
        List<Future<String>> futures = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            futures.add(executor.submit(() -> {
                for (int j = 1; j <= 5; j++) {
                    System.out.println("Thread " + Thread.currentThread().getName() +
                            " downloading file " + taskId + " step " + j);
                }
                try {
                    Thread.sleep(1000);
                    return "File " + taskId + " downloaded successfully";
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }));
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);
        long end = System.currentTimeMillis();
        System.out.println("Total time: " + (end - start));

        for (Future<String> f : futures) {
            try {
                System.out.println("Result: " + f.get());
            } catch (Exception e) {
                System.err.println("Failed: " + e.getCause());
            }
        }

//        long beforePool = System.currentTimeMillis();
//
//        for (int i =1; i<=10 ; i++){
//            executor.submit(task);
//        }
//
//        executor.shutdown();
//        executor.awaitTermination(1, TimeUnit.HOURS);
//        long afterPool = System.currentTimeMillis();
//        long timeTaken = afterPool - beforePool;
//        System.out.println("The time taken with executor service : "+timeTaken);

    }
}
