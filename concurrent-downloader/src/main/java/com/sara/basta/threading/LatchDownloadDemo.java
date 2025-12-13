package com.sara.basta.threading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class LatchDownloadDemo {

    private static final int TASK_COUNT = 5;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        CountDownLatch latch = new CountDownLatch(TASK_COUNT);

        long globalStart = System.currentTimeMillis();
        System.out.println("Main - starting " + TASK_COUNT + " download tasks at " + globalStart);

        List<Future<String>> futures = new ArrayList<>();

        for (int i = 1; i <= TASK_COUNT; i++) {
            final int fileId = i;
            futures.add(pool.submit(() -> {
                long start = System.currentTimeMillis();
                Worker.log("File " + fileId + " - start at " + start);
                try {
                    Thread.sleep(1000 * fileId);
                    long end = System.currentTimeMillis();
                    Worker.log("File " + fileId + " - end at " + end +
                            " (duration " + (end - start) + " ms)");
                    return "File " + fileId + " downloaded successfully";
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    Worker.log("File " + fileId + " - interrupted");
                    return "File " + fileId + " failed";
                } finally {
                    latch.countDown();
                }
            }));
        }

        latch.await();
        long globalEnd = System.currentTimeMillis();
        System.out.println("Main - all downloads finished at " + globalEnd +
                " (total " + (globalEnd - globalStart) + " ms)");

        for (Future<String> f : futures) {
            try {
                System.out.println("Result: " + f.get());
            } catch (ExecutionException e) {
                System.err.println("Result failed: " + e.getCause());
            }
        }

        pool.shutdown();
    }
}
