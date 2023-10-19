package com.example.repository;

import com.example.dto.Employee;
import lombok.AllArgsConstructor;
import org.mariadb.jdbc.MariaDbPoolDataSource;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
@AllArgsConstructor
public class EmployeeRepository {
    private final MariaDbPoolDataSource source;
    private final List<Employee> employees;

    protected List<Employee> employeeMapper(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        System.out.println(statement);
        employees.clear();
        while (resultSet.next()) {
            employees.add(
                    Employee.builder()
                            .id(resultSet.getInt("employee_id"))
                            .firstName(resultSet.getString("first_name"))
                            .lastName(resultSet.getString("last_name"))
                            .email(resultSet.getString("email"))
                            .phone(resultSet.getString("phone_number"))
                            .hireDate(resultSet.getDate("hire_date"))
                            .salary(resultSet.getBigDecimal("salary"))
                            .department(resultSet.getString("department_name"))
                            .jobTitle(resultSet.getString("job_title"))
                            .region(resultSet.getString("region_name"))
                            .country(resultSet.getString("country_name"))
                            .state(resultSet.getString("state_province"))
                            .city(resultSet.getString("city"))
                            .commissionPercent(resultSet.getBigDecimal("commission_pct"))
                            .build()
            );
        }
        return new CopyOnWriteArrayList<>(employees);
    }

    public List<Employee> getAllEmployees() {
        try (Connection connection = source.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees");
            employeeMapper(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    public List<Employee> getEmployeesBySomeString(String columnName, String condition) {
        try (Connection connection = source.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees WHERE " + columnName + "=" + "?");
            statement.setString(1, condition);
            employeeMapper(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    public List<Employee> getEmployeesByPartOfName(String columnName, String condition) {
        try (Connection connection = source.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM employees WHERE " + columnName + " LIKE " + "%" + "?" + "%");
            statement.setString(1, condition);
            employeeMapper(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    public List<Employee> getEmployeesBySomeInt(String columnName, Integer someInt) {
        try (Connection connection = source.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees WHERE " + columnName + "=" + "?");
            statement.setInt(1, someInt);
            employeeMapper(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    public List<Employee> getEmployeesBySalary(String columnName, BigDecimal max, BigDecimal min) {
        try (Connection connection = source.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM employees WHERE " + columnName + "<" + "? " + "AND " + columnName + ">" + "?");
            statement.setBigDecimal(1, max);
            statement.setBigDecimal(2, min);
            employeeMapper(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }
}
