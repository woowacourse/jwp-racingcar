package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.GameResultDao;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.dto.CarStatusResponseDto;
import racingcar.dto.GameResultResponseDto;
import racingcar.entity.GameResult;
import racingcar.entity.PlayerResult;
import racingcar.utils.RandomPowerGenerator;
import racingcar.utils.RandomPowerMaker;

@Service
public class RacingCarService {

	private final RandomPowerGenerator randomPowerGenerator;
	private final GameResultDao gameResultDao;

	public RacingCarService (final GameResultDao gameResultDao) {
		this.randomPowerGenerator = new RandomPowerMaker();
		this.gameResultDao = gameResultDao;
	}

	@Transactional
	public GameResultResponseDto startRace (final List<String> carNames, final TryCount tryCount) {
		Cars cars = Cars.createByNames(carNames);
		for (int i = 0; i < tryCount.getTryCount(); i++) {
			cars.moveAll(randomPowerGenerator);
		}
		List<PlayerResult> playerResults = toPlayerResults(cars);
		gameResultDao.saveGame(playerResults, tryCount);

		return GameResultResponseDto.toDto(cars.getWinnerNames(), cars);
	}

	private List<PlayerResult> toPlayerResults (Cars cars) {
		return cars.getCars().stream()
				.map((car -> {
					return new PlayerResult(car.getCarName(), car.getDistance(),
							cars.isWinner(car));
				}))
				.collect(Collectors.toList());
	}

	public List<GameResultResponseDto> findAllGameResults () {
		List<GameResult> gameResults = gameResultDao.fetchAllGameResult();
		List<GameResultResponseDto> gameResultResponseDtos = new ArrayList<>();
		for (final GameResult gameResult : gameResults) {
			gameResultResponseDtos.add(gameResultInOneGame(gameResult));
		}

		return gameResultResponseDtos;
	}

	private GameResultResponseDto gameResultInOneGame (final GameResult gameResult) {
		List<PlayerResult> playerResults = gameResultDao.fetchAllPlayerResultByGameId(gameResult.getId());
		List<String> winners = new ArrayList<>();
		List<CarStatusResponseDto> carStatusResponseDtos = new ArrayList<>();
		for (final PlayerResult playerResult : playerResults) {
			if (playerResult.isWinner()) {
				winners.add(playerResult.getName());
			}
			carStatusResponseDtos.add(CarStatusResponseDto.toDto(playerResult));
		}

		return GameResultResponseDto.toDto(winners, carStatusResponseDtos);
	}
}
