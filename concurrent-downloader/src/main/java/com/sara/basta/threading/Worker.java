package com.sara.basta.threading;

public class Worker implements Runnable {

    private volatile boolean running = true;

    @Override
    public void run() {
        log("Starting work");
        while (running) {
            log("Working...");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log("Interrupted, stopping");
                break;
            }
        }
        log("Stopped cleanly");
    }

    public void stop() {
        running = false;
    }

    public static synchronized void log(String msg) {
        System.out.println("[" + System.currentTimeMillis() + "] "
                + Thread.currentThread().getName() + " - " + msg);
    }
}
