package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.CarResultDao;
import racingcar.dao.GameResultDao;
import racingcar.dao.JdbcWinnerDao;
import racingcar.domain.*;
import racingcar.dto.request.GameRequest;
import racingcar.dto.response.CarResponse;
import racingcar.dto.response.GameResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RacingGameService {
    private final CarResultDao carResultDao;
    private final GameResultDao gameResultDao;
    private final JdbcWinnerDao winnerDao;

    public RacingGameService(CarResultDao carResultDao, GameResultDao gameResultDao, JdbcWinnerDao winnerDao) {
        this.carResultDao = carResultDao;
        this.gameResultDao = gameResultDao;
        this.winnerDao = winnerDao;
    }

    public GameResponse playGame(GameRequest gameRequest) {
        RacingGame racingGame = createRacingGame(gameRequest);
        racingGame.play();
        saveResult(racingGame, gameRequest.getCount());
        return GameResponse.of(racingGame.getWinnerNames(), getCarResponsesByCars(racingGame.getCars()));
    }

    private RacingGame createRacingGame(GameRequest gameRequest) {
        Cars cars = Cars.fromNameValues(gameRequest.getNames());
        TryCount tryCount = new TryCount(gameRequest.getCount());
        return new RacingGame(new RandomNumberGenerator(), cars, tryCount);
    }

    private void saveResult(RacingGame racingGame, int tryCount) {
        long playResultId = savePlayResult(tryCount, racingGame);
        saveWinners(racingGame.getWinnerNames(), playResultId);
        saveCarResults(racingGame.getCars(), playResultId);
    }

    private long savePlayResult(int tryCount, RacingGame racingGame) {
        return gameResultDao.save(new GameResult(tryCount, racingGame.getCreatedAt()));
    }

    private void saveWinners(List<String> winnerNames, long playResultId) {
        List<Winner> winners = winnerNames.stream()
                .map(name -> new Winner(name, playResultId))
                .collect(Collectors.toList());
        winnerDao.saveAll(winners);
    }

    private void saveCarResults(List<Car> cars, long playResultId) {
        List<CarResult> carResults = cars.stream()
                .map(car -> new CarResult(playResultId, car.getName(), car.getPosition()))
                .collect(Collectors.toList());
        carResultDao.saveAll(carResults);
    }

    private static List<CarResponse> getCarResponsesByCars(List<Car> cars) {
        return cars.stream()
                .map(CarResponse::fromCar)
                .collect(Collectors.toList());
    }

    private static List<CarResponse> getCarResponsesByCarResults(List<CarResult> carResults) {
        List<CarResponse> carResultResponse = carResults.stream()
                .map(CarResponse::fromCarResult)
                .collect(Collectors.toList());
        return carResultResponse;
    }

    @Transactional(readOnly = true)
    public List<GameResponse> findAllCarGame() {
        List<GameResponse> gameResultResponse = new ArrayList<>();
        for (GameResult gameResult : gameResultDao.findAll()) {
            List<CarResult> carResults = carResultDao.findAllByPlayResultId(gameResult.getId());
            List<CarResponse> carResponses = getCarResponsesByCarResults(carResults);
            List<String> winners = winnerDao.findNamesByGameResultId(gameResult.getId());
            gameResultResponse.add(GameResponse.of(winners, carResponses));
        }
        return gameResultResponse;
    }
}
