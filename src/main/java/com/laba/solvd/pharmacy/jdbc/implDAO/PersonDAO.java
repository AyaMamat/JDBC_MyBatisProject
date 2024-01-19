package com.laba.solvd.pharmacy.jdbc.implDAO;

import com.laba.solvd.pharmacy.interfaces.IPersonDAO;
import com.laba.solvd.pharmacy.model.Person;
import com.laba.solvd.pharmacy.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO implements IPersonDAO<Person> {

    private static final Logger LOGGER = LogManager.getLogger(PersonDAO.class);
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void saveEntity(Person person) {
        Connection connection = ConnectionPool.getConnection();
        String query = "INSERT INTO people (person_id, first_name, last_name, birth_date, email, phone, address_id) VALUES (?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(2, person.getFirstName());
            ps.setString(3, person.getLastName());
            ps.setDate(4, Date.valueOf(person.getBirthDate()));
            ps.setString(5, person.getEmail());
            ps.setString(6, person.getPhone());
            ps.setInt(7, person.getAddress().getAddressId());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    person.setPersonId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public Person getEntityByID(int id) {
        Connection connection = ConnectionPool.getConnection();
        String query = "SELECT * FROM people WHERE person_id = ?;";
        Person person = new Person();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    person.setPersonId(rs.getInt("person_id"));
                    person.setFirstName(rs.getString("first_name"));
                    person.setLastName(rs.getString("last_name"));
                    person.setBirthDate(rs.getDate("birth_date").toLocalDate());
                    person.setEmail(rs.getString("email"));
                    AddressDAO addressDAO = new AddressDAO();
                    person.setAddress(addressDAO.getEntityByID(rs.getInt("address_id")));
                    person.setPhone(rs.getString("phone"));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }
        return person;
    }

    @Override
    public void updateEntity(Person person) {
        Connection connection = ConnectionPool.getConnection();
        String query = "UPDATE people SET first_name = ?, last_name = ?, birth_date = ?, email = ?, phone = ?, address_id = ? WHERE person_id = ?;";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, person.getFirstName());
            ps.setString(2, person.getLastName());
            ps.setDate(3, Date.valueOf(person.getBirthDate()));
            ps.setString(4, person.getEmail());
            ps.setString(5, person.getPhone());
            ps.setInt(6, person.getAddress().getAddressId());
            ps.setInt(7, person.getPersonId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void removeEntityById(int id) {
        Connection connection = ConnectionPool.getConnection();
        String query = "DELETE FROM people WHERE person_id = ?;";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public List<Person> getAll() {
        List<Person> persons = new ArrayList<>();
        Connection connection = ConnectionPool.getConnection();
        String query = "SELECT * FROM people;";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Person person = new Person();
                    person.setPersonId(rs.getInt("person_id"));
                    person.setFirstName(rs.getString("first_name"));
                    person.setLastName(rs.getString("last_name"));
                    person.setBirthDate(rs.getDate("birth_date").toLocalDate());
                    person.setEmail(rs.getString("email"));

                    AddressDAO addressDAO = new AddressDAO();
                    person.setAddress(addressDAO.getEntityByID(rs.getInt("address_id")));

                    person.setPhone(rs.getString("phone"));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }
        return persons;
    }

    @Override
    public List<Person> getPersonsByBirthDate(LocalDate birthDate) {
        List<Person> persons = new ArrayList<>();
        Connection connection = ConnectionPool.getConnection();
        String query = "SELECT * FROM people WHERE birth_date = (?);";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setDate(1, Date.valueOf(birthDate));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Person person = new Person();
                    AddressDAO addressDAO = new AddressDAO();
                    person.setPersonId(rs.getInt("person_id"));
                    person.setFirstName(rs.getString("first_name"));
                    person.setLastName(rs.getString("last_name"));
                    person.setBirthDate(rs.getDate("birth_date").toLocalDate());
                    person.setEmail(rs.getString("email"));
                    person.setAddress(addressDAO.getEntityByID(rs.getInt("address_id")));
                    person.setPhone(rs.getString("phone"));
                    persons.add(person);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }
        return persons;
    }
}
