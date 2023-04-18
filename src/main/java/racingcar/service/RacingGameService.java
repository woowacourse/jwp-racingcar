package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dao.GameResultDAO;
import racingcar.dao.PlayerResultDAO;
import racingcar.dao.entity.GameResultEntity;
import racingcar.dao.entity.PlayerResultEntity;
import racingcar.domain.Cars;
import racingcar.domain.Names;
import racingcar.domain.TryCount;
import racingcar.domain.engine.Engine;
import racingcar.domain.engine.RandomMovingEngine;
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

    @Autowired
    public RacingGameService(GameResultDAO gameResultDAO, PlayerResultDAO playerResultDAO) {
        this.gameResultDAO = gameResultDAO;
        this.playerResultDAO = playerResultDAO;
    }

    public GameResponseDto play(Names names, TryCount tryCount) {
        Cars cars = Cars.createByNames(names);

        moveCars(cars, tryCount);

        List<CarDto> carDtos = CarDto.from(cars.getCars());
        String winners = decideWinners(carDtos);

        int savedId = gameResultDAO.save(GameResultDto.from(tryCount.getCount()));
        playerResultDAO.saveAll(PlayerResultDto.of(carDtos, savedId));

        return new GameResponseDto(winners, carDtos);
    }

    private void moveCars(Cars cars, TryCount tryCount) {
        Engine engine = new RandomMovingEngine();

        for (int i = 0; i < tryCount.getCount(); i++) {
            cars.moveCars(engine);
        }
    }


    private String decideWinners(List<CarDto> carDtos) {
        int maxPosition = carDtos.stream()
                .map(CarDto::getPosition)
                .max(Integer::compareTo)
                .orElseThrow();

        return carDtos.stream()
                .filter(carDto -> carDto.getPosition() == maxPosition)
                .map(CarDto::getName)
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
        List<CarDto> carDtos = playerResultEntities.stream()
                .filter(result -> result.getGameId() == gameId)
                .map(result -> CarDto.of(result.getName(), result.getPosition()))
                .collect(Collectors.toList());
        String winners = decideWinners(carDtos);

        return new GameResponseDto(winners, carDtos);
    }
}
