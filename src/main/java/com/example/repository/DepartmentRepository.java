package com.example.repository;

import com.example.dto.Department;
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
public class DepartmentRepository {
    private MariaDbPoolDataSource source;
    private List<Department> departments;

    protected List<Department> departmentMapper(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        System.out.println(statement);
        departments.clear();
        while (resultSet.next()) {
            departments.add(
                    Department.builder()
                            .id(resultSet.getInt("department_id"))
                            .name(resultSet.getString("department_name"))
                            .build()
            );
        }
        return departments;
    }

    public List<Department> getDepartmentByName(String name) {
        try (Connection connection = source.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM departments WHERE department_name" + "= ?");
            statement.setString(1, name);
            departmentMapper(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return departments;
    }
}
