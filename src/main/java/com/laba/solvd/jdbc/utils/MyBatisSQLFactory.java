package com.laba.solvd.jdbc.utils;

import com.laba.solvd.jdbc.factory.IConnectionMethod;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

import static com.laba.solvd.jdbc.Main.connectionFactory;

public class MyBatisSQLFactory implements IConnectionMethod {

    private static final Logger LOGGER = LogManager.getLogger(MyBatisSQLFactory.class.getName());

    private static final String path = "mybatis-config.xml";
    private static MyBatisSQLFactory INSTANCE;
    private static SqlSessionFactory sqlSessionFactory;

    public MyBatisSQLFactory() {
        initialize();
    }

    public static synchronized MyBatisSQLFactory getInstance() {
        if (INSTANCE == null && connectionFactory.isMyBatis())
            INSTANCE = (MyBatisSQLFactory) connectionFactory.getMethod();
        return INSTANCE;
    }

    @Override
    public void initialize() {
        try {
            InputStream inputStream = Resources.getResourceAsStream(path);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            LOGGER.error("Failed to find MyBatisSQLFactory configuration file");
        }
    }

    public SqlSession getSession() {
        return sqlSessionFactory.openSession();
    }
}
