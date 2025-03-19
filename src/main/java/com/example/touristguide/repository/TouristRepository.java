package com.example.touristguide.repository;

import com.example.touristguide.model.*;
import com.example.touristguide.rowMapper.CityRowMapper;
import com.example.touristguide.rowMapper.TagRowMapper;
import com.example.touristguide.rowMapper.TouristAttractionRowMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class TouristRepository {
    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    private final DataSource dataSource = new DriverManagerDataSource(dbUrl,username,password);
    private final JdbcTemplate jdbcTemplate;

    public TouristRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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
        String sqlTags = "INSERT INTO attraction_tag (AttractionID,TagID) VALUES (?,?)";

        for (Tag tag : attraction.getTags()){
            jdbcTemplate.update(sqlTags,attraction.getId(),tag.getId());
        }
    }

    // opdater en attraktion
    public void updateAttraction(TouristAttraction attraction) {
        String deleteOldTags = "DELETE * FROM Attraction_Tag WHERE attractionId = ?";
        jdbcTemplate.update(deleteOldTags,attraction.getId());
        String createNewTags = "insert into Attraction_Tag (AttractionID,TagID) VALUES (?,?)";

        for (Tag tag: attraction.getTags()){
            jdbcTemplate.update(createNewTags,attraction.getId(),tag.getId());
        }
        String sql = "UPDATE attraction SET Name = ?, Description = ?, CityID = ? WHERE AttractionID = ?";
        jdbcTemplate.update(sql, attraction.getName(), attraction.getDescription(), attraction.getCity().getId(), attraction.getId());
    }

    // slet en attraktion
    public boolean deleteAttraction(String name) {
        String sql1 = "DELETE attraction_tag FROM attraction_tag JOIN attraction ON attraction_tag.AttractionID = attraction.AttractionID WHERE attraction.name = ?";
        jdbcTemplate.update(sql1, name);

        String sql2 = "DELETE FROM attraction WHERE name = ?";
        int rowsAffected = jdbcTemplate.update(sql2, name);

        return rowsAffected > 0;
    }

    // hent alle byer
    public List<City> getCities() {
        String sql = "SELECT * FROM cities";
        return jdbcTemplate.query(sql, new CityRowMapper());
    }

    // hent by med id
    public City getCityById(int cityId) {
        System.out.println("tisselort" + cityId);
        String sql = "SELECT * FROM cities WHERE CityID = ?";
        List<City> cities = jdbcTemplate.query(sql, new CityRowMapper(), cityId);
        if (cities.isEmpty()) {
            throw new RuntimeException("ROTTELORT");
        } else {
            return cities.get(0);
        }
    }

    public List<Tag> getAllTags() {
        String sql = "SELECT * FROM tags";
        return jdbcTemplate.query(sql, new TagRowMapper());
    }

    // hent tags for en attraktion
    public List<Tag> getTagsForAttraction(int attractionId) {
        String sql = "SELECT * " +
                "FROM tags " +
                "JOIN Attraction_Tag ON tags.TagID = Attraction_Tag.TagID " +
                "WHERE Attraction_Tag.AttractionID = ?";
        return jdbcTemplate.query(sql, new TagRowMapper(), attractionId);
    }

    public Tag findTagById(int id){
        String sql = "SELECT * from tags where TagID = ?";
        List<Tag> tag = jdbcTemplate.query(sql,new TagRowMapper(),id);
        if (tag.isEmpty()){
            return null;
        }else {
            return tag.get(0);
        }
    }
}
