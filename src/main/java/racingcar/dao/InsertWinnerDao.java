package racingcar.dao;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import racingcar.dao.entity.WinnerEntity;

public class InsertWinnerDao {

    private final SimpleJdbcInsert insertActor;

    public InsertWinnerDao(final JdbcTemplate jdbcTemplate) {
        insertActor = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("winner")
                .usingGeneratedKeyColumns("winner_id");
    }

    public void insertAll(final List<WinnerEntity> winnerEntities) {
        final BeanPropertySqlParameterSource[] batch = winnerEntities.stream()
                .map(BeanPropertySqlParameterSource::new)
                .toArray(BeanPropertySqlParameterSource[]::new);

        insertActor.executeBatch(batch);
    }
}
