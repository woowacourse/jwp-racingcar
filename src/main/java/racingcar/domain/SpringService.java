package racingcar.domain;

import static racingcar.option.Option.MIN_TRIAL_COUNT;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;
import racingcar.dao.GameDao;
import racingcar.dao.GameLogDao;
import racingcar.dao.WinnersDao;

@Service
public class SpringService {

    private List<Car> cars;
    private final MoveChance moveChance;

    private GameDao gameDAO;
    private GameLogDao gameLogDAO;
    private WinnersDao winnersDAO;

    public SpringService(GameDao gameDAO, GameLogDao gameLogDAO, WinnersDao winnersDAO) {
        this.gameDAO = gameDAO;
        this.gameLogDAO = gameLogDAO;
        this.winnersDAO = winnersDAO;
        this.moveChance = new RandomMoveChance();
    }

    public void setUpGame(String names) {
        this.cars = Stream.of(names.split(","))
                .map(Car::new)
                .collect(Collectors.toList());
    }

    public List<Car> findWinners() {
        int maxPosition = findMaxPosition();
        return cars.stream()
                .filter(car -> car.hasSamePositionWith(maxPosition))
                .collect(Collectors.toList());
    }

    private int findMaxPosition() {
        int maxPosition = 0;
        for (Car car : cars) {
            maxPosition = car.selectMaxPosition(maxPosition);
        }
        return maxPosition;
    }

    public void playOnce() {
        for (Car car : cars) {
            car.move(moveChance);
        }
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }

    public void play(int trialCount) {
        validateNotNegativeInteger(trialCount);
        int gameNumber = gameDAO.saveGame(trialCount);
        playMultipleTimes(trialCount);
        for (Car car : cars) {
            gameLogDAO.insert(gameNumber, car.getName(), car.getPosition());
        }
        for (Car car : findWinners()) {
            winnersDAO.insert(gameNumber, car.getName());
        }
    }

    private void validateNotNegativeInteger(int trialCount) {
        if (trialCount <= MIN_TRIAL_COUNT) {
            throw new IllegalArgumentException("[ERROR] 시도 횟수를 다시 입력해 주세요.");
        }
    }

    private void playMultipleTimes(int trialCount) {
        for (int i = 0; i < trialCount; i++) {
            playOnce();
        }
    }
}
