package com.example;

import com.example.dto.Country;
import com.example.dto.Employee;
import org.mariadb.jdbc.MariaDbPoolDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Configuration
public class Config {
    @Value("${dbUrl}")
    private String dbUrl;

    @Bean
    public MariaDbPoolDataSource getHrDatabase() throws SQLException {
        MariaDbPoolDataSource source = new MariaDbPoolDataSource();
        source.setUrl(dbUrl);
        return source;
    }

    @Bean
    public List<Employee> getEmployees() {
        return Collections.synchronizedList(new ArrayList<>());
    }

    @Bean
    public List<Country> getCountries() {
        return Collections.synchronizedList(new ArrayList<>());
    }
}
