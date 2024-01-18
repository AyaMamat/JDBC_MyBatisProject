package com.laba.solvd.jdbc.dao.implDAO;

import com.laba.solvd.jdbc.dao.interfaces.IPrescriptionDAO;
import com.laba.solvd.jdbc.model.Customer;
import com.laba.solvd.jdbc.model.Doctor;
import com.laba.solvd.jdbc.model.Prescription;
import com.laba.solvd.jdbc.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDAO implements IPrescriptionDAO {

    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(PrescriptionDAO.class);

    @Override
    public void saveEntity(Prescription prescription) {
        Connection connection = connectionPool.getConnection();
        String query = "INSERT INTO prescriptions (prescription_date, quantity, dosage, doctor_id, customer_id) VALUES (?, ?, ?, ?, ?);";

        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, new java.sql.Date(prescription.getPrescriptionDate().getTime()));
            ps.setInt(2, prescription.getQuantity());
            ps.setString(3, prescription.getDosage());
            ps.setInt(4, prescription.getDoctor().getDoctorId());
            ps.setInt(5, prescription.getCustomer().getCustomerId());
            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    prescription.setPrescriptionId(generatedId);
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
    public Prescription getEntityByID(int id) {
        Connection connection = connectionPool.getConnection();
        String query = "SELECT * FROM prescriptions WHERE prescription_id = ?;";
        Prescription prescription = new Prescription();

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    prescription.setPrescriptionId(rs.getInt("prescription_id"));
                    prescription.setPrescriptionDate(rs.getDate("prescription_date"));
                    prescription.setQuantity(rs.getInt("quantity"));
                    prescription.setDosage(rs.getString("dosage"));

                    DoctorDAO doctorDAO = new DoctorDAO();
                    Doctor doctor = doctorDAO.getEntityByID(rs.getInt("doctor_id"));
                    prescription.setDoctor(doctor);

                    CustomerDAO customerDAO = new CustomerDAO();
                    Customer customer = customerDAO.getEntityByID(rs.getInt("customer_id"));
                    prescription.setCustomer(customer);

                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return prescription;
    }

    @Override
    public void updateEntity(Prescription prescription) {
        Connection connection = connectionPool.getConnection();
        String query = "UPDATE prescriptions SET prescription_date=?, quantity=?, dosage=?, doctor_id=?, customer_id=? WHERE prescription_id=?;";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setDate(1, new java.sql.Date(prescription.getPrescriptionDate().getTime()));
            ps.setInt(2, prescription.getQuantity());
            ps.setString(3, prescription.getDosage());
            ps.setInt(4, prescription.getDoctor().getDoctorId());
            ps.setInt(5, prescription.getCustomer().getCustomerId());
            ps.setInt(6, prescription.getPrescriptionId());
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
        String query = "DELETE FROM prescriptions WHERE prescription_id = ?;";

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
    public List<Prescription> getAll() {
        Connection connection = connectionPool.getConnection();
        String query = "SELECT * FROM prescriptions;";
        List<Prescription> prescriptions = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Prescription prescription = new Prescription();
                    prescription.setPrescriptionId(rs.getInt("prescription_id"));
                    prescription.setPrescriptionDate(rs.getDate("prescription_date"));
                    prescription.setQuantity(rs.getInt("quantity"));
                    prescription.setDosage(rs.getString("dosage"));

                    DoctorDAO doctorDAO = new DoctorDAO();
                    Doctor doctor = doctorDAO.getEntityByID(rs.getInt("doctor_id"));
                    prescription.setDoctor(doctor);

                    CustomerDAO customerDAO = new CustomerDAO();
                    Customer customer = customerDAO.getEntityByID(rs.getInt("customer_id"));
                    prescription.setCustomer(customer);

                    prescriptions.add(prescription);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return prescriptions;
    }


    @Override
    public List<Prescription> getPrescriptionByCustomerId(Customer customerId) {
        Connection connection = connectionPool.getConnection();
        String query = "SELECT * FROM prescriptions WHERE customer_id = ?;";
        List<Prescription> prescriptions = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, customerId.getCustomerId());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Prescription prescription = new Prescription();
                    prescription.setPrescriptionId(rs.getInt("prescription_id"));
                    prescription.setPrescriptionDate(rs.getDate("prescription_date"));
                    prescription.setQuantity(rs.getInt("quantity"));
                    prescription.setDosage(rs.getString("dosage"));

                    DoctorDAO doctorDAO = new DoctorDAO();
                    Doctor doctor = doctorDAO.getEntityByID(rs.getInt("doctor_id"));
                    prescription.setDoctor(doctor);

                    CustomerDAO customerDAO = new CustomerDAO();
                    Customer customer = customerDAO.getEntityByID(rs.getInt("customer_id"));
                    prescription.setCustomer(customer);

                    prescriptions.add(prescription);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return prescriptions;
    }
}
