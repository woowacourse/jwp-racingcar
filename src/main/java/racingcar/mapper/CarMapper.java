package racingcar.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.entity.CarEntity;

import java.sql.PreparedStatement;

@Repository
public class CarMapper {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public CarMapper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<CarEntity> entityRowMapper = (resultSet, rowNum) -> {
        CarEntity carEntity = new CarEntity(
                resultSet.getLong("id"),
                resultSet.getLong("play_result_id"),
                resultSet.getString("name"),
                resultSet.getInt("position")
        );
        return carEntity;
    };
    
    public long save(CarEntity carEntity) {
        String sql = "INSERT INTO car(play_result_id, name, position) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator psc = (connection) -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, carEntity.getPlayResultId());
            ps.setString(2, carEntity.getName());
            ps.setInt(3, carEntity.getPosition());
            return ps;
        };

        jdbcTemplate.update(psc, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public CarEntity findById(long id) {
        String sql = "SELECT * FROM car WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, entityRowMapper, id);
    }
}
