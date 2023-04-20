package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.dto.car.GameResultResponseDto;
import racingcar.dto.car.PlayerHistoryDto;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
public class GameResultDaoTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    GameResultDao gameResultDao;

    private final Cars cars = Cars.from(List.of("pobi", "crong"));
    private final TryCount tryCount = new TryCount(3);

    @BeforeEach
    void init() {
        gameResultDao = new GameResultDao(jdbcTemplate);
    }

    @Test
    @DisplayName("현재 저장된 게임의 id를 반환한다.")
    void returns_games_id() {
        // given
        gameResultDao.saveGame(cars, tryCount);

        // when
        List<Long> allGamesId = gameResultDao.findAllGamesId();

        // then
        assertThat(allGamesId.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("게임 id 기준으로 플레이어들의 정보를 가져온다.")
    void returns_player_histories_by_game_id() {
        // given
        gameResultDao.saveGame(cars, tryCount);

        // when
        List<PlayerHistoryDto> result = gameResultDao.findPlayerHistoriesByGameId(3L);

        // then
        assertThat(result.size()).isEqualTo(cars.getCars().size());
    }

    @Test
    @DisplayName("게임을 저장한다.")
    void save_game() {
        // when
        GameResultResponseDto result = gameResultDao.saveGame(cars, tryCount);

        // then
        assertThat(result.getRacingCars().size()).isEqualTo(cars.getCars().size());
    }
}
