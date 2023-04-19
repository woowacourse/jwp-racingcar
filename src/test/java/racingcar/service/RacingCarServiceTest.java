package racingcar.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.dto.RacingResultDTO;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingCarServiceTest {

    @Autowired
    private GameDao gameDao;
    @Autowired
    private CarDao carDao;

    private RacingCarService racingCarService;

    @BeforeEach
    void setUp() {
        racingCarService = new RacingCarService(gameDao, carDao);
    }

    @Test
    void 게임을_진행한다() {
        //given
        final RacingResultDTO racingResultDTO = racingCarService.play("huchu,gavi", 5);

        //when
        //then
        assertSoftly(softly -> {
            softly.assertThat(racingResultDTO.getWinners()).isNotNull();
            softly.assertThat(racingResultDTO.getRacingCars()).isNotNull();
        });
    }

    @Test
    void 게임_결과를_조회한다() {
        //given
        final RacingResultDTO playedResult = racingCarService.play("huchu,gavi", 5);

        //when
        final List<RacingResultDTO> showedResults = racingCarService.showGameResults();
        final RacingResultDTO showedResult = showedResults.get(0);

        //then
        assertThat(showedResult).isEqualTo(playedResult);
    }
}
