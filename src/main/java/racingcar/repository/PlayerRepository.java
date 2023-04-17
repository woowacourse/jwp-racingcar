package racingcar.repository;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;
import racingcar.PlayerDto;
import racingcar.dto.RacingCarStatusDto;

@Repository
public class PlayerRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PlayerRepository(final NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveAll(final List<RacingCarStatusDto> responses, final List<String> winnerNames, final long gameId) {
        String sql = "INSERT INTO player(game_id, name, position, is_winner) VALUES(:gameId, :name, :position, :isWinner)";

        List<PlayerDto> playerDtos = responses.stream()
                .map(response -> PlayerDto.of(response, gameId, winnerNames.contains(response.getName())))
                .collect(Collectors.toList());

        jdbcTemplate.batchUpdate(sql, SqlParameterSourceUtils.createBatch(playerDtos));
    }
}
