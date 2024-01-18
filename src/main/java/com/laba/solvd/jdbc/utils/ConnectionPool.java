package com.laba.solvd.jdbc.utils;

import com.laba.solvd.jdbc.factory.IConnectionMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.IntStream;

import static com.laba.solvd.jdbc.Main.connectionFactory;

public class ConnectionPool  implements IConnectionMethod {
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class.getName());

    private static ConnectionPool INSTANCE;
    private ArrayList<Connection> connectionPool;

    private String driver;
    private String url;
    private String username;
    private String password;
    private int poolSize;

    public ConnectionPool() {
        initialize();
    }

    @Override
    public void initialize() {
        try {
            driver = Config.DRIVER.getValue();
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            logger.error("Unable to find driver", e);
        }

        url = Config.URL.getValue();
        username = Config.USERNAME.getValue();
        password = Config.PASSWORD.getValue();
        poolSize = Integer.parseInt(Config.POOL_SIZE.getValue());

        connectionPool = new ArrayList<>(poolSize);
        IntStream.range(0, poolSize).boxed().forEach((i -> connectionPool.add(createConnection())));
    }

    public static synchronized ConnectionPool getInstance() {
        if (INSTANCE == null && !connectionFactory.isMyBatis())
            INSTANCE = (ConnectionPool) connectionFactory.getMethod();
        return INSTANCE;
    }

    public Connection createConnection() {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create connection");
        }
    }

    public synchronized Connection getConnection() {
        if (connectionPool.isEmpty()) {
            try {
                while (connectionPool.isEmpty())
                    connectionPool.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException("Connection unavailable");
            }
        }

        return connectionPool.remove(connectionPool.size() - 1);
    }

    public synchronized void releaseConnection(Connection connection) {
        connectionPool.add(connection);
    }
}
