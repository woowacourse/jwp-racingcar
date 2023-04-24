package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.GameDao;
import racingcar.dao.GameLogDao;
import racingcar.dao.WinnersDao;
import racingcar.domain.Car;
import racingcar.dto.ServiceControllerDto;

import java.util.ArrayList;
import java.util.List;

import static racingcar.option.Option.MIN_TRIAL_COUNT;

@Service
public class WebService {
    private final GameDao gameDao;
    private final GameLogDao gameLogDao;
    private final WinnersDao winnersDao;

    public WebService(final GameDao gameDao, final GameLogDao gameLogDao, final WinnersDao winnersDao) {
        this.gameDao = gameDao;
        this.gameLogDao = gameLogDao;
        this.winnersDao = winnersDao;
    }

    public ServiceControllerDto play(final int trialCount, final String names) {
        ServiceControllerDto serviceControllerDto = playOnConsole(trialCount,names);
        long gameNumber = gameDao.insert(trialCount);
        insertCars(gameNumber, serviceControllerDto.getGameLog());
        insertWinners(gameNumber, serviceControllerDto.getWinners());
        return serviceControllerDto;
    }
    public static ServiceControllerDto playOnConsole(final int trialCount, final String names){
        validateNotNegativeInteger(trialCount);
        GameLogic gameLogic = new GameLogic(names);
        playMultipleTimes(trialCount, gameLogic);
        return new ServiceControllerDto(gameLogic.getCars(),gameLogic.findWinners());
    }

    private void insertWinners(long gameNumber, List<Car> winners) {
        for (Car car : winners) {
            winnersDao.insert(gameNumber, car.getName());
        }
    }

    private void insertCars(long gameNumber, List<Car> cars) {
        for (Car car : cars) {
            gameLogDao.insert(gameNumber, car.getName(), car.getPosition());
        }
    }

    private static void validateNotNegativeInteger(int trialCount) {
        if (trialCount < MIN_TRIAL_COUNT) {
            throw new IllegalArgumentException("[ERROR] 시도횟수는 음수이면 안됩니다.");
        }
    }

    public List<ServiceControllerDto> mappingEachGame() {
        List<ServiceControllerDto> gameLog = new ArrayList<>();
        for (Long gameNumber : gameDao.load()) {
            gameLog.add(new ServiceControllerDto(makeGameLogList(gameNumber), makeWinnersList(gameNumber)));
        }
        return gameLog;
    }

    private List<Car> makeGameLogList(final Long gameNumber) {
        return gameLogDao.load(gameNumber);
    }

    private List<Car> makeWinnersList(final long gameNumber) {
        return winnersDao.load(gameNumber);
    }

    private static void playMultipleTimes(final int trialCount, final GameLogic gameLogic) {
        for (int i = 0; i < trialCount; i++) {
            gameLogic.playOnce();
        }
    }
}
