package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.gameresult.GameRowDao;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.dto.CarStatusResponseDto;
import racingcar.dto.GameResultResponseDto;
import racingcar.entity.GameRow;
import racingcar.entity.PlayerRow;
import racingcar.utils.RandomPowerGenerator;
import racingcar.utils.RandomPowerMaker;

@Service
public class RacingCarService {

	private final RandomPowerGenerator randomPowerGenerator;
	private final GameRowDao gameRowDao;

	public RacingCarService (final GameRowDao gameRowDao) {
		this.randomPowerGenerator = new RandomPowerMaker();
		this.gameRowDao = gameRowDao;
	}

	@Transactional
	public GameResultResponseDto startRace (final List<String> carNames, final TryCount tryCount) {
		Cars cars = Cars.createByNames(carNames);
		for (int i = 0; i < tryCount.getTryCount(); i++) {
			cars.moveAll(randomPowerGenerator);
		}
		List<PlayerRow> playerRows = toPlayerResults(cars);
		gameRowDao.saveGame(playerRows, tryCount);

		return GameResultResponseDto.toDto(cars.getWinnerNames(), cars);
	}

	private List<PlayerRow> toPlayerResults (Cars cars) {
		return cars.getCars().stream()
				.map((car -> {
					return new PlayerRow(car.getCarName(), car.getDistance(),
							cars.isWinner(car));
				}))
				.collect(Collectors.toList());
	}

	public List<GameResultResponseDto> findAllGameResults () {
		List<GameRow> gameRows = gameRowDao.fetchAllGameRow();
		List<GameResultResponseDto> gameResultResponseDtos = new ArrayList<>();
		for (final GameRow gameRow : gameRows) {
			gameResultResponseDtos.add(gameResultInOneGame(gameRow));
		}

		return gameResultResponseDtos;
	}

	private GameResultResponseDto gameResultInOneGame (final GameRow gameRow) {
		List<PlayerRow> playerRows = gameRowDao.fetchAllPlayerResultByGameId(gameRow.getId());
		List<String> winners = new ArrayList<>();
		List<CarStatusResponseDto> carStatusResponseDtos = new ArrayList<>();
		for (final PlayerRow playerRow : playerRows) {
			if (playerRow.isWinner()) {
				winners.add(playerRow.getName());
			}
			carStatusResponseDtos.add(CarStatusResponseDto.toDto(playerRow));
		}

		return GameResultResponseDto.toDto(winners, carStatusResponseDtos);
	}
}
