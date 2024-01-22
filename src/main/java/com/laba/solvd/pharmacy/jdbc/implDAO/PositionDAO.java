package com.laba.solvd.pharmacy.jdbc.implDAO;

import com.laba.solvd.pharmacy.interfaces.IPositionDAO;
import com.laba.solvd.pharmacy.model.Position;
import com.laba.solvd.pharmacy.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PositionDAO implements IPositionDAO<Position> {

    private static final Logger LOGGER = LogManager.getLogger(AddressDAO.class);
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void saveEntity(Position position) {
        Connection connection = connectionPool.getConnection();
        String query = "INSERT INTO positions (position_title) VALUES (?);";

        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, position.getPositionTitle());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    position.setPositionId(generatedId);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public Position getEntityByID(int id) {
        Connection connection = connectionPool.getConnection();
        String query = "SELECT * FROM positions WHERE position_id = ?;";
        Position position = new Position();

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    position.setPositionId(rs.getInt("position_id"));
                    position.setPositionTitle(rs.getString("position_title"));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return position;

    }

    @Override
    public void updateEntity(Position position) {
        Connection connection = connectionPool.getConnection();
        String query = "UPDATE positions SET position_title = ? WHERE position_id = ?;";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, position.getPositionTitle());
            ps.setInt(2, position.getPositionId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void removeEntityById(int id) {
        Connection connection = connectionPool.getConnection();
        String query = "DELETE FROM positions WHERE position_id = ?;";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public List<Position> getAll() {
        Connection connection = connectionPool.getConnection();
        List<Position> positions = new ArrayList<>();
        String query = "SELECT * FROM positions;";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Position position = new Position();
                    position.setPositionId(rs.getInt("position_id"));
                    position.setPositionTitle(rs.getString("position_title"));
                    positions.add(position);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return positions;
    }

    @Override
    public List<Position> getPositionByTitle(String title) {
        Connection connection = connectionPool.getConnection();
        List<Position> positions = new ArrayList<>();
        String query = "SELECT * FROM positions WHERE position_title = (?);";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, title);

            try (ResultSet rs = ps.getResultSet()) {
                while (rs.next()) {
                    Position position = new Position();
                    position.setPositionId(rs.getInt("position_id"));
                    position.setPositionTitle(rs.getString("position_title"));
                    positions.add(position);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return positions;
    }
}
