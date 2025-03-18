package com.example.touristguide.rowMapper;

import com.example.touristguide.model.City;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityRowMapper implements RowMapper<City> {
    @Override
    public City mapRow(ResultSet rs, int rownum) throws SQLException {
        City city = new City();
        city.setId(rs.getInt("CityID"));
        city.setName(rs.getString("Name"));
        return city;
    }
}
