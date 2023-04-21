package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import racingcar.Strategy.RandomNumberGenerator;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.model.Cars;
import racingcar.model.RacingGame;
import racingcar.model.Trial;

@Service
public class RacingcarService {
    private final GameDao gameDao;
    private final CarDao carDao;

    public RacingcarService(final GameDao gameDao, final CarDao carDao) {
        this.gameDao = gameDao;
        this.carDao = carDao;
    }

    private static List<CarEntity> getPlayerResults(Cars cars, RacingResult racingResult) {
        return cars.getCars()
            .stream()
            .map(car -> new CarEntity(racingResult.getId(), car.getName(), car.getPosition()))
            .collect(Collectors.toList());
    }

    /*private String findWinners(final Cars cars) {
        return String.join(", ", cars.findWinners());
    }*/

    public RacingResponse play(final Cars cars, final Trial trial) {
        RacingGame racingGame = new RacingGame(cars, trial, new RandomNumberGenerator());
        racingGame.play();
        List<CarEntity> carEntities = insertResult(trial.getTrial(), cars, racingGame.winners());
        return new RacingResponse(racingGame.winners(), carEntities);
    }

    private List<CarEntity> insertResult(int count, Cars cars, String winners) {
        RacingResult racingResult = gameDao.insertRacingResult(new RacingResult(winners, count));
        List<CarEntity> carEntities = getPlayerResults(cars, racingResult);
        carEntities.forEach(carDao::insertPlayer);
        return carEntities;
    }

    public List<RacingResponse> allResults() {
        List<RacingResult> racingResults = gameDao.selectAllResults();
        return getRacingResponses(racingResults);
    }

    private List<RacingResponse> getRacingResponses(List<RacingResult> racingResults) {
        List<RacingResponse> racingResponses = new ArrayList<>();
        for (RacingResult racingResult : racingResults) {
            int playResultId = racingResult.getId();
            List<CarEntity> carEntities = carDao.selectPlayerResultByRacingResultId(playResultId);
            racingResponses.add(new RacingResponse(racingResult.getWinners(), carEntities));
        }
        return racingResponses;
    }
}
