package racingcar.repository;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import racingcar.repository.entity.CarEntity;

public class InsertCarDao {

    private final SimpleJdbcInsert insertActor;

    public InsertCarDao(final DataSource dataSource) {
        insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("car")
                .usingGeneratedKeyColumns("car_id");
    }

    public void saveAll(final List<CarEntity> carEntities, final int gameId) {
        final MapSqlParameterSource[] batch = carEntities.stream().map(carEntity ->
                new MapSqlParameterSource()
                        .addValue("game_id", gameId)
                        .addValue("name", carEntity.getName())
                        .addValue("position", carEntity.getPosition())
        ).toArray(MapSqlParameterSource[]::new);

        insertActor.executeBatch(batch);
    }
}
