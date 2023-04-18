package racingcar.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.dto.RacingCarResultDto;

@Transactional
@SpringBootTest
class RacingGameServiceTest {

    @Autowired
    GameDao gameDao;

    @Autowired
    CarDao carDao;

    @Autowired
    RacingGameService gameService;

    @Test
    @DisplayName("게임 실행 테스트")
    public void run() {
        List<String> carNames = List.of("오잉", "포이");
        List<RacingCarResultDto> gameResult = gameService.run(carNames, 3);
        long gameId = gameResult.get(0).getGameId();
        assertThat(gameResult.size()).isEqualTo(2);
        assertThat(gameResult.get(0).getName()).isEqualTo("오잉");
        assertThat(gameResult.get(1).getName()).isEqualTo("포이");

        List<RacingCarResultDto> gameResultFromDb = carDao.findCarsById(gameId);
        assertThat(gameResultFromDb.size()).isEqualTo(2);
        assertThat(gameResultFromDb.get(0).getName()).isEqualTo("오잉");
        assertThat(gameResultFromDb.get(1).getName()).isEqualTo("포이");
    }

    @Test
    @DisplayName("게임 이력 조회 테스트")
    public void history() {
        List<String> carNames1 = List.of("오잉", "포이");
        List<RacingCarResultDto> gameResult1 = gameService.run(carNames1, 3);

        List<String> carNames2 = List.of("잉오", "이포");
        List<RacingCarResultDto> gameResult2 = gameService.run(carNames2, 3);

        List<List<RacingCarResultDto>> history = gameService.history();
        assertThat(history.size()).isEqualTo(2);

        assertThat(history.get(0).size()).isEqualTo(2);
        assertThat(history.get(0).get(0).getName()).isEqualTo("오잉");
        assertThat(history.get(0).get(1).getName()).isEqualTo("포이");

        assertThat(history.get(1).size()).isEqualTo(2);
        assertThat(history.get(1).get(0).getName()).isEqualTo("잉오");
        assertThat(history.get(1).get(1).getName()).isEqualTo("이포");
    }
}
