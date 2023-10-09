package com.example.repository;

import com.example.dto.Job;
import lombok.AllArgsConstructor;
import org.mariadb.jdbc.MariaDbPoolDataSource;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
@AllArgsConstructor
public class JobRepository {
    private List<Job> jobs;
    private MariaDbPoolDataSource source;
    protected List<Job> jobsMapper(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        jobs.clear();
        while (resultSet.next()) {
            jobs.add(Job.builder()
                    .id(resultSet.getString("job_id"))
                    .jobTitle(resultSet.getString("job_title"))
                    .minSalary(resultSet.getBigDecimal("min_salary"))
                    .maxSalary(resultSet.getBigDecimal("max_salary"))
                    .build());
        }
        return new CopyOnWriteArrayList<>(jobs);
    }
    public List<Job> getAllJobs() {
        try(Connection connection = source.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM jobs");
            jobsMapper(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return jobs;
    }
    public List<Job> getJobByTitle(String jobTitle) {
        try(Connection connection = source.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM jobs WHERE job_title = ?");
            statement.setString(1, jobTitle);
            jobsMapper(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return jobs;
    }
}
