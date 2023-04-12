package racingcar.infrastructure.persistence.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import racingcar.infrastructure.persistence.entity.WinnerEntity;

import java.util.List;

@Component
public class WinnerDao {

    private final JdbcTemplate template;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public WinnerDao(final JdbcTemplate template) {
        this.template = template;
        this.simpleJdbcInsert = new SimpleJdbcInsert(template)
                .withTableName("WINNER")
                .usingGeneratedKeyColumns("id");
    }

    public Long save(final WinnerEntity winnerEntity) {
        final SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(winnerEntity);
        return simpleJdbcInsert.executeAndReturnKey(sqlParameterSource).longValue();
    }

    public List<WinnerEntity> findByGameId(final Long gameId) {
        return template.query("SELECT * FROM WINNER WHERE game_id = ?",
                (rs, rowNum) -> new WinnerEntity(
                        rs.getString("name"),
                        rs.getLong("game_id")
                ), gameId);
    }
}
