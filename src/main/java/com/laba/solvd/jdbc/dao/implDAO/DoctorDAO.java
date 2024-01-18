package com.laba.solvd.jdbc.dao.implDAO;

import com.laba.solvd.jdbc.dao.interfaces.IDoctorDAO;
import com.laba.solvd.jdbc.model.Doctor;
import com.laba.solvd.jdbc.model.DoctorSpecialty;
import com.laba.solvd.jdbc.model.Person;
import com.laba.solvd.jdbc.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO implements IDoctorDAO {

    private static final Logger LOGGER = LogManager.getLogger(DoctorDAO.class);
    private final PersonDAO personDAO = new PersonDAO();
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
                    doctor = new Doctor();
                    doctor.setDoctorId(rs.getInt("doctor_id"));
                    int personId = rs.getInt("person_id");
                    Person person = personDAO.getEntityByID(personId);
                    doctor.setPerson(person);
                    List<DoctorSpecialty> specialties = new ArrayList<>();
                    specialties = getDoctorSpecialtyByDoctorId(doctor.getDoctorId());
                    doctor.setSpecialties(specialties);
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
        List<Doctor> doctors = new ArrayList<>();

        String query = "SELECT * FROM doctors;";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Doctor doctor = new Doctor();
                    doctor.setDoctorId(rs.getInt("doctor_id"));
                    Person person = new Person();
                    person.setPersonId(rs.getInt("person_id"));
                    doctor.setPerson(person);

                    List<DoctorSpecialty> specialties = new ArrayList<>();
                    specialties = getDoctorSpecialtyByDoctorId(doctor.getDoctorId());

                    doctor.setSpecialties(specialties);
                    doctors.add(doctor);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return doctors;
    }

    @Override
    public List<DoctorSpecialty> getDoctorSpecialtyByDoctorId(int id) {
        List<DoctorSpecialty> doctorSpecialtyList = new ArrayList<>();
        String query = "SELECT * FROM doctor_specialties WHERE doctor_id = (?)";
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DoctorSpecialty specialty = new DoctorSpecialty();
                    specialty.setDoctorSpecialtyId(rs.getInt("doctor_specialty_id"));
                    specialty.setTitle(rs.getString("title"));
                    doctorSpecialtyList.add(specialty);
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return doctorSpecialtyList;
    }
}
