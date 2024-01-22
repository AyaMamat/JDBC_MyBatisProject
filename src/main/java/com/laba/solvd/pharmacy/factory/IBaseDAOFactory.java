package com.laba.solvd.pharmacy.factory;

import com.laba.solvd.pharmacy.interfaces.IBaseDAO;

public interface IBaseDAOFactory {

    IBaseDAO getDAO(String tableName);
}
