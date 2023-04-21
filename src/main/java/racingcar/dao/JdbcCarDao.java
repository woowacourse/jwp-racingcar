package racingcar.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import racingcar.service.CarEntity;

@Repository
public class JdbcCarDao implements CarDao {

    private final SimpleJdbcInsert insertActor;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final RowMapper<CarEntity> actorRowMapper = (resultSet, rowNum) -> new CarEntity(
        resultSet.getInt("id"),
        resultSet.getInt("play_result_id"),
        resultSet.getString("name"),
        resultSet.getInt("position")
    );

    public JdbcCarDao(final DataSource dataSource, final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertActor = new SimpleJdbcInsert(dataSource)
            .withTableName("PLAYER_RESULT")
            .usingGeneratedKeyColumns("id");
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<CarEntity> selectCarsByGameId(final int ragingResultId) {
        String sql = "select id, play_result_id, name, position from player_result where PLAY_RESULT_ID = :play_result_id";
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource("play_result_id", ragingResultId);
        return namedParameterJdbcTemplate.query(sql, sqlParameterSource, actorRowMapper);
    }

    public void insertCar(final CarEntity carEntity) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(carEntity);
        Number newId = insertActor.executeAndReturnKey(parameterSource);
        carEntity.setId(newId.intValue());
    }
}
