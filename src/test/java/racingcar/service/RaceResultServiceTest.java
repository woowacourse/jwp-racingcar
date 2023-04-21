package racingcar.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.dao.RaceResultDao;
import racingcar.domain.RacingCars;
import racingcar.domain.RacingGame;
import racingcar.entity.CarEntity;
import racingcar.entity.RaceResultEntity;
import racingcar.service.dto.CarStatusResponse;
import racingcar.service.dto.GameInfoRequest;
import racingcar.service.dto.RaceResultResponse;
import racingcar.service.mapper.RaceResultMapper;
import racingcar.util.NumberGenerator;
import racingcar.util.RandomNumberGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("RaceResultService Unit Test")
class RaceResultServiceTest {

    private RaceResultMapper raceResultMapper;
    private CarService carService;
    private RaceResultDao raceResultDao;
    private NumberGenerator numberGenerator;

    private RaceResultService raceResultService;

    @BeforeEach
    void dataInit() {
        carService = mock(CarService.class);
        raceResultMapper = mock(RaceResultMapper.class);
        raceResultDao = mock(RaceResultDao.class);
        numberGenerator = new RandomNumberGenerator();

        raceResultService =
                new RaceResultService(
                        raceResultDao, carService,
                        numberGenerator, raceResultMapper
                );
    }

    @Test
    @DisplayName("createRaceResult() : 게임 정보를 통해 새로운 게임을 만들 수 있다.")
    void test_createRaceResult() throws Exception {
        //given
        final GameInfoRequest gameInfoRequest = new GameInfoRequest("a,b,c,d", 4);
        final int trialCount = gameInfoRequest.getCount();

        final RaceResultEntity raceResultEntity =
                new RaceResultEntity(trialCount, "a,b,c", LocalDateTime.now());

        final List<CarStatusResponse> carStatusResponses =
                List.of(new CarStatusResponse("a", 1),
                        new CarStatusResponse("b", 1),
                        new CarStatusResponse("c", 1),
                        new CarStatusResponse("d", 0));

        //when
        when(raceResultMapper.mapToRaceResultEntity(any()))
                .thenReturn(raceResultEntity);

        when(raceResultDao.save(any()))
                .thenReturn(1);

        doNothing().when(carService)
                   .registerCars(any(), anyInt());

        when(raceResultMapper.mapToCarStatusResponseFrom((RacingGame) any()))
                .thenReturn(carStatusResponses);

        final RaceResultResponse raceResult = raceResultService.createRaceResult(gameInfoRequest);

        //then
        final List<CarStatusResponse> carStatusResult = raceResult.getRacingCars();

        assertAll(
                () -> assertEquals(raceResult.getWinners(), "a,b,c"),
                () -> assertThat(carStatusResult).hasSize(4),
                () -> assertThat(carStatusResult).extracting("name")
                                                 .containsExactly("a", "b", "c", "d"),
                () -> assertThat(carStatusResult).extracting("position")
                                                 .containsExactly(1, 1, 1, 0)
        );
    }

    @Test
    @DisplayName("searchRaceResult() : 모든 경기 결과를 조회할 수 있다.")
    void test_searchRaceResult() throws Exception {
        //given
        final List<CarEntity> cars =
                List.of(new CarEntity("a", 2, 1, LocalDateTime.now()),
                        new CarEntity("b", 3, 1, LocalDateTime.now()),
                        new CarEntity("c", 1, 1, LocalDateTime.now()),
                        new CarEntity("d", 4, 1, LocalDateTime.now()));

        final Map<String, List<CarEntity>> allRaceResult = Map.of("d", cars);

        final List<CarStatusResponse> carStatusResponses = List.of(new CarStatusResponse("a", 2),
                                                                   new CarStatusResponse("b", 3),
                                                                   new CarStatusResponse("c", 1),
                                                                   new CarStatusResponse("d", 4));

        when(raceResultDao.findAllRaceResult())
                .thenReturn(allRaceResult);

        when(raceResultMapper.mapToCarStatusResponseFrom(cars))
                .thenReturn(carStatusResponses);

        final List<RaceResultResponse> raceResultResponses = raceResultService.searchRaceResult();

        //then
        final RaceResultResponse response = raceResultResponses.get(0);

        final Map<String, Integer> responseMap = response.getRacingCars()
                                                         .stream()
                                                         .collect(Collectors.toMap(CarStatusResponse::getName,
                                                                                   CarStatusResponse::getPosition));

        assertAll(
                () -> assertEquals(response.getWinners(), "d"),
                () -> assertThat(response.getRacingCars()).hasSize(4),
                () -> assertThat(responseMap).containsKeys("a", "b", "c", "d")
                                             .containsValues(2, 3, 1, 4)
        );
    }
}
