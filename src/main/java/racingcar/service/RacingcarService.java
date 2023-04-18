package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingcar.controller.RacingResponse;
import racingcar.dao.PlayResult;
import racingcar.dao.PlayResultDaoImpl;
import racingcar.dao.PlayerResult;
import racingcar.dao.PlayerResultDaoImpl;
import racingcar.model.Car;

@Service
public class RacingcarService {

    private static final int MINIMUM_PARTICIPANT = 2;

    private final PlayResultDaoImpl playResultDaoImpl;
    private final PlayerResultDaoImpl playerResultDaoImpl;

    public RacingcarService(final PlayResultDaoImpl playResultDaoImpl, final PlayerResultDaoImpl playerResultDaoImpl) {
        this.playResultDaoImpl = playResultDaoImpl;
        this.playerResultDaoImpl = playerResultDaoImpl;
    }

    public RacingResponse move(final List<String> carNames, final int count) {
        List<Car> cars = getCars(carNames);

        for (int i = 1; i <= count; i++) {
            moveAllCars(cars);
        }

        String winners = findWinners(cars);

        int playResultId = playResultDaoImpl.insertPlayResult(new PlayResult(winners, count));
        List<PlayerResult> playerResults = insertPlayerResults(cars, playResultId);
        return new RacingResponse(winners, playerResults);
    }

    private List<PlayerResult> insertPlayerResults(final List<Car> cars, final int playResultId) {
        List<PlayerResult> playerResults = cars.stream()
                .map(car -> new PlayerResult(playResultId, car.getName(), car.getPosition()))
                .collect(Collectors.toList());

        playerResults.forEach(playerResultDaoImpl::insertPlayerResult);
        return playerResults;
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
        int winnerPosition = findPosition(cars);

        List<Car> winnersCars = cars.stream()
                .filter(car -> car.isPosition(winnerPosition))
                .collect(Collectors.toUnmodifiableList());

        return winnersCars.stream()
                .map(Car::getName)
                .collect(Collectors.joining(", "));
    }

    private int findPosition(final List<Car> cars) {
        int maxPosition = 0;

        for (Car car : cars) {
            maxPosition = car.findGreaterPosition(maxPosition);
        }

        return maxPosition;
    }

    public List<RacingResponse> allResults() {
        List<PlayResult> playResults = playResultDaoImpl.selectAllResults();
        List<List<PlayerResult>> playerResults = selectPlayerResultListsByPlayResultIds(playResults);

        List<RacingResponse> racingResponses = new ArrayList<>();
        for (int i = 0; i < playerResults.size(); i++) {
            racingResponses.add(new RacingResponse(playResults.get(i).getWinners(), playerResults.get(i)));
        }
        return racingResponses;
    }

    private List<List<PlayerResult>> selectPlayerResultListsByPlayResultIds(final List<PlayResult> playResults) {
        List<List<PlayerResult>> playerResults = new ArrayList<>();
        for (PlayResult playResult : playResults) {
            int playResultId = playResult.getId();
            playerResults.add(playerResultDaoImpl.selectPlayerResult(playResultId));
        }
        return playerResults;
    }
}
