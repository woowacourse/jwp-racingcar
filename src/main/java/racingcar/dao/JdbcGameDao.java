package racingcar.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import racingcar.dto.ResultDto;

@Repository
public class JdbcGameDao implements GameDao{
    private final SimpleJdbcInsert insertActor;

    public JdbcGameDao(DataSource dataSource) {
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("game")
                .usingColumns("trialCount")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public long saveGame(int trialCount, ResultDto resultDto) {
        Map<String, Object> map = new HashMap<>();
        map.put("trialCount", trialCount);
        return insertActor.executeAndReturnKey(map).longValue();
    }
}
