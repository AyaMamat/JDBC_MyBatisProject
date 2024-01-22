package com.laba.solvd.pharmacy.jdbc.implDAO;

import com.laba.solvd.pharmacy.builder.DoctorBuilder;
import com.laba.solvd.pharmacy.interfaces.IDoctorDAO;
import com.laba.solvd.pharmacy.model.Address;
import com.laba.solvd.pharmacy.model.Doctor;
import com.laba.solvd.pharmacy.model.DoctorSpecialty;
import com.laba.solvd.pharmacy.model.Person;
import com.laba.solvd.pharmacy.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO implements IDoctorDAO<Doctor> {

    private static final Logger LOGGER = LogManager.getLogger(DoctorDAO.class);
    private final PersonDAO personDAO = new PersonDAO();
    private final AddressDAO addressDAO = new AddressDAO();
    private final DoctorSpecialtyDAO doctorSpecialtyDAO = new DoctorSpecialtyDAO();
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void saveEntity(Doctor doctor) {
        Connection connection = connectionPool.getConnection();
        String query = "INSERT INTO doctors (person_id) VALUES (?);";

        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, doctor.getPerson().getPersonId());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    doctor.setDoctorId(rs.getInt(1));
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
    public Doctor getEntityByID(int id) {
        Connection connection = connectionPool.getConnection();
        String query = "SELECT * FROM doctorsA WHERE doctor_id =(?);";
        Doctor doctor = new Doctor();

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Person person = personDAO.getEntityByID(rs.getInt("person_id"));
                    List<DoctorSpecialty> specialties = doctorSpecialtyDAO.getDoctorSpecialtyByDoctorId(rs.getInt("doctor_id"));
                    doctor=new DoctorBuilder()
                            .withDoctorId(rs.getInt("doctor_id"))
                            .withPerson(person)
                            .withSpecialties(specialties)
                            .build();

                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return doctor;
    }


    @Override
    public void updateEntity(Doctor doctor) {
        Connection connection = connectionPool.getConnection();
        String query = "UPDATE doctors SET person_id = ? WHERE doctor_id = ?;";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, doctor.getPerson().getPersonId());
            ps.setInt(2, doctor.getDoctorId());
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
        String query = "DELETE FROM doctors WHERE doctor_id = ?;";

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
    public List<Doctor> getAll() {
        Connection connection = connectionPool.getConnection();
        List<Doctor> doctorList = new ArrayList<>();

        String query = "SELECT * FROM doctors;";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Person person = personDAO.getEntityByID(rs.getInt("person_id"));
                    List<DoctorSpecialty> specialties = doctorSpecialtyDAO.getDoctorSpecialtyByDoctorId(rs.getInt("doctor_id"));
                    Doctor doctor=new DoctorBuilder()
                            .withDoctorId(rs.getInt("doctor_id"))
                            .withPerson(person)
                            .withSpecialties(specialties)
                            .build();
                    doctorList.add(doctor);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return doctorList;
    }


    @Override
    public Person getByPersonLastName(String lastName) {
        String query = "SELECT * FROM people WHERE last_name = (?)";
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1,lastName);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Address address = addressDAO.getEntityByID(rs.getInt("address_id"));
                    return new Person(rs.getInt("person_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getDate("birth_date").toLocalDate(),
                            rs.getString("email"),
                            rs.getString("phone"),
                            address
                    );
                }
            }
        } catch (SQLException e) {
            LOGGER.info(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return null;
    }
    }
