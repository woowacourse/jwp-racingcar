package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.dto.PlayerResultDto;
import racingcar.entity.PlayerResultEntity;
import racingcar.utils.mapper.EntityToMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PlayersResultDao {
    private final SimpleJdbcInsert insertPlayersResult;
    private final JdbcTemplate jdbcTemplate;

    public PlayersResultDao(final JdbcTemplate jdbcTemplate) {
        this.insertPlayersResult = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("players_result")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<PlayerResultEntity> playersResultRowMapper = (resultSet, rowNum) -> {
        PlayerResultEntity playerResultEntity = new PlayerResultEntity(
                resultSet.getInt("id"),
                resultSet.getInt("result_id"),
                resultSet.getString("name"),
                resultSet.getInt("position")
        );
        return playerResultEntity;
    };

    public void insertResult(final List<? extends PlayerResultDto> playerResultDtos, final int resultId) {
        for (final PlayerResultDto playerResultDto : playerResultDtos) {
            insertPlayersResult.execute(EntityToMap.convert(dto -> {
                        Map<String, Object> params = new HashMap<>();
                        params.put("name", dto.getName());
                        params.put("position", dto.getPosition());
                        return params;
                    }
                    , playerResultDto, resultId));
        }
    }

    public List<PlayerResultEntity> getResultByResultId(final int resultId) {
        String sql = "select * from players_result where result_id = ?";
        return jdbcTemplate.query(sql, playersResultRowMapper, resultId);
    }
}
