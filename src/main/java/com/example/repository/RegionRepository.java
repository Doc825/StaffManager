package com.example.repository;

import com.example.dto.Region;
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
public class RegionRepository {
    private List<Region> regionList;
    private MariaDbPoolDataSource source;

    protected List<Region> regionMapper(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        regionList.clear();
        while (resultSet.next()) {
            regionList.add(Region.builder()
                    .id(resultSet.getInt("region_id"))
                    .name(resultSet.getString("region_name"))
                    .build()
            );
        }
        return new CopyOnWriteArrayList<>(regionList);
    }

    public List<Region> getAllRegions() {
        try (Connection connection = source.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM regions");
            regionMapper(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return regionList;
    }

    public List<Region> getRegionByName(String name) {
        try (Connection connection = source.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM regions WHERE region_name = ?");
            statement.setString(1, name);
            regionMapper(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return regionList;
    }
}
