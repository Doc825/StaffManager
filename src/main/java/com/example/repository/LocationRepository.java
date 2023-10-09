package com.example.repository;

import com.example.dto.Location;
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
public class LocationRepository {
    private List<Location> locations;
    private MariaDbPoolDataSource source;

    protected List<Location> locationMapper(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        locations.clear();
        while (resultSet.next()) {
            locations.add(Location.builder()
                    .id(resultSet.getInt("location_id"))
                    .street(resultSet.getString("street_address"))
                    .postalCode(resultSet.getString("postal_code"))
                    .city(resultSet.getString("city"))
                    .stateProvince(resultSet.getString("state_province"))
                    .build());
        }
        return new CopyOnWriteArrayList<>(locations);
    }

    public List<Location> getAllLocations() {
        try (Connection connection = source.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT  * FROM locations");
            locationMapper(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return locations;
    }

    public List<Location> getLocationsByCity(String city) {
        try (Connection connection = source.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM locations WHERE city = ?");
            statement.setString(1, city);
            locationMapper(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return locations;
    }
}
