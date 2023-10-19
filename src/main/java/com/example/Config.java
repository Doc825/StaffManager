package com.example;

import org.mariadb.jdbc.MariaDbPoolDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;


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
}
