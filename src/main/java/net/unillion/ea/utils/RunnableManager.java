package net.unillion.ea.utils;

public class RunnableManager {
    private Thread thread;
    private volatile boolean running;

    public synchronized void start(Runnable runnable) {
        if (running) {
            return;
        }

        running = true;
        thread = new Thread(() -> {
            try {
                runnable.run();
            } finally {
                stop();
            }
        });
        thread.start();
    }

    public synchronized void stop() {
        if (!running) {
            return;
        }

        running = false;
        if (thread != null) {
            thread.interrupt();
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        thread = null;
    }
    public synchronized boolean isRunning() {
        return running;
    }
    public boolean isRunningFlag() {
        return running;
    }
}
