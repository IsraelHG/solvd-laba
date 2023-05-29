package com.solvd.block1.lab4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.*;

public class Main {
    public static final Logger LOGGER = LogManager.getLogger(Main.class.getName());
    public static void main(String[] args) {
        // Create an instance of the ConnectionPool
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.initializePool(5); // Initialize connection pool with size 5

        int totalThreads = 7;

        // Create an ExecutorService with a fixed thread pool of the given size
        ExecutorService executorService = Executors.newFixedThreadPool(totalThreads);

        // Create an array of CompletableFutures to hold the futures of all worker threads
        CompletableFuture<?>[] futures = new CompletableFuture<?>[totalThreads];

        for (int i = 0; i < totalThreads; i++) {
            // Create a CompletableFuture for each worker thread using supplyAsync
            CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
                // Retrieve a connection from the connection pool
                Connection connection = connectionPool.getConnection();
                try {
                    // Sleep for 2 seconds to simulate work...
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Release the connection back to the pool
                connectionPool.releaseConnection(connection);
                return null;
            }, executorService);

            // Store the CompletableFuture in the array
            futures[i] = future;
        }
        // Wait for all worker threads to complete using CompletableFuture.allOf()
        // Program stops here until all threads are done
        // .join() to prevent main thread from executing before worker threads finish
        CompletableFuture.allOf(futures).join();

        // Shut down the executor service
        executorService.shutdown();

        LOGGER.info("All threads have finished.");
    }
}