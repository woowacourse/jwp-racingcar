package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.CarResultDao;
import racingcar.dao.GameResultDao;
import racingcar.domain.*;
import racingcar.dto.request.CarGameRequest;
import racingcar.dto.response.GameResponse;
import racingcar.dto.response.CarResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingGameService {
    private final CarResultDao carResultDao;
    private final GameResultDao gameResultDao;

    public RacingGameService(CarResultDao carResultDao, GameResultDao gameResultDao) {
        this.carResultDao = carResultDao;
        this.gameResultDao = gameResultDao;
    }

    public GameResponse play(CarGameRequest carGameRequest) {
        RacingGame racingGame = createRacingGame(carGameRequest);
        racingGame.play();
        saveResult(racingGame, carGameRequest.getCount());
        return GameResponse.of(racingGame.getWinnerNames(), racingGame.getCars());
    }

    private RacingGame createRacingGame(CarGameRequest carGameRequest) {
        Cars cars = Cars.from(carGameRequest.getNames());
        return new RacingGame(new CarRandomNumberGenerator(), cars, carGameRequest.getCount());
    }

    private void saveResult(RacingGame racingGame, int tryCount) {
        long playResultId = savePlayResult(tryCount, racingGame, racingGame.getWinnerNames());
        saveAllCarResult(racingGame.getCars(), playResultId);
    }

    private long savePlayResult(int tryCount, RacingGame racingGame, String winners) {
        return gameResultDao.save(new GameResult(tryCount, winners, racingGame.getCreatedAt()));
    }

    private void saveCarResult(List<Car> cars, long playResultId) {
        cars.stream()
                .map(car -> new CarResult(playResultId, car.getName(), car.getPosition()))
                .forEach(carResultDao::save);
    }

    private void saveAllCarResult(List<Car> cars, long playResultId) {
        List<CarResult> carResults = cars.stream()
                .map(car -> new CarResult(playResultId, car.getName(), car.getPosition()))
                .collect(Collectors.toList());
        carResultDao.saveAll(carResults);
    }

    public List<GameResponse> findAllCarGame() {
        List<GameResponse> gameResultResponse = new ArrayList<>();
        for (GameResult gameResult : gameResultDao.findAll()) {
            List<CarResult> carResults = carResultDao.findAllByPlayResultId(gameResult.getId());
            List<CarResponse> carResultResponse = carResults.stream()
                    .map(CarResponse::fromCarResult)
                    .collect(Collectors.toList());
            gameResultResponse.add(new GameResponse(gameResult.getWinners(), carResultResponse));
        }
        return gameResultResponse;
    }
}
