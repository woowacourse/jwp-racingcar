package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import racingcar.AlwaysMoveNumberGenerator;
import racingcar.dao.CarDao;
import racingcar.dao.RacingGameDao;
import racingcar.dto.RacingGameRequest;
import racingcar.dto.RacingGameResponse;

@Import(value = AlwaysMoveNumberGenerator.class)
@SpringBootTest
class RacingGameServiceTest {

    @Autowired
    CarDao carDao;

    @Autowired
    RacingGameDao racingGameDao;

    @Test
    void playTest() {
        RacingGameRequest racingGameRequest = new RacingGameRequest("박스터,엔초", 10);
        List<String> winners = List.of("박스터", "엔초");

        RacingGameService racingGameService = new RacingGameService(carDao, racingGameDao,
                new AlwaysMoveNumberGenerator());
        RacingGameResponse play = racingGameService.play(racingGameRequest);

        assertThat(play.getWinners()).isEqualTo(winners);
        assertThat(play.getRacingCars()).hasSize(2);
    }
}
