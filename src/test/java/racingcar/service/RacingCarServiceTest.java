package racingcar.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.domain.numbergenerator.NumberGenerator;
import racingcar.dto.CarDTO;
import racingcar.dto.RacingResultDTO;

import java.util.List;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingCarServiceTest {

    private static final int MOVEABLE_NUMBER = 5;

    @Autowired
    private GameDao gameDao;
    @Autowired
    private CarDao carDao;

    private RacingCarService racingCarService;

    @BeforeEach
    void setUp() {
        racingCarService = new RacingCarService(gameDao, carDao, new TestNumberGenerator());
    }

    @Test
    void 게임을_진행한다() {
        //given
        final RacingResultDTO racingResultDTO = racingCarService.play("huchu,gavi", 1);

        //when
        //then
        assertSoftly(softly -> {
            softly.assertThat(racingResultDTO.getWinners()).isEqualTo("huchu,gavi");
            final List<CarDTO> racingCars = racingResultDTO.getRacingCars();
            softly.assertThat(racingCars).hasSize(2);
            softly.assertThat(getName(racingCars, 0)).isEqualTo("huchu");
            softly.assertThat(getName(racingCars, 1)).isEqualTo("gavi");
            softly.assertThat(getPosition(racingCars, 0)).isEqualTo(1);
            softly.assertThat(getPosition(racingCars, 1)).isEqualTo(1);
        });
    }

    private String getName(final List<CarDTO> racingCars, final int index) {
        return racingCars.get(index).getName();
    }

    private int getPosition(final List<CarDTO> racingCars, final int index) {
        return racingCars.get(index).getPosition();
    }

    @Test
    void 게임_결과를_조회한다() {
        //given
        racingCarService.play("huchu,gavi", 1);

        //when
        final List<RacingResultDTO> showedResults = racingCarService.showGameResults();
        
        //then
        assertSoftly(softly -> {
            softly.assertThat(showedResults).hasSize(1);

            final RacingResultDTO showedResult = showedResults.get(0);
            softly.assertThat(showedResult.getWinners()).isEqualTo("huchu,gavi");

            final List<CarDTO> racingCars = showedResult.getRacingCars();
            softly.assertThat(racingCars).hasSize(2);
            softly.assertThat(getName(racingCars, 0)).isEqualTo("huchu");
            softly.assertThat(getName(racingCars, 1)).isEqualTo("gavi");
            softly.assertThat(getPosition(racingCars, 0)).isEqualTo(1);
            softly.assertThat(getPosition(racingCars, 1)).isEqualTo(1);
        });
    }

    private static class TestNumberGenerator implements NumberGenerator {

        @Override
        public int generate() {
            return MOVEABLE_NUMBER;
        }
    }
}
