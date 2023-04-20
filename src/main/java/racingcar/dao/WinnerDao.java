package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.dao.entity.WinnerEntity;

import java.util.List;

@Repository
public class WinnerDao {

    private final SimpleJdbcInsert insertActor;
    private final JdbcTemplate jdbcTemplate;

    public WinnerDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertActor = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("winner");
    }

    public int[] saveAll(List<WinnerEntity> winners) {
        SqlParameterSource[] sqlParameterSource = SqlParameterSourceUtils.createBatch(winners);
        return insertActor.executeBatch(sqlParameterSource);
    }

    public List<Integer> findWinnerCarIdsByGameId(final int gameId) {
        String sql = "SELECT * FROM WINNER WHERE game_id =?";
        return jdbcTemplate.query(sql,(resultSet, rowNum)-> {
            return resultSet.getInt("car_id");
        }, gameId);
    }

}

