package com.laba.solvd.pharmacy.factory;

public class ConnectionDAOFactory {

    public IBaseDAOFactory getDAOFactory(DBConnectionType DBConnectionType) {
        if (DBConnectionType == DBConnectionType.JDBC) {
            return new JDBCDAOFactory();
        } else if (DBConnectionType == DBConnectionType.MYBATIS) {
            return new MyBatisDAOFactory();
        }
        return null;
    }
}
