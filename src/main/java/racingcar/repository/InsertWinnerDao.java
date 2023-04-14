package racingcar.repository;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import racingcar.repository.entity.CarEntity;

public class InsertWinnerDao {

    private final SimpleJdbcInsert insertActor;

    public InsertWinnerDao(final DataSource dataSource) {
        insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("winner")
                .usingGeneratedKeyColumns("winner_id");
    }

    public void saveAll(final List<CarEntity> winners, final int gameId) {
        final MapSqlParameterSource[] batch = winners.stream().map(winner ->
                new MapSqlParameterSource()
                        .addValue("game_id", gameId)
                        .addValue("car_id", winner.getId())
        ).toArray(MapSqlParameterSource[]::new);

        insertActor.executeBatch(batch);
    }
}
