package racingcar;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dto.RacingCarStatusResponse;

@Repository
public class PlayerInsertDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertPlayer(List<RacingCarStatusResponse> responses) {
        List<Object[]> players = responses.stream()
                .map(response -> new Object[]{
                        response.getName(), response.getPosition()
                }).collect(Collectors.toList());
        String sql = "INSERT INTO player (name, position) VALUES (?, ?)";
        jdbcTemplate.batchUpdate(sql, players);
    }
}
