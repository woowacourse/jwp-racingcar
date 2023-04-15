package racingcar.infrastructure.persistence.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import racingcar.infrastructure.persistence.entity.CarEntity;

import java.util.List;

@Component
public class CarDao {

    private final JdbcTemplate template;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private final RowMapper<CarEntity> mapper = (rs, rowNum) -> new CarEntity(
            rs.getString("name"),
            rs.getInt("position"),
            rs.getLong("game_id")
    );

    public CarDao(final JdbcTemplate template) {
        this.template = template;
        this.simpleJdbcInsert = new SimpleJdbcInsert(template)
                .withTableName("CAR")
                .usingGeneratedKeyColumns("id");
    }

    public Long save(final CarEntity carEntity) {
        final SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(carEntity);
        return simpleJdbcInsert.executeAndReturnKey(sqlParameterSource).longValue();
    }

    public List<CarEntity> findByGameId(final Long gameId) {
        return template.query("SELECT * FROM CAR WHERE game_id = ?", mapper, gameId);
    }

    public List<CarEntity> findAll() {
        return template.query("SELECT * FROM CAR", mapper);
    }
}
