package com.example.touristguide.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityRowMapper implements RowMapper<City> {
    @Override
    public City mapRow(ResultSet rs, int rownum) throws SQLException {
        return new City(
                rs.getInt("CityID"),
                rs.getString("Name")
        );
    }
}
