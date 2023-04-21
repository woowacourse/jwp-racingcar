package racingcar.service;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import racingcar.dao.GameDao;
import racingcar.dao.PlayerDao;
import racingcar.domain.Car;
import racingcar.domain.NumberGenerator;
import racingcar.dto.GameRequestDto;
import racingcar.dto.GameResponseDto;
import racingcar.entity.GameId;
import racingcar.entity.PlayerEntity;
import racingcar.utils.TestNumberGenerator;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class RacingGameServiceTest {

    private GameDao gameDao;
    private PlayerDao playerDao;
    private RacingGameService racingGameService;

    @BeforeEach
    void setUp() {
        gameDao = Mockito.mock(GameDao.class);
        playerDao = Mockito.mock(PlayerDao.class);
        final NumberGenerator numberGenerator = new TestNumberGenerator(Lists.newArrayList(4, 3, 3));
        racingGameService = new RacingGameService(
                numberGenerator,
                gameDao,
                playerDao
        );
    }

    @Test
    void 자동차_경주를_진행한다() {
        // given
        final GameRequestDto request = new GameRequestDto(List.of("브리", "비버", "허브"), 1);
        given(gameDao.saveAndGetGameId(any())).willReturn(new GameId(1));

        // when
        final GameResponseDto result = racingGameService.play(request);

        // then
        assertAll(
                () -> assertThat(result.getWinners()).containsExactly("브리"),
                () -> assertThat(result.getRacingCars()).hasSize(3)
        );
    }

    @Test
    void 자동차_결과를_반환한다() {
        //given
        List<PlayerEntity> players = Arrays.asList(
                new PlayerEntity(new Car("car1"), true, 1),
                new PlayerEntity(new Car("car2"), false, 1),
                new PlayerEntity(new Car("car3"), false, 1),
                new PlayerEntity(new Car("car4"), true, 1)
        );


        given(playerDao.findAll()).willReturn(players);

        //when
        List<GameResponseDto> result = racingGameService.findAll();

        //then
        assertAll(
                () -> assertThat(result).hasSize(1),
                () -> assertThat(result.get(0).getRacingCars()).hasSize(4),
                () -> assertThat(result.get(0).getWinners()).hasSize(2)
        );
    }
}
