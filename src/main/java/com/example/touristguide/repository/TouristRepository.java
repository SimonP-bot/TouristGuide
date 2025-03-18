package com.example.touristguide.repository;

import com.example.touristguide.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.metrics.StartupStep;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class TouristRepository {
    private final JdbcTemplate jdbcTemplate;

    public TouristRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // hent alle attraktioner
    public List<TouristAttraction> getAllAttractions() {
        String sql = "SELECT * FROM attraction";
        return jdbcTemplate.query(sql, new TouristAttractionRowMapper());
    }

    // hent attraktion med navn
    public TouristAttraction getAttractionByName(String name) {
        String sql = "SELECT * FROM attraction WHERE Name = ?";
        List<TouristAttraction> attractions = jdbcTemplate.query(sql, new TouristAttractionRowMapper(), name);
        if (attractions.isEmpty()) {
            return null;
        } else {
            return attractions.get(0);
        }
    }

    // tilføj attraktion
    public void addAttraction(TouristAttraction attraction) {
        String sql = "INSERT INTO attraction (Name, Description, CityID) VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] {"AttractionID"});
            ps.setString(1, attraction.getName());
            ps.setString(2, attraction.getDescription());
            ps.setInt(3, attraction.getCity().getId());
            return ps;
        }, keyHolder);
        attraction.setId(keyHolder.getKey().intValue());
        String sqlTags = "Insert into attraction_tag (AtttractionID,TagID) VALUES (?,?)";

        for (Tag tag : attraction.getTags()){
            jdbcTemplate.update(sqlTags,attraction.getId(),tag.getId());
        }



    }

    // opdater en attraktion
    public void updateAttraction(TouristAttraction attraction) {
        String sql = "UPDATE attraction SET Name = ?, Description = ?, CityID = ? WHERE AttractionID = ?";
        jdbcTemplate.update(sql, attraction.getName(), attraction.getDescription(), attraction.getCity().getId(), attraction.getId());
    }

    // slet en attraktion
    public boolean deleteAttraction(int attractionId) {
        String sql = "DELETE FROM attraction WHERE AttractionID = ?";
        return jdbcTemplate.update(sql, attractionId) > 0;
    }

    // hent alle byer
    public List<City> getCities() {
        String sql = "SELECT * FROM cities";
        return jdbcTemplate.query(sql, new CityRowMapper());
    }

    // hent by med navn
    public City getCityByName(String name) {
        String sql = "SELECT * FROM cities WHERE Name = ?";
        List<City> cities = jdbcTemplate.query(sql, new CityRowMapper(), name);
        if (cities.isEmpty()) {
            return null;
        } else {
            return cities.get(0);
        }
    }

    public List<Tag> getAllTags() {
        String sql = "SELECT * FROM tags";
        return jdbcTemplate.query(sql, new TagRowMapper());
    }


    public void addTagsToAttraction(TouristAttraction attraction) {
        for (int tagId : tagIds) {
            String sql = "INSERT INTO Attraction_Tag (AttractionID, TagID) VALUES (?, ?)";
            jdbcTemplate.update(sql, attractionId, tagId);
        }
    }

    public void updateAttractionTags(int attractionId, List<Integer> tagIds) {
        String sql = "DELETE FROM Attraction_Tag WHERE AttractionID = ?";
        jdbcTemplate.update(sql, attractionId);
        addTagsToAttraction(attractionId, tagIds);
    }
    // hent tags for en attraktion
    public List<Tag> getTagsForAttraction(int attractionId) {
        String sql = "SELECT * " +
                "FROM tags " +
                "JOIN Attraction_Tag ON tags.TagID = Attraction_Tag.TagID " +
                "WHERE Attraction_Tag.AttractionID = ?";
        return jdbcTemplate.query(sql, new TagRowMapper(), attractionId);
    }
}
