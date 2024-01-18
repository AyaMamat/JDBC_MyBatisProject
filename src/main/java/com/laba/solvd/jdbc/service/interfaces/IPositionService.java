package com.laba.solvd.jdbc.service.interfaces;

import com.laba.solvd.jdbc.model.Position;

import java.util.List;

public interface IPositionService {
    Position create(Position position);
    List<Position> getAll();
}
