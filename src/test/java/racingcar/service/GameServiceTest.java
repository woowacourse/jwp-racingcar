package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.Car;
import racingcar.domain.Game;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GameServiceTest {

    @Autowired
    private GameService gameService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @DisplayName("게임 생성 테스트")
    void create_game() {
        final List<String> names = List.of("땡칠", "필립", "다니", "코일");
        final Game game = gameService.createGameWith(names, 5);

        assertThat(getNamesOf(game.getCars())).containsExactlyElementsOf(names);
    }

    private List<String> getNamesOf(List<Car> cars) {
        return cars.stream()
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    @Test
    @DisplayName("게임 진행 테스트")
    void play_game() {
        final List<String> names = List.of("땡칠", "필립", "다니", "코일");
        final Game game = gameService.createGameWith(names, 5);

        gameService.play(game);

        assertThat(game.getTrialCount()).isEqualTo(5);
    }

    @Test
    @DisplayName("진행 후 게임 저장 테스트")
    void save_game() {
        final List<String> names = List.of("땡칠", "필립", "다니", "코일");
        final Game game = gameService.createGameWith(names, 5);

        gameService.play(game);
        final String sql = "select trial_count from games where id = ?";
        final Integer trialCount = jdbcTemplate.queryForObject(sql, Integer.class, getGreatestGameId());

        assertThat(trialCount).isEqualTo(5);
    }

    @Test
    @DisplayName("진행 후 자동차 저장 테스트")
    void save_cars() {
        final List<String> names = List.of("땡칠", "필립", "다니", "코일");
        final Game game = gameService.createGameWith(names, 5);

        gameService.play(game);
        final String sql = "select name from cars where game_id = ?";
        final List<String> actualNames = jdbcTemplate.queryForList(sql, String.class, getGreatestGameId());

        assertThat(actualNames).containsExactlyInAnyOrderElementsOf(names);
    }

    @Test
    @DisplayName("진행 후 우승자 저장 테스트")
    void save_winners() {
        final List<String> names = List.of("땡칠", "필립", "다니", "코일");
        final Game game = gameService.createGameWith(names, 5);

        gameService.play(game);
        final String sql = "select count(car_id) from winners where game_id = ?";
        final Integer winnerCount = jdbcTemplate.queryForObject(sql, Integer.class, getGreatestGameId());

        assertThat(winnerCount).isGreaterThanOrEqualTo(1);
    }

    private Integer getGreatestGameId() {
        final String sql = "select max(id) as id from games";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
