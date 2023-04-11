package racingcar.infrastructure.persistence.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import racingcar.infrastructure.persistence.entity.WinnerEntity;

import java.sql.PreparedStatement;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Component
public class WinnerDao {

    private final JdbcTemplate template;

    public WinnerDao(final JdbcTemplate template) {
        this.template = template;
    }

    public Long save(final WinnerEntity winnerEntity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String sql = "INSERT INTO WINNER (name, game_id) VALUES (?, ?)";
        template.update(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql, RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, winnerEntity.getName());
            preparedStatement.setLong(2, winnerEntity.getGameId());
            return preparedStatement;
        }, keyHolder);

        return ((long) keyHolder.getKeys().get("id"));
    }

    public List<WinnerEntity> findByGameId(final Long gameId) {
        return template.query("SELECT * FROM WINNER WHERE game_id = ?",
                (rs, rowNum) -> new WinnerEntity(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getLong(3)
                ), gameId);
    }
}
