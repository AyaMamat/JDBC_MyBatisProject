package com.laba.solvd.jdbc.dao.implDAO;

import com.laba.solvd.jdbc.dao.interfaces.IDoctorSpecialtyDAO;
import com.laba.solvd.jdbc.model.DoctorSpecialty;
import com.laba.solvd.jdbc.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorSpecialtyDAO implements IDoctorSpecialtyDAO {

    private static final Logger LOGGER = LogManager.getLogger(DoctorSpecialtyDAO.class);
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void saveEntity(DoctorSpecialty doctorSpecialty) {
        Connection connection = connectionPool.getConnection();
        String query = "INSERT INTO doctor_specialties (title) VALUES (?);";

        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, doctorSpecialty.getTitle());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    doctorSpecialty.setDoctorSpecialtyId(rs.getInt(1));
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
    public DoctorSpecialty getEntityByID(int id) {
        Connection connection = connectionPool.getConnection();
        String query = "SELECT * FROM doctor_specialties WHERE doctor_specialty_id = (?);";
        DoctorSpecialty doctorSpecialty = null;

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    doctorSpecialty = new DoctorSpecialty();
                    doctorSpecialty.setDoctorSpecialtyId(rs.getInt("doctor_specialty_id"));
                    doctorSpecialty.setTitle(rs.getString("title"));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return doctorSpecialty;
    }

    @Override
    public void updateEntity(DoctorSpecialty doctorSpecialty) {
        Connection connection = connectionPool.getConnection();
        String query = "UPDATE doctor_specialties SET title = ? WHERE doctor_specialty_id = ?;";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, doctorSpecialty.getTitle());
            ps.setInt(2, doctorSpecialty.getDoctorSpecialtyId());
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
        String query = "DELETE FROM doctor_specialties WHERE doctor_specialty_id = (?);";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public List<DoctorSpecialty> getAll() {
        Connection connection = connectionPool.getConnection();
        String query = "SELECT * FROM doctor_specialties;";
        List<DoctorSpecialty> specialties = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.execute();
            try (ResultSet rs = ps.getResultSet()) {
                while (rs.next()) {
                    DoctorSpecialty doctorSpecialty = new DoctorSpecialty();
                    doctorSpecialty.setDoctorSpecialtyId(rs.getInt("doctor_specialty_id"));
                    doctorSpecialty.setTitle(rs.getString("title"));
                    specialties.add(doctorSpecialty);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return specialties;
    }

    @Override
    public DoctorSpecialty getDoctorSpecialtyByTitle(String title) {
        Connection connection = connectionPool.getConnection();
        String query = "SELECT * FROM doctor_specialties WHERE title = ?;";
        DoctorSpecialty doctorSpecialty = new DoctorSpecialty();

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, title);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    doctorSpecialty.setDoctorSpecialtyId(rs.getInt("doctor_specialty_id"));
                    doctorSpecialty.setTitle(rs.getString("title"));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return doctorSpecialty;
    }
}
