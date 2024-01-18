package com.laba.solvd.jdbc.service.impl;

import com.laba.solvd.jdbc.dao.implDAO.EmployeeDAO;
import com.laba.solvd.jdbc.dao.implMapper.EmployeeMapper;
import com.laba.solvd.jdbc.dao.interfaces.IEmployeeDAO;
import com.laba.solvd.jdbc.model.Employee;
import com.laba.solvd.jdbc.model.Person;
import com.laba.solvd.jdbc.model.Position;
import com.laba.solvd.jdbc.service.interfaces.IEmployeeService;
import com.laba.solvd.jdbc.service.interfaces.IPersonService;
import com.laba.solvd.jdbc.service.interfaces.IPositionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.laba.solvd.jdbc.Main.connectionFactory;

public class EmployeeService implements IEmployeeService {
    private static final Logger logger = LogManager.getLogger(EmployeeService.class.getName());
    private final IEmployeeDAO employeeDAO;
    private final IPersonService personService;
    private final IPositionService positionService;

    public EmployeeService(IPersonService personService, IPositionService positionService) {
        this.personService = personService;
        this.positionService = positionService;
        if (!connectionFactory.isMyBatis()) {
            this.employeeDAO = new EmployeeDAO();
            logger.info("Using JDBC Employee repository");
        } else {
            this.employeeDAO = new EmployeeMapper();
            logger.info("Using MyBatis Employee mapper");
        }
    }

    @Override
    public Employee create(Employee employee) {

        if (employee.getPerson() != null) {
            Person person = personService.create(employee.getPerson());
            employee.setPerson(person);
        }

        if (employee.getPosition() != null) {
            Position position = positionService.create(employee.getPosition());
            employee.setPosition(position);
        }

        employeeDAO.saveEntity(employee);
        return employee;
    }

    @Override
    public List<Employee> getAll() {
        return employeeDAO.getAll();
    }
}
