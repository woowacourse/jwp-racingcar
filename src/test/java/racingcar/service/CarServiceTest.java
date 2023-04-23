package racingcar.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import racingcar.dao.GameDao;
import racingcar.dao.PlayerDao;
import racingcar.domain.TestNumberGenerator;
import racingcar.dto.CarDto;
import racingcar.dto.GamePlayResponseDto;
import racingcar.strategy.RacingNumberGenerator;

@SpringBootTest
class CarServiceTest {

	@Autowired
	private PlayerDao playerDao;

	@Autowired
	private GameDao gameDao;

	private CarService carService;

	@BeforeEach
	void setUp() {
		final RacingNumberGenerator numberGenerator = new TestNumberGenerator(List.of(9, 6, 3, 0));
		carService = new CarService(playerDao, gameDao, numberGenerator);
	}

	@Test
	void playGameTest() {
		//given
		final List<String> carNames = List.of("준팍", "무민", "빙봉", "검프");

		//when
		final GamePlayResponseDto gamePlayResponseDto = carService.playGame(carNames, 1);
		final String winners = gamePlayResponseDto.getWinners();
		final List<CarDto> racingCars = gamePlayResponseDto.getRacingCars();

		//then
		assertThat(winners).isEqualTo(String.join(",", carNames.get(0), carNames.get(1)));
		assertThat(racingCars).hasSize(4);
	}

	@Test
	void findGamePlayHistoryAll() {
		//given
		final List<String> carNames = List.of("준팍", "무민", "빙봉", "검프");
		carService.playGame(carNames, 1);

		//when
		final List<GamePlayResponseDto> gamePlayHistoryAll = carService.findGamePlayHistoryAll();
		final String winners = gamePlayHistoryAll.get(0).getWinners();
		final List<CarDto> racingCars = gamePlayHistoryAll.get(0).getRacingCars();

		//then
		assertThat(winners).isEqualTo(String.join(",", carNames.get(0), carNames.get(1)));
		assertThat(racingCars).hasSize(carNames.size());
	}
}
