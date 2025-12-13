package com.sara.basta.threading;

public class RaceConditionDemo {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        int threads = 10;
        int iterations = 100_000;

        Thread[] workers = new Thread[threads];

        for (int t = 0; t < threads; t++) {
            workers[t] = new Thread(() -> {
                for (int i = 0; i < iterations; i++) {
                    counter.increment();
                }
            });
        }

        long start = System.currentTimeMillis();
        for (Thread w : workers) w.start();
        for (Thread w : workers) w.join();
        long end = System.currentTimeMillis();

        System.out.println("Expected: " + (threads * iterations));
        System.out.println("Actual  : " + counter.getCount());
        System.out.println("Time    : " + (end - start) + " ms");
    }
}
