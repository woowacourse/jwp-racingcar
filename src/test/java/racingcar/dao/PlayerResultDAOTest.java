package racingcar.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.PlayerResultDto;

import java.sql.PreparedStatement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ComponentScan
@JdbcTest
class PlayerResultDAOTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PlayerResultDAO playerResultDAO;

    @DisplayName("플레이어들의 게임 결과를 저장할 수 있다.")
    @Transactional
    @Test
    void savePlayerResultTest() {
        //given
        List<PlayerResultDto> resultDto = List.of(
                new PlayerResultDto("zuny", 5)
        );
        int savedId = saveSampleGameResult();

        //when
        playerResultDAO.save(savedId, resultDto);

        //then
        PlayerResultDto findResult = playerResultDAO.findAllByGameId(savedId).get(0);

        assertThat(findResult.getPosition())
                .isEqualTo(resultDto.get(0).getPosition());
        assertThat(findResult.getName())
                .isEqualTo(resultDto.get(0).getName());
    }

    private int saveSampleGameResult() {
        String sql = "insert into GAME_RESULT (winners, trial_count) values (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setString(1, "sample");
            preparedStatement.setInt(2, 10);
            return preparedStatement;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }
}
