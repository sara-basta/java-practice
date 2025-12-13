package com.sara.basta.threading;

public class VolatileDemo {

    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker();
        Thread t = new Thread(worker, "worker-1");

        t.start();
//        t.interrupt();
        Thread.sleep(2000);

        System.out.println("Main - requesting stop");
        worker.stop();

        t.join();
        System.out.println("Main - finished");
    }
}
