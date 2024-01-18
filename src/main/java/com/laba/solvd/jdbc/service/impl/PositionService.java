package com.laba.solvd.jdbc.service.impl;

import com.laba.solvd.jdbc.dao.implDAO.PositionDAO;
import com.laba.solvd.jdbc.dao.implMapper.PositionMapper;
import com.laba.solvd.jdbc.dao.interfaces.IPositionDAO;
import com.laba.solvd.jdbc.model.Position;
import com.laba.solvd.jdbc.service.interfaces.IPositionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.laba.solvd.jdbc.Main.connectionFactory;

public class PositionService implements IPositionService {
    private static final Logger LOGGER = LogManager.getLogger(PositionService.class.getName());
    private final IPositionDAO positionDAO;

    public PositionService() {
        if (!connectionFactory.isMyBatis()) {
            this.positionDAO = new PositionDAO();
            LOGGER.info("Using JDBC Position repository");
        } else {
            this.positionDAO = new PositionMapper();
            LOGGER.info("Using MyBatisSQLFactory Position mapper");
        }
    }

    @Override
    public Position create(Position position) {
        positionDAO.saveEntity(position);
        return position;
    }

    @Override
    public List<Position> getAll() {
        return positionDAO.getAll();
    }
}
