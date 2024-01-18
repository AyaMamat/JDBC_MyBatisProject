package com.laba.solvd.jdbc.factory;

import com.laba.solvd.jdbc.utils.DBConfig;
import com.laba.solvd.jdbc.utils.ConnectionPool;
import com.laba.solvd.jdbc.utils.MyBatisSQLFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.laba.solvd.jdbc.utils.DBConfig.CONNECTION_METHOD;

public class ConnectionMethodFactory {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionMethodFactory.class.getName());
    private static String connectionMethod;

    public ConnectionMethodFactory() {
        connectionMethod = CONNECTION_METHOD;
    }

    public IConnectionMethod getMethod() {
        if (connectionMethod == null || connectionMethod.isBlank()) {
            LOGGER.error("Connection method was not provided");
            LOGGER.warn("Default connection method has been set to JDBC");
        }

        if (!connectionMethod.equals("jdbc") && !connectionMethod.equals("mybatis")) {
            LOGGER.error("Provided connection is not valid");
            LOGGER.warn("Default connection method has been set to JDBC");
        }

        if (isMyBatis()) {
            LOGGER.info("Connection method has been set to MyBatisSQLFactory");
            return new MyBatisSQLFactory();
        }

        LOGGER.info("Connection method has been set to JDBC Connection Pool");
        return (IConnectionMethod) new ConnectionPool();
    }

    public boolean isMyBatis() {
        if (connectionMethod == null || connectionMethod.isBlank())
            return false;
        return connectionMethod.equals("mybatis");
    }
}
