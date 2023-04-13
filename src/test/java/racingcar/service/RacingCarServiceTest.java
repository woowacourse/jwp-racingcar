package racingcar.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.dto.ResponseDTO;

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
        final ResponseDTO responseDTO = racingCarService.play("huchu,gavi", 5);

        //when
        //then
        assertSoftly(softly -> {
            softly.assertThat(responseDTO.getWinners()).isNotNull();
            softly.assertThat(responseDTO.getRacingCars()).isNotNull();
        });
    }
}
