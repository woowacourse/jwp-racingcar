package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import racingcar.DatabaseTest;
import racingcar.service.dto.ResultDto;

@DatabaseTest
class GameServiceTest {

    @Autowired
    private GameService gameService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @DisplayName("게임 진행 테스트")
    void play_game() {
        final List<String> names = List.of("땡칠", "필립", "다니", "코일");
        ResultDto resultDto = gameService.playWith(names, 5);

        assertThat(resultDto.getWinners()).containsAnyElementsOf(names);
    }

    @Test
    @DisplayName("진행 후 게임 저장 테스트")
    void save_game() {
        final List<String> names = List.of("땡칠", "필립", "다니", "코일");

        gameService.playWith(names, 5);
        final String sql = "select remaining_trial_count from gamestates where id = ?";
        final Integer trialCount = jdbcTemplate.queryForObject(sql, Integer.class, getGreatestGameId());

        assertThat(trialCount).isEqualTo(0);
    }

    @Test
    @DisplayName("진행 후 자동차 저장 테스트")
    void save_cars() {
        final List<String> names = List.of("땡칠", "필립", "다니", "코일");

        gameService.playWith(names, 5);
        final String sql = "select name from cars where game_id = ?";
        final List<String> actualNames = jdbcTemplate.queryForList(sql, String.class, getGreatestGameId());

        assertThat(actualNames).containsExactlyInAnyOrderElementsOf(names);
    }

    private Integer getGreatestGameId() {
        final String sql = "select max(id) as id from gamestates";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
