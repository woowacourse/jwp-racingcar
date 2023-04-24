package racingcar.controller;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import racingcar.dao.GameDao;
import racingcar.dao.PlayerDao;
import racingcar.dto.CarDto;
import racingcar.dto.GamePlayRequestDto;
import racingcar.dto.GamePlayResponseDto;
import racingcar.service.CarService;
import racingcar.strategy.RacingRandomNumberGenerator;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebCarControllerTest {

	@Value("${local.server.port}")
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private CarService carService;

	@BeforeEach
	void setUp() {
		carService = new CarService(new PlayerDao(jdbcTemplate), new GameDao(jdbcTemplate),
			new RacingRandomNumberGenerator());
	}

	@AfterEach
	void tearDown() {
		jdbcTemplate.update("DELETE FROM PLAYER;");
		jdbcTemplate.update("DELETE FROM GAME;");
	}

	@Test
	void playsTest() {
		// given
		final GamePlayRequestDto gamePlayRequestDto = new GamePlayRequestDto("준팍, 무민", 1);
		final String url = String.format("http://localhost:%d/plays", port);

		// when
		final ResponseEntity<GamePlayResponseDto> responseEntity = restTemplate.postForEntity(url, gamePlayRequestDto,
			GamePlayResponseDto.class);

		// then
		final List<GamePlayResponseDto> gamePlayHistoryAll = carService.findGamePlayHistoryAll();
		int size = gamePlayHistoryAll.get(0).getRacingCars().size();
		List<String> racingCars = gamePlayHistoryAll.get(0)
			.getRacingCars()
			.stream()
			.map(CarDto::getName)
			.collect(Collectors.toList());

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(size).isEqualTo(2);
		assertThat(racingCars).isEqualTo(List.of("준팍", "무민"));
	}
}
