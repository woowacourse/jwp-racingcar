package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dao.GameResultDAO;
import racingcar.dao.PlayerResultDAO;
import racingcar.dao.entity.GameResultEntity;
import racingcar.dao.entity.PlayerResultEntity;
import racingcar.domain.*;
import racingcar.dto.CarDto;
import racingcar.dto.GameResultDto;
import racingcar.dto.PlayerResultDto;
import racingcar.dto.response.GameResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingGameService {
    private final GameResultDAO gameResultDAO;
    private final PlayerResultDAO playerResultDAO;
    private final RacingGameManager racingGameManager = RacingGameManager.getInstance();

    @Autowired
    public RacingGameService(GameResultDAO gameResultDAO, PlayerResultDAO playerResultDAO) {
        this.gameResultDAO = gameResultDAO;
        this.playerResultDAO = playerResultDAO;
    }

    public GameResponseDto play(Names names, TryCount tryCount) {
        Cars cars = Cars.createByNames(names);

        racingGameManager.play(cars, tryCount);

        Cars winningCars = racingGameManager.decideWinners(cars);
        String winners = convertToString(winningCars);
        List<CarDto> carDtos = CarDto.from(cars.getCars());

        int savedId = gameResultDAO.save(GameResultDto.from(tryCount.getCount()));
        playerResultDAO.saveAll(PlayerResultDto.of(carDtos, savedId));

        return new GameResponseDto(winners, carDtos);
    }

    private String convertToString(Cars winners) {
        return winners.getCars().stream()
                .map(Car::getName)
                .collect(Collectors.joining(","));
    }

    public List<GameResponseDto> findAllGameAndPlayerResults() {
        List<GameResultEntity> gameResultEntities = gameResultDAO.findAll();
        List<PlayerResultEntity> playerResultEntities = playerResultDAO.findAll();

        List<Integer> gameIds = getGameIds(gameResultEntities);

        return gameIds.stream()
                .map(gameId -> getGameResponseDto(playerResultEntities, gameId))
                .collect(Collectors.toList());
    }

    private List<Integer> getGameIds(List<GameResultEntity> gameResultEntities) {
        return gameResultEntities.stream()
                .map(GameResultEntity::getId)
                .distinct()
                .collect(Collectors.toList());
    }

    private GameResponseDto getGameResponseDto(List<PlayerResultEntity> playerResultEntities, Integer gameId) {
        List<Car> carsOfGameId = playerResultEntities.stream()
                .filter(result -> result.getGameId() == gameId)
                .map(result -> Car.of(Name.from(result.getName()), Position.from(result.getPosition())))
                .collect(Collectors.toList());

        Cars winningCars = racingGameManager.decideWinners(Cars.from(carsOfGameId));
        String winners = convertToString(winningCars);

        List<CarDto> carDtos = carsOfGameId.stream()
                .map(car -> CarDto.of(car.getName(), car.getPosition()))
                .collect(Collectors.toList());

        return new GameResponseDto(winners, carDtos);
    }
}
