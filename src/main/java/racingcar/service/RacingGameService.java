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
        int playResultId = savePlayResult(tryCount, racingGame, racingGame.getWinnerNames());
        saveCarResult(racingGame.getCars(), playResultId);
    }

    private int savePlayResult(int tryCount, RacingGame racingGame, String winners) {
        return gameResultDao.save(GameResult.of(tryCount, winners, racingGame.getCreatedAt()));
    }

    private void saveCarResult(List<Car> cars, int playResultId) {
        cars.stream()
                .map(car -> CarResult.of(playResultId, car.getName(), car.getPosition()))
                .forEach(carResultDao::save);
    }

    public List<GameResponse> findAllCarGame() {
        List<GameResponse> gameResultRespons = new ArrayList<>();
        for (GameResult gameResult : gameResultDao.findAll()) {
            List<CarResult> carResults = carResultDao.findAllByPlayResultId(gameResult.getId());
            List<CarResponse> carResultRespons = carResults.stream()
                    .map(CarResponse::fromCarResult)
                    .collect(Collectors.toList());
            gameResultRespons.add(new GameResponse(gameResult.getWinners(), carResultRespons));
        }
        return gameResultRespons;
    }
}
