package com.solvd.laba.lab4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    public static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class.getName());
    private static ConnectionPool instance;
    private final LinkedBlockingQueue<Connection> connections;

    private ConnectionPool() {
        connections = new LinkedBlockingQueue<>();
    }

    /**
     * Returns the singleton instance of the ConnectionPool class.
     * If the instance doesn't exist, it creates a new one.
     *
     * @return The ConnectionPool instance
     */
    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    /**
     * Retrieves a connection from the pool.
     * If no connection is available, it waits until a connection becomes available.
     *
     * @return A connection from the pool
     */
    public Connection getConnection() {
        Connection connection = null;
        try {
            // Retrieves a connection, waits if none are available
            connection = connections.take();
            LOGGER.info("Worker connected: " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Releases a connection back to the pool.
     *
     * @param connection The connection to be released
     */
    public void releaseConnection(Connection connection) {
        connections.offer(connection);
        LOGGER.info("Worker released: " + Thread.currentThread().getName());
    }

    /**
     * Initializes the connection pool with a specified size.
     * Creates new connections and adds them to the pool.
     *
     * @param size The size of the connection pool
     */
    public void initializePool(int size) {
        for (int i = 0; i < size; i++) {
            Connection connection = new Connection();
            connections.offer(connection);
        }
    }
}