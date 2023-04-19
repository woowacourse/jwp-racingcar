package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.GameDao;
import racingcar.dao.GameLogDao;
import racingcar.dao.WinnersDao;
import racingcar.domain.Car;
import racingcar.domain.MoveChance;
import racingcar.domain.RandomMoveChance;
import racingcar.dto.ServiceControllerDto;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static racingcar.option.Option.MIN_TRIAL_COUNT;

@Service
public class GameService {
    private List<Car> cars;
    private final MoveChance moveChance;
    private final GameDao gameDAO;
    private final GameLogDao gameLogDAO;
    private final WinnersDao winnersDAO;

    public GameService(final GameDao gameDAO, final GameLogDao gameLogDAO, final WinnersDao winnersDAO) {
        this.gameDAO = gameDAO;
        this.gameLogDAO =gameLogDAO;
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
        long gameNumber = gameDAO.saveGame(trialCount);
        playMultipleTimes(trialCount);
        for (Car car : cars) {
            gameLogDAO.insert(gameNumber, car.getName(), car.getPosition());
        }
        for (Car car : findWinners()) {
            winnersDAO.insert(gameNumber, car.getName());
        }
    }

    private void validateNotNegativeInteger(int trialCount) {
        if (trialCount < MIN_TRIAL_COUNT) {
            throw new IllegalArgumentException("[ERROR] 시도횟수는 음수이면 안됩니다.");
        }
    }

    private void playMultipleTimes(int trialCount) {
        for (int i = 0; i < trialCount; i++) {
            playOnce();
        }
    }

    public List<ServiceControllerDto> mappingEachGame(){
        List<ServiceControllerDto> gameLog = new ArrayList<>();
        for (Long gameNumber : gameDAO.load()){
            gameLog.add(new ServiceControllerDto(makeGameLogList(gameNumber),makeWinnersList(gameNumber)));
        }
        return gameLog;
    }

    private List<Car> makeGameLogList(final Long gameNumber){
        return gameLogDAO.load(gameNumber)
                .stream()
                .map(gameLogEntity -> new Car(gameLogEntity.getPlayerName(),gameLogEntity.getResultPosition()))
                .collect(Collectors.toList());
    }

    private List<Car> makeWinnersList(final long gameNumber){
        return winnersDAO
                .load(gameNumber)
                .stream()
                .map(winnerEntity-> new Car(winnerEntity.getWinner()))
                .collect(Collectors.toList());
    }
}
