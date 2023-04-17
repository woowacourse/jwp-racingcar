package racingcar.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import racingcar.dao.GameLogDao;
import racingcar.dao.WinnersDao;

public class Cars {

    private final List<Car> cars;

    private Cars(List<Car> cars) {
        this.cars = cars;
    }

    public static Cars of(String names) {
        List<Car> cars = Stream.of(names.split(","))
                .map(name -> new Car(new Name(name)))
                .collect(Collectors.toList());
        return new Cars(cars);
    }

    public List<Car> getWinners() {
        return new Cars(findWinners().stream()
                .collect(Collectors.toUnmodifiableList())
        ).getCars();
    }

    private List<Car> findWinners() {
        int maxPosition = findMaxPosition();
        return cars.stream()
                .filter(car -> car.hasSamePositionWith(maxPosition))
                .collect(Collectors.toUnmodifiableList());
    }

    private int findMaxPosition() {
        int maxPosition = 0;
        for (Car car : cars) {
            maxPosition = car.selectMaxPosition(maxPosition);
        }
        return maxPosition;
    }

    public void moveCars(MoveChance moveChance) {
        for (Car car : cars) {
            car.move(moveChance);
        }
    }

    public void saveGameLog(GameLogDao gameLogDao, int gameNumber) {
        for (Car car : cars) {
            gameLogDao.insert(gameNumber, car.getName(), car.getPosition());
        }
    }

    public void saveGameWinner(WinnersDao winnersDao, int gameNumber) {
        for (Car car : getWinners()) {
            winnersDao.insert(gameNumber, car.getName());
        }
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }
}
