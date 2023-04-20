package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import racingcar.dao.PlayerResultDao;
import racingcar.dao.RacingResultDao;
import racingcar.model.Car;

@Service
public class RacingcarService {

    private static final int MINIMUM_PARTICIPANT = 2;

    private final RacingResultDao racingResultDao;
    private final PlayerResultDao playerResultDao;

    public RacingcarService(final RacingResultDao racingResultDao, final PlayerResultDao playerResultDao) {
        this.racingResultDao = racingResultDao;
        this.playerResultDao = playerResultDao;
    }

    public RacingResponse move(final List<String> carNames, final int count) {
        List<Car> cars = getCars(carNames);
        moveAllCars(cars, count);
        String winners = findWinners(cars);
        List<PlayerResult> playerResults = insertResult(count, cars, winners);
        return new RacingResponse(winners, playerResults);
    }

    private static List<Car> getCars(final List<String> carNames) {
        if (carNames.size() < MINIMUM_PARTICIPANT) {
            throw new IllegalArgumentException("[ERROR] 경주는 최소 " + MINIMUM_PARTICIPANT + "명이 필요해요.");
        }
        return CarFactory.makeCars(carNames);
    }

    private void moveAllCars(final List<Car> cars, int count) {
        for (int i = 1; i <= count; i++) {
            cars.forEach(car -> car.move(RandomMaker.random()));
        }
    }

    private String findWinners(final List<Car> cars) {
        int winnerPosition = maxPosition(cars);
        return cars.stream()
            .filter(car -> car.isMaxPosition(winnerPosition))
            .map(Car::getName)
            .collect(Collectors.joining(", "));
    }

    private List<PlayerResult> insertResult(int count, List<Car> cars, String winners) {
        RacingResult racingResult = racingResultDao.insertRacingResult(new RacingResult(winners, count));
        List<PlayerResult> playerResults = getPlayerResults(cars, racingResult);
        playerResults.forEach(playerResultDao::insertPlayer);
        return playerResults;
    }

    private static List<PlayerResult> getPlayerResults(List<Car> cars, RacingResult racingResult) {
        return cars.stream()
            .map(car -> new PlayerResult(racingResult.getId(), car.getName(), car.getPosition()))
            .collect(Collectors.toList());
    }

    private int maxPosition(final List<Car> cars) {
        int maxPosition = 0;
        for (Car car : cars) {
            maxPosition = car.findHigherPosition(maxPosition);
        }
        return maxPosition;
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
