package com.example.touristguide.rowMapper;

import com.example.touristguide.model.Tag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagRowMapper implements RowMapper<Tag> {
    @Override
    public Tag mapRow(ResultSet rs, int rownum) throws SQLException {
        return new Tag(
                rs.getInt("TagID"),
                rs.getString("TagName")
        );
    }
}
