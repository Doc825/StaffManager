package com.example.repository;

import com.example.dto.Employee;
import lombok.AllArgsConstructor;
import org.mariadb.jdbc.MariaDbPoolDataSource;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@AllArgsConstructor
public class EmployeeRepository {
    private MariaDbPoolDataSource source;
    private List<Employee> employees;

    public List<Employee> getFromDataBase() {
        try (Connection connection = source.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employees.add(
                        Employee.builder()
                                .id(resultSet.getInt("employee_id"))
                                .firstName(resultSet.getString("first_name"))
                                .lastName(resultSet.getString("last_name"))
                                .email(resultSet.getString("email"))
                                .phoneNum(resultSet.getString("phone_number")).build()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Employee emp : employees) {
            System.out.println(emp);
        }
        return employees;
    }
}
