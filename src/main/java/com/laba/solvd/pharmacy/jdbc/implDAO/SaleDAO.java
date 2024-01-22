package com.laba.solvd.pharmacy.jdbc.implDAO;

import com.laba.solvd.pharmacy.interfaces.ISalesDAO;
import com.laba.solvd.pharmacy.model.Employee;
import com.laba.solvd.pharmacy.model.Prescription;
import com.laba.solvd.pharmacy.model.Sale;
import com.laba.solvd.pharmacy.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SaleDAO implements ISalesDAO<Sale> {

    private static final Logger LOGGER = LogManager.getLogger(SaleDAO.class);
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void saveEntity(Sale sale) {
        Connection connection = connectionPool.getConnection();
        String query = "INSERT INTO sales (total_price, sale_date, employee_id, prescription_id) VALUES (?, ?, ?, ?);";

        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDouble(1, sale.getTotalPrice());
            ps.setDate(2, Date.valueOf(sale.getSaleDate()));
            ps.setInt(3, sale.getEmployee().getEmployeeId());
            ps.setInt(4, sale.getPrescription().getPrescriptionId());
            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    sale.setSaleId(generatedId);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error saving sale entity", e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public Sale getEntityByID(int id) {
        Connection connection = connectionPool.getConnection();
        String query = "SELECT * FROM sales WHERE sale_id = ?;";
        Sale sale = new Sale();

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    sale.setSaleId(rs.getInt("sale_id"));
                    sale.setTotalPrice(rs.getDouble("total_price"));
                    sale.setSaleDate(rs.getDate("sale_date").toLocalDate());

                    EmployeeDAO employeeDAO = new EmployeeDAO();
                    Employee employee = employeeDAO.getEntityByID(rs.getInt("employee_id"));
                    sale.setEmployee(employee);

                    PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
                    Prescription prescription = prescriptionDAO.getEntityByID(rs.getInt("prescription_id"));
                    sale.setPrescription(prescription);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return sale;
    }

    @Override
    public void updateEntity(Sale sale) {
        Connection connection = connectionPool.getConnection();
        String query = "UPDATE sales SET total_price=?, sale_date=?, employee_id=?, prescription_id=? WHERE sale_id=?;";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setDouble(1, sale.getTotalPrice());
            ps.setDate(2, Date.valueOf(sale.getSaleDate()));
            ps.setInt(3, sale.getEmployee().getEmployeeId());
            ps.setInt(4, sale.getPrescription().getPrescriptionId());
            ps.setInt(5, sale.getSaleId());
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
        String query = "DELETE FROM sales WHERE sale_id = ?;";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error removing sale entity by ID", e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public List<Sale> getAll() {
        Connection connection = connectionPool.getConnection();
        String query = "SELECT * FROM sales;";
        List<Sale> sales = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Sale sale = new Sale();
                    sale.setSaleId(rs.getInt("sale_id"));
                    sale.setTotalPrice(rs.getDouble("total_price"));
                    sale.setSaleDate(rs.getDate("sale_date").toLocalDate());

                    EmployeeDAO employeeDAO = new EmployeeDAO();
                    Employee employee = employeeDAO.getEntityByID(rs.getInt("employee_id"));
                    sale.setEmployee(employee);

                    PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
                    Prescription prescription = prescriptionDAO.getEntityByID(rs.getInt("prescription_id"));
                    sale.setPrescription(prescription);
                    sales.add(sale);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return sales;
    }

    @Override
    public List<Sale> getSalesByDate(LocalDate date) {

        String query = "SELECT * FROM sales WHERE sale_date = ?;";
        List<Sale> sales = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setDate(1, Date.valueOf(date));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Sale sale = new Sale();
                    sale.setSaleId(rs.getInt("sale_id"));
                    sale.setTotalPrice(rs.getDouble("total_price"));
                    sale.setSaleDate(rs.getDate("sale_date").toLocalDate());

                    EmployeeDAO employeeDAO = new EmployeeDAO();
                    Employee employee = employeeDAO.getEntityByID(rs.getInt("employee_id"));
                    sale.setEmployee(employee);

                    PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
                    Prescription prescription = prescriptionDAO.getEntityByID(rs.getInt("prescription_id"));
                    sale.setPrescription(prescription);
                    sales.add(sale);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return sales;
    }
}
