package com.example.touristguide.rowMapper;

import com.example.touristguide.model.City;
import com.example.touristguide.model.TouristAttraction;
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
