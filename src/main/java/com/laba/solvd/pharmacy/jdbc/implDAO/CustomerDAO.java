package com.laba.solvd.pharmacy.jdbc.implDAO;

import com.laba.solvd.pharmacy.interfaces.ICustomerDAO;
import com.laba.solvd.pharmacy.model.Address;
import com.laba.solvd.pharmacy.model.Customer;
import com.laba.solvd.pharmacy.model.Person;
import com.laba.solvd.pharmacy.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements ICustomerDAO<Customer> {

    private static final Logger LOGGER = LogManager.getLogger(CustomerDAO.class);
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    PersonDAO personDAO = new PersonDAO();

    @Override
    public void saveEntity(Customer customer) {
        Connection connection = connectionPool.getConnection();
        String query = "INSERT INTO customers (person_id) VALUES (?);";

        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, customer.getPerson().getPersonId());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next())
                customer.setCustomerId(rs.getInt(1));
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public Customer getEntityByID(int id) {
        Connection connection = connectionPool.getConnection();
        String query = "SELECT * FROM customers WHERE customer_id =(?);";
        Customer customer = new Customer();

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer();
                    Person person = new Person();
                    person.setPersonId(rs.getInt("person_id"));
                    person.setFirstName(rs.getString("first_name"));
                    person.setLastName(rs.getString("last_name"));
                    person.setBirthDate(rs.getDate("birth_date").toLocalDate());
                    person.setEmail(rs.getString("email"));
                    person.setPhone(rs.getString("phone"));
                    Address address = new Address();
                    address.setAddressId(rs.getInt("address_id"));
                    address.setStreet(rs.getString("street"));
                    address.setCity(rs.getString("city"));
                    address.setState(rs.getString("state"));
                    address.setZipCode(rs.getInt("zip_code"));
                    address.setCountry(rs.getString("country"));
                    person.setAddress(address);
                    customer.setCustomerId(rs.getInt("customer_id"));
                    customer.setPerson(person);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return customer;
    }

    @Override
    public void updateEntity(Customer customer) {
        Connection connection = connectionPool.getConnection();
        String query = "UPDATE customers SET person_id = ? WHERE customer_id = ?;";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, customer.getPerson().getPersonId());
            ps.setInt(2, customer.getCustomerId());
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
        String query = "DELETE FROM customers WHERE customer_id = (?);";
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
    public List<Customer> getAll() {
        Connection connection = connectionPool.getConnection();
        String query = "SELECT * FROM customers;";
        List<Customer> customers = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Customer customer = new Customer();
                    customer.setCustomerId(rs.getInt("customer_id"));
                    int personId = rs.getInt("person_id");
                    Person person = personDAO.getEntityByID(personId);
                    customer.setPerson(person);
                    customers.add(customer);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return customers;
    }

    @Override
    public List<Customer> getCustomersByLastName(String lastName) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        String query = "SELECT * FROM customers INNER JOIN people ON customers.person_id = people.person_id WHERE people.last_name = (?);";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, lastName);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Customer customer = new Customer();
                    Person person = new Person();
                    Address address = new Address();
                    person.setPersonId(rs.getInt("person_id"));
                    person.setFirstName(rs.getString("first_name"));
                    person.setLastName(rs.getString("last_name"));
                    person.setBirthDate(rs.getDate("birth_date").toLocalDate());
                    person.setEmail(rs.getString("email"));
                    person.setPhone(rs.getString("phone"));
                    address.setAddressId(rs.getInt("address_id"));
                    address.setStreet(rs.getString("street"));
                    address.setCity(rs.getString("city"));
                    address.setState(rs.getString("state"));
                    address.setZipCode(rs.getInt("zip_code"));
                    address.setCountry(rs.getString("country"));
                    person.setAddress(address);
                    customer.setCustomerId(rs.getInt("customer_id"));
                    customer.setPerson(person);
                    customers.add(customer);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return customers;
    }
}
