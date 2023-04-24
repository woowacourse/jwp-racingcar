package racingcar.dao.web;

import java.util.Arrays;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.dao.WinnerRepository;
import racingcar.dao.entity.WinnerEntity;

@Repository
public class WinnerJdbcRepository implements WinnerRepository {

    private final SimpleJdbcInsert insertActor;
    private final JdbcTemplate jdbcTemplate;

    public WinnerJdbcRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertActor = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("winner")
                .usingGeneratedKeyColumns("winner_id");
    }

    public void saveAll(List<WinnerEntity> winners) {
        SqlParameterSource[] sqlParameterSource = SqlParameterSourceUtils.createBatch(winners);
        Arrays.stream(insertActor.executeBatch(sqlParameterSource));
    }

    public List<Integer> findWinnerCarIdsByGameId(final int gameId) {
        String sql = "SELECT * FROM WINNER WHERE game_id =?";
        return jdbcTemplate.query(sql,(resultSet, rowNum)-> {
            return resultSet.getInt("car_id");
        }, gameId);
    }

}

