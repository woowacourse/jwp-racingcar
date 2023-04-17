package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.GameResultDao;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.dto.GameResultResponseDto;
import racingcar.entity.GameResult;
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
		moveCars(cars, tryCount);
		List<GameResult> gameResults = cars.getCars().stream()
				.map((car -> {
					return new GameResult(car.getCarName(), car.getDistance(),
							cars.isWinner(car));
				}))
				.collect(Collectors.toList());
		gameResultDao.saveGame(gameResults, tryCount);
		return GameResultResponseDto.toDto(cars.getWinnerNames(), cars);
	}

	private void moveCars (final Cars cars, final TryCount tryCount) {
		for (int i = 0; i < tryCount.getTryCount(); i++) {
			cars.moveAll(randomPowerGenerator);
		}
	}
}
