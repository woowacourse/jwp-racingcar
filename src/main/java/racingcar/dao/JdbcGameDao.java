package racingcar.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcGameDao implements GameDao {
    private final SimpleJdbcInsert insertActor;

    public JdbcGameDao(DataSource dataSource) {
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("game")
                .usingColumns("trialCount")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public long saveGame(int trialCount) {
        Map<String, Object> map = new HashMap<>();
        map.put("trialCount", trialCount);
        return insertActor.executeAndReturnKey(map).longValue();
    }
}
