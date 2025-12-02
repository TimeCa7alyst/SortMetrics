package model.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPool {

    private static ExecutorService executor;

    private ThreadPool() {
    }

    public static synchronized ExecutorService getExecutor() {
        if (executor == null || executor.isShutdown()) {
            int processorsNum = Runtime.getRuntime().availableProcessors();
            executor = Executors.newFixedThreadPool(processorsNum);
        }
        return executor;
    }

    public static void monitor() {
        if (executor instanceof ThreadPoolExecutor) {
            ThreadPoolExecutor pool = (ThreadPoolExecutor) executor;

            System.out.println("\n--- Thread Pool Monitoring ---");
            System.out.println("Core Pool Size: " + pool.getCorePoolSize());
            System.out.println("Active Threads: " + pool.getActiveCount());
            System.out.println("Completed Tasks: " + pool.getCompletedTaskCount());
            System.out.println("Total Tasks Submitted: " + pool.getTaskCount());
            System.out.println("Queue Size: " + pool.getQueue().size());
            System.out.println("Is Shutdown: " + pool.isShutdown());
            System.out.println("Is Terminated: " + pool.isTerminated());
            System.out.println("--------------------------\n");
        }
    }

    public static void shutdown() {
        if (executor != null && !executor.isShutdown()) {
            System.out.println("Shutting down");
            executor.shutdown();
        }
    }
}
