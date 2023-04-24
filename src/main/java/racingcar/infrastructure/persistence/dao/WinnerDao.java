package racingcar.infrastructure.persistence.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import racingcar.infrastructure.persistence.entity.WinnerEntity;

import java.util.List;

@Component
public class WinnerDao {

    private final JdbcTemplate template;
    private final RowMapper<WinnerEntity> mapper = (rs, rowNum) -> new WinnerEntity(
            rs.getString("name"),
            rs.getLong("game_id")
    );

    public WinnerDao(final JdbcTemplate template) {
        this.template = template;
    }

    public void save(final List<WinnerEntity> entities) {
        template.batchUpdate("INSERT INTO WINNER (name, game_id) VALUES (?, ?)",
                entities,
                50,
                (ps, entity) -> {
                    ps.setString(1, entity.getName());
                    ps.setLong(2, entity.getGameId());
                });
    }

    public List<WinnerEntity> findByGameId(final Long gameId) {
        return template.query("SELECT * FROM WINNER WHERE game_id = ?", mapper, gameId);
    }

    public List<WinnerEntity> findAll() {
        return template.query("SELECT * FROM WINNER", mapper);
    }
}
