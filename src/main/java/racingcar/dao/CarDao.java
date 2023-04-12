package racingcar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import racingcar.dto.RacingCarResultDto;

@Repository
public class CarDao {

    @Autowired
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CarDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(RacingCarResultDto racingCarResultDto) {
        String sql = "insert into car (name, position, is_win, game_id) values (:name, :position, :isWin, :gameId)";
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(racingCarResultDto);
        jdbcTemplate.update(sql, namedParameters);
    }
}
