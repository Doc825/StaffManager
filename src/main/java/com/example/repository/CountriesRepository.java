package com.example.repository;

import com.example.dto.Country;
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
public class CountriesRepository {
    private final MariaDbPoolDataSource source;
    private List<Country> countries;

    public List<Country> countryMapper(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        countries.clear();
        while (resultSet.next()) {
            countries.add(
                    Country.builder()
                            .id(resultSet.getString("country_id"))
                            .name(resultSet.getString("country_name"))
                            .build()
            );
        }
        return countries;
    }

    public List<Country> getAllCountries() {
        try (Connection connection = source.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM countries");
            countryMapper(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return countries;
    }
}
