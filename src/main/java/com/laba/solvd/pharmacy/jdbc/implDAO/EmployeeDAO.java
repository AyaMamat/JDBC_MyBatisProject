package com.laba.solvd.pharmacy.jdbc.implDAO;

import com.laba.solvd.pharmacy.interfaces.IEmployeeDAO;
import com.laba.solvd.pharmacy.model.Employee;
import com.laba.solvd.pharmacy.model.Person;
import com.laba.solvd.pharmacy.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO implements IEmployeeDAO<Employee> {

    private static final Logger LOGGER = LogManager.getLogger(EmployeeDAO.class);
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void saveEntity(Employee employee) {
        Connection connection = connectionPool.getConnection();
        String query = "INSERT INTO employees (employee_id, person_id, position_id) VALUES (?, ?, ?);";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, employee.getEmployeeId());
            ps.setInt(2, employee.getPerson().getPersonId());
            ps.setInt(3, employee.getPosition().getPositionId());
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
    public Employee getEntityByID(int id) {
        Connection connection = connectionPool.getConnection();
        String query = "SELECT * FROM employees WHERE employee_id =(?);";
        Employee employee = new Employee();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.execute();
            try (ResultSet rs = ps.getResultSet()) {
                while (rs.next()) {
                    employee.setEmployeeId(rs.getInt("employee_id"));
                    PersonDAO personDAO = new PersonDAO();
                    PositionDAO positionDAO = new PositionDAO();
                    employee.setPerson(personDAO.getEntityByID(rs.getInt("person_id")));
                    employee.setPosition(positionDAO.getEntityByID(rs.getInt("position_id")));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return employee;
    }

    @Override
    public void updateEntity(Employee employee) {
        Connection connection = connectionPool.getConnection();
        String query = "UPDATE employees SET person_id = ?, position_id = ? WHERE employee_id = ?;";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, employee.getPerson().getPersonId());
            ps.setInt(2, employee.getPosition().getPositionId());
            ps.setInt(3, employee.getEmployeeId());
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
        String query = "DELETE FROM employees WHERE employee_id = ?;";

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
    public List<Employee> getAll() {
        Connection connection = connectionPool.getConnection();
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employees;";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.execute();
            try (ResultSet rs = ps.getResultSet()) {
                while (rs.next()) {
                    Employee employee = new Employee();
                    employee.setEmployeeId(rs.getInt("employee_id"));
                    PersonDAO personDAO = new PersonDAO();
                    PositionDAO positionDAO = new PositionDAO();
                    employee.setPerson(personDAO.getEntityByID(rs.getInt("person_id")));
                    employee.setPosition(positionDAO.getEntityByID(rs.getInt("position_id")));
                    employees.add(employee);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return employees;
    }


    @Override
    public List<Employee> getEmployeeByLastName(String lastName) {
        Connection connection = connectionPool.getConnection();
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employees INNER JOIN people ON employees.person_id = people.person_id WHERE people.last_name = ?;";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, lastName);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Employee employee = new Employee();
                    employee.setEmployeeId(rs.getInt("employee_id"));
                    Person person = new Person();
                    person.setPersonId(rs.getInt("person_id"));
                    person.setFirstName(rs.getString("first_name"));
                    person.setLastName(rs.getString("last_name"));
                    person.setBirthDate(rs.getDate("birth_date").toLocalDate());
                    person.setEmail(rs.getString("email"));
                    person.setPhone(rs.getString("phone"));
                    AddressDAO addressDAO = new AddressDAO();
                    person.setAddress(addressDAO.getEntityByID(rs.getInt("address_id")));
                    employee.setPerson(person);
                    PositionDAO positionDAO = new PositionDAO();
                    employee.setPosition(positionDAO.getEntityByID(rs.getInt("position_id")));
                    employees.add(employee);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return employees;
    }
}
