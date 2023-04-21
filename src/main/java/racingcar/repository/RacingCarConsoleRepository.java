package racingcar.repository;

import racingcar.domain.Car;
import racingcar.dto.RacingCarDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RacingCarConsoleRepository implements RacingCarRepository {
    private Integer gameId;
    private final Map<Integer, List<RacingCarDto>> cars;
    private final Map<Integer, List<String>> winners;

    public RacingCarConsoleRepository() {
        this.gameId = 0;
        this.cars = new HashMap<>();
        this.winners = new HashMap<>();
    }

    @Override
    public void saveWinners(int gameId, List<String> winners) {
        this.winners.put(gameId, winners);
    }

    @Override
    public void saveCars(int gameId, List<Car> cars) {
        List<RacingCarDto> racingCars = new ArrayList<>();
        cars.forEach(car -> racingCars.add(new RacingCarDto(car.getName(), car.getPosition())));
        this.cars.put(gameId, racingCars);
    }

    @Override
    public int saveGame(int count) {
        return gameId++;
    }

    @Override
    public List<String> findWinnersByGameId(int gameId) {
        return this.winners.get(gameId);
    }

    @Override
    public List<RacingCarDto> findRacingCarsByGameId(int gameId) {
        return this.cars.get(gameId);
    }

    @Override
    public Map<Integer, List<String>> findWinners() {
        return this.winners;
    }

    @Override
    public Map<Integer, List<RacingCarDto>> findRacingCars() {
        return this.cars;
    }
}
