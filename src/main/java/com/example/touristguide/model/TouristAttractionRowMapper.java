package com.example.touristguide.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TouristAttractionRowMapper implements RowMapper<TouristAttraction> {
    @Override
    public TouristAttraction mapRow(ResultSet rs, int rownum) throws SQLException {
        City city = new City(rs.getInt("CityID"),rs.getString("Name"));
        return new TouristAttraction(
                rs.getInt("AttractionID"),
                rs.getString("Name"),
                rs.getString("Description"),
                city
        );
    }
}
