package racingcar.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.dao.PlayerDao;
import racingcar.dao.RaceDao;
import racingcar.dao.WinnerDao;
import racingcar.domain.Car;
import racingcar.dto.CarDto;
import racingcar.dto.GameInputDto;
import racingcar.dto.RacingResultResponseDto;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class RacingGameServiceImplTest {
    @Mock
    private RaceDao raceDao;
    @Mock
    private PlayerDao playerDao;
    @Mock
    private WinnerDao winnerDao;
    private RacingGameService racingGameService;
    
    @BeforeEach
    void setUp() {
        racingGameService = new RacingGameServiceImpl(raceDao, playerDao, winnerDao);
    }
    
    @Test
    void 게임_진행_후_결과를_반환한다() {
        GameInputDto gameInputDto = new GameInputDto("스플릿,아벨,포비", "12");
        final RacingResultResponseDto racingResultResponseDto = racingGameService.playGameWithoutPrint(gameInputDto, () -> 3);
        
        assertAll(
                () -> assertThat(racingResultResponseDto.getWinners()).isEqualTo("스플릿,아벨,포비"),
                () -> assertThat(racingResultResponseDto.getRacingCars())
                        .containsExactly(
                                new Car("스플릿", 0),
                                new Car("아벨", 0),
                                new Car("포비", 0)
                        )
        );
    }
    
    @Test
    void 모든_게임_결과를_반환한다() {
        List<CarDto> carDtos = List.of(
                new CarDto(new Car("아벨", 0)),
                new CarDto(new Car("스플릿", 0)),
                new CarDto(new Car("포비", 0))
        );
        given(raceDao.findAllIds()).willReturn(List.of(1L));
        given(playerDao.findByIds(anyList())).willReturn(List.of(new CarDto(new Car("포비", 0))));
        given(playerDao.findByRaceIds(anyLong())).willReturn(carDtos);

        List<RacingResultResponseDto> allGameResult = racingGameService.findAllGameResult();
        List<String> winners = allGameResult.stream()
                .map(RacingResultResponseDto::getWinners)
                .collect(Collectors.toUnmodifiableList());

        List<String> carNames = allGameResult.stream()
                .map(RacingResultResponseDto::getRacingCars)
                .map(this::parseCarNames)
                .collect(Collectors.toUnmodifiableList());

        assertAll(
                () -> assertThat(winners).containsExactly("포비"),
                () -> assertThat(carNames).containsExactly("아벨,스플릿,포비")
        );
    }
    
    private String parseCarNames(List<Car> cars) {
        return cars.stream()
                .map(Car::getName)
                .collect(Collectors.joining(","));
    }
}