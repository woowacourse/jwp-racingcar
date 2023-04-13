package racingcar.repository;

import java.util.List;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dto.RacingCarStatusResponse;

@Repository
public class PlayerRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final WinnerRepository winnerRepository;

    public PlayerRepository(final NamedParameterJdbcTemplate jdbcTemplate, final WinnerRepository winnerRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.winnerRepository = winnerRepository;
    }

    public void insertPlayer(final List<RacingCarStatusResponse> responses, final List<String> winnerNames, final int gameId) {
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO player(name, position) VALUES(:name, :position)";

        for (RacingCarStatusResponse response : responses) {
            SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(response);
            jdbcTemplate.update(sql, parameterSource, generatedKeyHolder);

            if (winnerNames.contains(response.getName())) {
                int playerId = generatedKeyHolder.getKey().intValue();
                winnerRepository.insertWinner(gameId, playerId);
            }
        }
    }
}
