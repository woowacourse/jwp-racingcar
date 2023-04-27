package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.CarDao;
import racingcar.dao.GameResultDao;
import racingcar.domain.*;
import racingcar.dto.CarDto;
import racingcar.dto.ResultDto;
import racingcar.dto.UserInputDto;
import racingcar.entity.CarEntity;
import racingcar.entity.GameResultEntity;
import racingcar.utils.DefaultMovingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RacingGameService {

    private final CarDao carDao;
    private final GameResultDao gameResultDao;

    public RacingGameService(CarDao carDao, GameResultDao gameResultDao) {
        this.carDao = carDao;
        this.gameResultDao = gameResultDao;
    }

    @Transactional
    public Long addGameResultAndCars(UserInputDto inputDto) {
        RacingGame racingGame = getRacingGame(inputDto);
        TryCount tryCount = new TryCount(inputDto.getCount());

        Long gameResultId = gameResultDao.insert(new GameResultEntity(tryCount.getCount()));

        List<Car> results = getResults(racingGame).getCars();
        List<Car> winners = racingGame.winners(results);

        List<CarEntity> finalResults = results.stream()
                .map(car -> new CarEntity(car.getName(), car.getPosition(), winners.contains(car), gameResultId))
                .collect(Collectors.toList());

        carDao.insert(finalResults);
        return gameResultId;
    }

    private RacingGame getRacingGame(UserInputDto inputDto) {
        String names = inputDto.getNames();
        List<String> splitNames = List.of(names.split(","));
        List<Name> nameList = splitNames.stream()
                .map(Name::of)
                .collect(Collectors.toList());
        TryCount tryCount = new TryCount(inputDto.getCount());

        return new RacingGame(nameList, tryCount);
    }

    private Cars getResults(RacingGame racingGame) {
        return racingGame.start(new DefaultMovingStrategy());
    }

    @Transactional(readOnly = true)
    public ResultDto findResults(Long gameResultId) {
        return makeResultDtoByGameResultId(gameResultId);
    }

    @Transactional(readOnly = true)
    public List<ResultDto> findHistories() {
        List<ResultDto> maps = new ArrayList<>();
        List<Map<Long, GameResultEntity>> gameResults = gameResultDao.findAll();
        for (Map<Long, GameResultEntity> gameResult : gameResults) {
            Long id = new ArrayList<>(gameResult.keySet()).get(0);
            maps.add(makeResultDtoByGameResultId(id));
        }
        return maps;
    }

    private ResultDto makeResultDtoByGameResultId(Long gameResultId){
        List<CarEntity> finalResultEntity = carDao.findByGameResultId(gameResultId);
        List<CarEntity> winnersEntity = finalResultEntity.stream()
                .filter(CarEntity::isWinner)
                .collect(Collectors.toList());

        List<String> winners = winnersEntity.stream()
                .map(CarEntity::getPlayerName)
                .collect(Collectors.toList());
        List<CarDto> finalResult = finalResultEntity.stream()
                .map(result -> new CarDto(result.getPlayerName(), result.getFinalPosition()))
                .collect(Collectors.toList());
        return new ResultDto(winners, finalResult);
    }
}
