package com.example.repository;

import com.example.dto.EmployeeJobView;
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
public class EmployeeJobViewRepository {
    private MariaDbPoolDataSource source;
    private List<EmployeeJobView> viewList;

    protected List<EmployeeJobView> viewMapper(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        viewList.clear();
        while (resultSet.next()) {
            viewList.add(
                    EmployeeJobView.builder()
                            .firstName(resultSet.getString("first_name"))
                            .lastName(resultSet.getString("last_name"))
                            .departmentName(resultSet.getString("department_name"))
                            .jobTitle(resultSet.getString("job_title"))
                            .regionName(resultSet.getString("region_name"))
                            .countryName(resultSet.getString("country_name"))
                            .state(resultSet.getString("state_province"))
                            .city(resultSet.getString("city"))
                            .build()
            );
        }
        return viewList;
    }

    public List<EmployeeJobView> getAllEmployeesDetails() {
        try (Connection connection = source.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT first_name, last_name, department_name, job_title, region_name, country_name, state_province, city " +
                            "FROM employees e JOIN departments d ON e.department_id = d.department_id " +
                            "JOIN jobs j ON e.job_id = j.job_id JOIN locations l ON d.location_id = l.location_id " +
                            "JOIN countries c ON l.country_id = c.country_id JOIN regions r ON c.region_id = r.region_id " +
                            "ORDER BY region_name, country_name, state_province");
            viewMapper(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return viewList;
    }

    public List<EmployeeJobView> getEmployeeDetailsByPartOfName(String columnName, String partOfName) {
        try (Connection connection = source.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT first_name, last_name, department_name, job_title, region_name, country_name, state_province, city " +
                            "FROM employees e JOIN departments d ON e.department_id = d.department_id " +
                            "JOIN jobs j ON e.job_id = j.job_id JOIN locations l ON d.location_id = l.location_id " +
                            "JOIN countries c ON l.country_id = c.country_id JOIN regions r ON c.region_id = r.region_id " +
                            "WHERE " + "e." + columnName + " LIKE ? " +
                            "ORDER BY region_name, country_name, state_province");
            statement.setString(1, "'%" + partOfName + "%' ");
            System.out.println(statement);
            viewMapper(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return viewList;
    }

    public List<EmployeeJobView> getEmployeeDetailsBySomeString(String columnName, String condition) {
        try (Connection connection = source.getConnection()) {
            String query = "SELECT first_name, last_name, department_name, job_title, region_name, country_name, state_province, city " +
                    "FROM employees e JOIN departments d ON e.department_id = d.department_id " +
                    "JOIN jobs j ON e.job_id = j.job_id JOIN locations l ON d.location_id = l.location_id " +
                    "JOIN countries c ON l.country_id = c.country_id JOIN regions r ON c.region_id = r.region_id " +
                    "WHERE " + columnName + " = ? " +
                    "ORDER BY region_name, country_name, state_province";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, condition);

            System.out.println(statement);
            viewMapper(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return viewList;
    }

}
