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

        for (int i = 1; i <= count; i++) {
            moveAllCars(cars);
        }

        String winners = findWinners(cars);

        RacingResult racingResult = racingResultDao.insertPlayResult(new RacingResult(winners, count));

        List<PlayerResult> playerResults = cars.stream()
                .map(car -> new PlayerResult(racingResult.getId(), car.getName(), car.getPosition()))
                .collect(Collectors.toList());

        playerResults.forEach(playerResultDao::insertPlayer);
        return new RacingResponse(winners, playerResults);
    }

    private void moveAllCars(final List<Car> cars) {
        for (Car car : cars) {
            car.move(RandomMaker.random());
        }
    }

    private static List<Car> getCars(final List<String> carNames) {
        if (carNames.size() < MINIMUM_PARTICIPANT) {
            throw new IllegalArgumentException("[ERROR] 경주는 최소 " + MINIMUM_PARTICIPANT + "명이 필요해요.");
        }
        return CarFactory.makeCars(carNames);
    }

    private String findWinners(final List<Car> cars) {
        int winnerPosition = maxPosition(cars);

        List<Car> winnersCars = cars.stream()
                .filter(car -> car.isPosition(winnerPosition))
                .collect(Collectors.toUnmodifiableList());

        return winnersCars.stream()
                .map(Car::getName)
                .collect(Collectors.joining(", "));
    }

    private int maxPosition(final List<Car> cars) {
        int maxPosition = 0;

        for (Car car : cars) {
            maxPosition = car.findGreaterPosition(maxPosition);
        }

        return maxPosition;
    }

    public List<RacingResponse> allResults() {
        List<RacingResult> racingResults = racingResultDao.selectAllResults();
        List<List<PlayerResult>> playerResults = new ArrayList<>();
        for (RacingResult racingResult : racingResults) {
            int playResultId = racingResult.getId();
            playerResults.add(playerResultDao.selectPlayerResultByRacingResultId(playResultId));
        }

        List<RacingResponse> racingResponses = new ArrayList<>();
        for (int i = 0; i < playerResults.size(); i++) {
            racingResponses.add(new RacingResponse(racingResults.get(i).getWinners(), playerResults.get(i)));
        }
        return racingResponses;
    }
}
