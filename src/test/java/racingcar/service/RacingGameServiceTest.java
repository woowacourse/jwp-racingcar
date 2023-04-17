package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.Optional;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.domain.NumberGenerator;
import racingcar.dto.GamePlayRequestDto;
import racingcar.dto.GamePlayResponseDto;
import racingcar.entity.CarEntity;
import racingcar.utils.TestNumberGenerator;

@DisplayName("RacingGameService 클래스")
@DisplayNameGeneration(ReplaceUnderscores.class)
class RacingGameServiceTest {

    private GameDao gameDao;
    private CarDao carDao;
    private RacingGameService racingGameService;

    @BeforeEach
    void setUp() {
        gameDao = Mockito.mock(GameDao.class);
        carDao = Mockito.mock(CarDao.class);
        final NumberGenerator numberGenerator = new TestNumberGenerator(Lists.newArrayList(4, 3, 3));
        final RacingGameMapper racingGameMapper = new RacingGameMapper();
        racingGameService = new RacingGameService(
                numberGenerator,
                racingGameMapper,
                gameDao,
                carDao
        );
    }

    @Test
    void play_메서드는_자동차_경주를_진행한다() {
        // given
        final GamePlayRequestDto request = new GamePlayRequestDto(List.of("브리", "비버", "허브"), 1);
        given(gameDao.saveAndGetId(any())).willReturn(Optional.of(1));

        // when
        final GamePlayResponseDto result = racingGameService.play(request);

        // then
        assertAll(
                () -> assertThat(result.getWinners()).containsExactly("브리"),
                () -> assertThat(result.getRacingCars()).hasSize(3)
        );
    }

    @Test
    void findAll_메서드는_게임_결과를_반환한다() {
        // given
        List<CarEntity> carEntities = List.of(
                new CarEntity("car1", 5, true, 1),
                new CarEntity("car2", 5, true, 1),
                new CarEntity("car1", 5, true, 2),
                new CarEntity("car2", 5, true, 2),
                new CarEntity("car3", 2, false, 2)
        );
        given(carDao.findAll()).willReturn(carEntities);

        // when
        final List<GamePlayResponseDto> result = racingGameService.findAll();

        // then
        assertAll(
                () -> assertThat(result).hasSize(2),
                () -> assertThat(result.get(0).getRacingCars()).hasSize(2),
                () -> assertThat(result.get(0).getWinners()).hasSize(2),
                () -> assertThat(result.get(1).getRacingCars()).hasSize(3),
                () -> assertThat(result.get(1).getWinners()).hasSize(2)
        );
    }
}
