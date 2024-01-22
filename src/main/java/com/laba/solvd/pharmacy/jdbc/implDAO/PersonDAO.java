package com.laba.solvd.pharmacy.jdbc.implDAO;

import com.laba.solvd.pharmacy.interfaces.IPersonDAO;
import com.laba.solvd.pharmacy.model.Address;
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
    private final AddressDAO addressDAO=new AddressDAO();

    @Override
    public void saveEntity(Person person) {
        Connection connection = connectionPool.getConnection();
        String query = "INSERT INTO people (person_id, first_name, last_name, birth_date, email, phone, address_id) VALUES (?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, person.getFirstName());
            ps.setString(2, person.getLastName());
            ps.setDate(3, Date.valueOf(person.getBirthDate()));
            ps.setString(4, person.getEmail());
            ps.setString(5, person.getPhone());
            ps.setInt(6, person.getAddress().getAddressId());
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
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public Person getEntityByID(int id) {
        Connection connection = connectionPool.getConnection();
        String query = "SELECT * FROM people WHERE person_id = ?;";
        Person person = new Person();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Address address = addressDAO.getEntityByID(rs.getInt("address_id"));
                    return new Person(rs.getInt("person_id"), rs.getString("first_name"),
                            rs.getString("last_name"), rs.getDate("birth_date").toLocalDate(),
                            rs.getString("email"), rs.getString("phone"), address);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return person;
    }

    @Override
    public void updateEntity(Person person) {
        Connection connection = connectionPool.getConnection();
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
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void removeEntityById(int id) {
        Connection connection = connectionPool.getConnection();
        String query = "DELETE FROM people WHERE person_id = ?;";

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
    public List<Person> getAll() {
        List<Person> people = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        String query = "SELECT * FROM people;";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Address address = addressDAO.getEntityByID(rs.getInt("address_id"));
                    people.add(new Person(
                            rs.getInt("person_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getDate("birth_date").toLocalDate(),
                            rs.getString("email"),
                            rs.getString("phone"),
                            address));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return people;
    }

    @Override
    public List<Person> getPersonsByBirthDate(LocalDate birthDate) {
        List<Person> persons = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        String query = "SELECT * FROM people WHERE birth_date = (?);";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setDate(1, Date.valueOf(birthDate));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Address address = addressDAO.getEntityByID(rs.getInt("address_id"));
                    persons.add(new Person(
                            rs.getInt("person_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getDate("birth_date").toLocalDate(),
                            rs.getString("email"),
                            rs.getString("phone"),
                            address
                    ));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return persons;
    }
}
