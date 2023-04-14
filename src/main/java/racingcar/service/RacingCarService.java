package racingcar.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.GameResultDao;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.dto.GameResultResponseDto;
import racingcar.utils.CarsFactory;
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
		Cars cars = CarsFactory.createCars(carNames.toArray(new String[0]));
		moveCars(cars, tryCount);
		GameResultResponseDto gameResult = GameResultResponseDto.toDto(cars.getWinnerNames(), cars);

		gameResultDao.saveGame(tryCount, gameResult);
		return gameResult;
	}

	private void moveCars (final Cars cars, final TryCount tryCount) {
		for (int i = 0; i < tryCount.getTryCount(); i++) {
			cars.moveAll(randomPowerGenerator);
		}
	}
}
