package racingcar.service;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import racingcar.dao.GameDao;
import racingcar.dao.PlayerDao;
import racingcar.dao.entity.GameEntity;
import racingcar.dao.entity.JoinEntity;
import racingcar.dao.entity.PlayerEntity;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.domain.vo.Name;
import racingcar.domain.vo.Position;
import racingcar.domain.vo.Round;
import racingcar.dto.CarDto;
import racingcar.dto.GamePlayResponseDto;
import racingcar.strategy.RacingNumberGenerator;

@Service
public class CarService {

	private final PlayerDao playerDao;
	private final GameDao gameDao;
	private final RacingNumberGenerator generator;

	public CarService(final PlayerDao playerDao, final GameDao gameDao, final RacingNumberGenerator generator) {
		this.playerDao = playerDao;
		this.gameDao = gameDao;
		this.generator = generator;
	}

	@Transactional
	public GamePlayResponseDto playGame(final List<String> carNames, final int tryCount) {
		final RacingGame racingGame = RacingGame.of(carNames, tryCount);
		racingGame.playGame(generator);

		return save(racingGame);
	}

	public List<GamePlayResponseDto> findGamePlayHistoryAll() {
		return getGameResultAll().stream()
			.flatMap(this::mapToGamePlayResponseDto)
			.collect(toList());
	}

	private GamePlayResponseDto save(final RacingGame racingGame) {
		final GameEntity gameEntity = getGameEntity(racingGame);
		final long id = gameDao.save(gameEntity);

		final List<PlayerEntity> playerEntities = getPlayerEntities(racingGame, id);
		playerDao.batchInsert(playerEntities);

		return new GamePlayResponseDto(racingGame.findWinnerNames(), mapToCarDto(racingGame.getCars()));
	}

	private GameEntity getGameEntity(final RacingGame racingGame) {
		final Round round = racingGame.getRound();

		return new GameEntity(null, racingGame.findWinnerNames(), round.getValue(), null);
	}

	private List<PlayerEntity> getPlayerEntities(final RacingGame racingGame, final long id) {
		return racingGame.getCars().stream()
			.map(car -> {
				final Name name = car.getName();
				final Position position = car.getPosition();
				return new PlayerEntity(null, id, name.getValue(), position.getValue());
			})
			.collect(toList());
	}

	private List<CarDto> mapToCarDto(final List<Car> cars) {
		return cars.stream()
			.map(car -> {
				final Name name = car.getName();
				final Position position = car.getPosition();
				return new CarDto(name.getValue(), position.getValue());
			})
			.collect(toList());
	}

	private List<Map<String, List<Car>>> getGameResultAll() {
		return new ArrayList<>(gameDao.findGamePlayHistoryAll().stream()
			.collect(groupingBy(JoinEntity::getGameId, getGameResult()))
			.values());
	}

	private Collector<JoinEntity, ?, Map<String, List<Car>>> getGameResult() {
		return groupingBy(JoinEntity::getWinners,
			mapping(joinEntity -> Car.of(joinEntity.getName(), joinEntity.getPosition()),
				toUnmodifiableList()));
	}

	private Stream<GamePlayResponseDto> mapToGamePlayResponseDto(final Map<String, List<Car>> gameResult) {
		return gameResult.keySet().stream()
			.map(winners ->
				new GamePlayResponseDto(winners, mapToCarDto(gameResult.get(winners))));
	}

}
