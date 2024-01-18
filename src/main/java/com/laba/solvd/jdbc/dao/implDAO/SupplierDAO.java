package com.laba.solvd.jdbc.dao.implDAO;

import com.laba.solvd.jdbc.dao.interfaces.ISupplierDAO;
import com.laba.solvd.jdbc.model.Supplier;
import com.laba.solvd.jdbc.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO implements ISupplierDAO {

    private static final Logger LOGGER = LogManager.getLogger(SupplierDAO.class);
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void saveEntity(Supplier supplier) {
        Connection connection = connectionPool.getConnection();
        String query = "INSERT INTO suppliers (company_name) VALUES (?);";

        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, supplier.getCompanyName());
            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    supplier.setSupplierId(generatedId);
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
    public Supplier getEntityByID(int id) {
        Connection connection = connectionPool.getConnection();
        String query = "SELECT * FROM suppliers WHERE supplier_id = ?;";
        Supplier supplier = new Supplier();

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    supplier.setSupplierId(rs.getInt("supplier_id"));
                    supplier.setCompanyName(rs.getString("company_name"));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return supplier;
    }

    @Override
    public void updateEntity(Supplier supplier) {
        Connection connection = connectionPool.getConnection();
        String query = "UPDATE suppliers SET company_name = ? WHERE supplier_id = ?;";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, supplier.getCompanyName());
            ps.setInt(2, supplier.getSupplierId());
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
        String query = "DELETE FROM suppliers WHERE supplier_id = ?;";

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
    public List<Supplier> getAll() {
        Connection connection = connectionPool.getConnection();
        String query = "SELECT * FROM suppliers;";
        List<Supplier> suppliers = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Supplier supplier = new Supplier();
                supplier.setSupplierId(rs.getInt("supplier_id"));
                supplier.setCompanyName(rs.getString("company_name"));
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return suppliers;
    }

    @Override
    public List<Supplier> getSupplierByName(String name) {
        Connection connection = connectionPool.getConnection();
        String query = "SELECT * FROM suppliers WHERE company_name = ?;";
        List<Supplier> suppliers = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Supplier supplier = new Supplier();
                    supplier.setSupplierId(rs.getInt("supplier_id"));
                    supplier.setCompanyName(rs.getString("company_name"));
                    suppliers.add(supplier);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return suppliers;
    }
}
