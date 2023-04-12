package racingcar.dao;

import java.util.List;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class RacingGameDaoImpl implements RacingGameDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public RacingGameDaoImpl(final NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long saveGame(final int trialCount) {
        final String sql = "insert into Game (trial_count) values (:trialCount)";
        final SqlParameterSource gameParameters = new MapSqlParameterSource("trialCount", trialCount);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, gameParameters, keyHolder);
        return (long) keyHolder.getKeys().get("id");
    }

    @Override
    public void saveAllPlayers(final List<PlayerSaveDto> playerSaveDtos) {
        final String sql = "insert into Player (game_id, name, position, is_winner) "
                + "values (:gameId, :name, :position, :isWinner)";

        jdbcTemplate.batchUpdate(sql, SqlParameterSourceUtils.createBatch(playerSaveDtos));
    }
}
