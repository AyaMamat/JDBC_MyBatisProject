package com.laba.solvd.jdbc.dao.interfaces;

import com.laba.solvd.jdbc.model.Position;

import java.util.List;

public interface IPositionDAO extends IBaseDAO<Position>{

    List<Position> getPositionByTitle(String title);
}
