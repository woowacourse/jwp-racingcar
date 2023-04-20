package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import racingcar.Strategy.RandomNumberGenerator;
import racingcar.dao.PlayerResultDao;
import racingcar.dao.RacingResultDao;
import racingcar.model.Car;
import racingcar.model.Cars;
import racingcar.model.Trial;

@Service
public class RacingcarService {
    private final RacingResultDao racingResultDao;
    private final PlayerResultDao playerResultDao;

    public RacingcarService(final RacingResultDao racingResultDao, final PlayerResultDao playerResultDao) {
        this.racingResultDao = racingResultDao;
        this.playerResultDao = playerResultDao;
    }

    public RacingResponse move(final List<String> carNames, final int count) {
        Cars cars = Cars.from(carNames);
        Trial trial = new Trial(count);
        cars.move(trial.getTrial(), new RandomNumberGenerator());
        String winners = findWinners(cars);
        List<PlayerResult> playerResults = insertResult(count, cars, winners);
        return new RacingResponse(winners, playerResults);
    }

    private String findWinners(final Cars cars) {
        return cars.findWinners()
            .stream()
            .map(Car::getName)
            .collect(Collectors.joining(", "));
    }

    private List<PlayerResult> insertResult(int count, Cars cars, String winners) {
        RacingResult racingResult = racingResultDao.insertRacingResult(new RacingResult(winners, count));
        List<PlayerResult> playerResults = getPlayerResults(cars, racingResult);
        playerResults.forEach(playerResultDao::insertPlayer);
        return playerResults;
    }

    private static List<PlayerResult> getPlayerResults(Cars cars, RacingResult racingResult) {
        return cars.getCars()
            .stream()
            .map(car -> new PlayerResult(racingResult.getId(), car.getName(), car.getPosition()))
            .collect(Collectors.toList());
    }

    public List<RacingResponse> allResults() {
        List<RacingResult> racingResults = racingResultDao.selectAllResults();
        return getRacingResponses(racingResults);
    }

    private List<RacingResponse> getRacingResponses(List<RacingResult> racingResults) {
        List<RacingResponse> racingResponses = new ArrayList<>();
        for (RacingResult racingResult : racingResults) {
            int playResultId = racingResult.getId();
            List<PlayerResult> playerResults = playerResultDao.selectPlayerResultByRacingResultId(playResultId);
            racingResponses.add(new RacingResponse(racingResult.getWinners(), playerResults));
        }
        return racingResponses;
    }
}
