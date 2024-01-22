package com.laba.solvd.pharmacy.service.impl;

import com.laba.solvd.pharmacy.mybatis.implDAO.PositionDAO;
import com.laba.solvd.pharmacy.interfaces.IPositionDAO;
import com.laba.solvd.pharmacy.factory.ConnectionDAOFactory;
import com.laba.solvd.pharmacy.factory.DBConnectionType;
import com.laba.solvd.pharmacy.model.Position;

import java.util.List;

public class PositionService implements IPositionDAO<Position> {

    private static final PositionDAO positionDAO = (PositionDAO) new ConnectionDAOFactory().getDAOFactory(DBConnectionType.MYBATIS).getDAO("positions");

    @Override
    public void saveEntity(Position position) {
        positionDAO.saveEntity(position);
    }

    @Override
    public Position getEntityByID(int id) {
        return positionDAO.getEntityByID(id);
    }

    @Override
    public void updateEntity(Position position) {
        positionDAO.updateEntity(position);
    }

    @Override
    public void removeEntityById(int id) {
        positionDAO.removeEntityById(id);
    }

    @Override
    public List<Position> getAll() {
        return positionDAO.getAll();
    }

    @Override
    public List<Position> getPositionByTitle(String title) {
        return positionDAO.getPositionByTitle(title);
    }
}
