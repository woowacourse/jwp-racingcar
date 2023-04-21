package racing.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racing.controller.dto.response.RacingGameResultResponse;
import racing.dao.CarDao;
import racing.dao.CarEntity;
import racing.dao.GameDao;
import racing.domain.Car;
import racing.domain.Cars;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class RacingGameServiceTest {

    private GameDao gameDao;
    private CarDao carDao;

    @Autowired
    private RacingGameService racingGameService;

    private final Cars cars = new Cars(List.of(new Car("bebe"), new Car("royce"), new Car("dd")));

    @BeforeEach
    void setUp() {
        gameDao = Mockito.spy(GameDao.class);
        carDao = Mockito.spy(CarDao.class);
        racingGameService = new RacingGameService(gameDao, carDao);
    }

    @DisplayName("자동차가 생성되어야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"bebe,royce,dd"})
    void createCars(String names) {
        // when, then
        Assertions.assertThat(racingGameService.createCars(names).getCars().size()).isEqualTo(3);
    }

    @DisplayName("게임을 생성해야 한다.")
    @ParameterizedTest
    @ValueSource(ints = {7})
    void createRacingGame(int count) {
        when(gameDao.saveGame(count)).thenReturn(1L);

        // when, then
        Assertions.assertThat(racingGameService.createRacingGame(count)).isEqualTo(1);
    }

    @DisplayName("자동차를 움직일 수 있어야 한다.")
    @Test
    void move() {
        // when
        racingGameService.move(6, cars.getCar(0));
        racingGameService.move(5, cars.getCar(0));
        racingGameService.move(7, cars.getCar(0));

        // then
        Assertions.assertThat(cars.getCars().get(0).getPosition()).isEqualTo(3);
    }

    @DisplayName("RacingGameResultResponse List를 반환해야 한다.")
    @Test
    void getRacingGameResultResponse() {
        // given
        racingGameService.createCars("bebe,dd");
        when(carDao.findAll()).thenReturn(List.of(new CarEntity("bebe", 5, true, 1L), new CarEntity("dd", 4, false, 1L)));

        // when
        List<RacingGameResultResponse> racingGameResultResponse = racingGameService.getRacingGameResultResponse();

        // then
        assertAll(
                () -> Assertions.assertThat(racingGameResultResponse).hasSize(1),
                () -> Assertions.assertThat(racingGameResultResponse.get(0).getRacingCars()).hasSize(2),
                () -> Assertions.assertThat(racingGameResultResponse.get(0).getWinners()).hasSize(1)
        );
    }


}
