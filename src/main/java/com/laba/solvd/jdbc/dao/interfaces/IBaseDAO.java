package com.laba.solvd.jdbc.dao.interfaces;

import java.util.List;

public interface IBaseDAO<Entity> {

    void saveEntity(Entity entity);

    Entity getEntityByID(int id);

    void updateEntity(Entity entity);

    void removeEntityById(int id);

    List<Entity> getAll();
}
