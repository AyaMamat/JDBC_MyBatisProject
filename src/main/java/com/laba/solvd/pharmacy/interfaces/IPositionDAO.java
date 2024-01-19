package com.laba.solvd.pharmacy.interfaces;

import java.util.List;

public interface IPositionDAO<Position> extends IBaseDAO<Position> {

    List<Position> getPositionByTitle(String title);
}
