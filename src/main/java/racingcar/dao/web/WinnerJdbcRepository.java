package racingcar.dao.web;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.dao.WinnerRepository;
import racingcar.dao.entity.WinnerEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class WinnerJdbcRepository implements WinnerRepository {

    private final SimpleJdbcInsert insertActor;
    private final JdbcTemplate jdbcTemplate;

    public WinnerJdbcRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertActor = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("winner");
    }

    public List<Integer> saveAll(List<WinnerEntity> winners) {
        SqlParameterSource[] sqlParameterSource = SqlParameterSourceUtils.createBatch(winners);
        return Arrays.stream(insertActor.executeBatch(sqlParameterSource))
                .mapToObj(Integer::new)
                .collect(Collectors.toList());
    }

    public List<Integer> findWinnerCarIdsByGameId(final int gameId) {
        String sql = "SELECT * FROM WINNER WHERE game_id =?";
        return jdbcTemplate.query(sql,(resultSet, rowNum)-> {
            return resultSet.getInt("car_id");
        }, gameId);
    }

}

