package com.laba.solvd.jdbc.dao.implDAO;

import com.laba.solvd.jdbc.dao.interfaces.IMedicationDAO;
import com.laba.solvd.jdbc.model.Medication;
import com.laba.solvd.jdbc.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicationDAO implements IMedicationDAO {
    private ConnectionPool connectionPool=ConnectionPool.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(MedicationDAO.class);

    @Override
    public void saveEntity(Medication medication) {
        Connection connection = connectionPool.getConnection();
        String query = "INSERT INTO medications (name,description,price,supplier_id) VALUES (?,?,?,?);";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, medication.getName());
            ps.setString(2, medication.getDescription());
            ps.setDouble(3, medication.getPrice());
            ps.setInt(4, medication.getSupplier().getSupplierId());
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
    public Medication getEntityByID(int id) {
        Connection connection = connectionPool.getConnection();
        String query = "SELECT * FROM medications WHERE medication_id = ?;";
        Medication medication = new Medication();

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.execute();
            try (ResultSet rs = ps.getResultSet()) {
                if (rs.next()) {
                    medication.setMedicationId(rs.getInt("medication_id"));
                    medication.setName(rs.getString("name"));
                    medication.setDescription(rs.getString("description"));
                    medication.setPrice(rs.getDouble("price"));
                    SupplierDAO supplierDAO=new SupplierDAO();
                    medication.setSupplier(supplierDAO.getEntityByID(rs.getInt("supplier_id")));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return medication;
    }

    @Override
    public void updateEntity(Medication medication) {
        Connection connection = connectionPool.getConnection();
        String query = "UPDATE medications SET name = ? WHERE medication_id = ?;";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, medication.getName());
            ps.setInt(2, medication.getMedicationId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error( e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }

    }

    @Override
    public void removeEntityById(int id) {
        Connection connection = connectionPool.getConnection();
        String query = "DELETE FROM medications WHERE medication_id = ?;";

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
    public List<Medication> getAll() {
        Connection connection = connectionPool.getConnection();
        List<Medication> medications = new ArrayList<>();
        String query = "SELECT * FROM medications;";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.execute();
            try (ResultSet rs = ps.getResultSet()) {
                while (rs.next()) {
                    Medication medication = new Medication();
                    medication.setMedicationId(rs.getInt("medication_id"));
                    medication.setName(rs.getString("name"));
                    medication.setDescription(rs.getString("description"));
                    medication.setPrice(rs.getDouble("price"));
                    SupplierDAO supplierDAO=new SupplierDAO();
                    medication.setSupplier(supplierDAO.getEntityByID(rs.getInt("supplier_id")));
                    medications.add(medication);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return medications;
    }

    @Override
    public List<Medication> getMedicationByName(String name) {
        Connection connection = connectionPool.getConnection();
        List<Medication> medications = new ArrayList<>();
        String query = "SELECT * FROM medications WHERE name LIKE ?;";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, "%" + name + "%");
            ps.execute();
            try (ResultSet rs = ps.getResultSet()) {
                while (rs.next()) {
                    Medication medication = new Medication();
                    medication.setMedicationId(rs.getInt("medication_id"));
                    medication.setName(rs.getString("name"));
                    medication.setDescription(rs.getString("description"));
                    medication.setPrice(rs.getDouble("price"));
                    SupplierDAO supplierDAO=new SupplierDAO();
                    medication.setSupplier(supplierDAO.getEntityByID(rs.getInt("supplier_id")));
                    medications.add(medication);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return medications;

    }
}