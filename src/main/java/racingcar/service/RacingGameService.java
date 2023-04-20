package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dao.GameDao;
import racingcar.dao.GameLogDao;
import racingcar.dao.WinnersDao;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RandomMoveChance;
import racingcar.domain.TrialCount;
import racingcar.dto.GameRequestDto;
import racingcar.dto.GameResponseDto;

@Service
public class RacingGameService {

    private GameDao gameDao;
    private GameLogDao gameLogDao;
    private WinnersDao winnersDao;

    public RacingGameService() {
    }

    @Autowired
    public RacingGameService(GameDao gameDao, GameLogDao gameLogDao, WinnersDao winnersDao) {
        this.gameDao = gameDao;
        this.gameLogDao = gameLogDao;
        this.winnersDao = winnersDao;
    }

    public List<GameResponseDto> getGameLog() {
        List<GameResponseDto> getLogs = new ArrayList<>();
        List<Integer> gameNumbers = getGameNumbers();
        for (int gameNumber : gameNumbers) {
            getLogs.add(new GameResponseDto(winnerResponseDto(gameNumber), gameLogDao.find(gameNumber)));
        }
        return getLogs;
    }

    public List<Car> winnerResponseDto(int gameNumber) {
        return winnersDao.find(gameNumber);
    }

    public List<Integer> getGameNumbers() {
        return gameDao.getGameNumbers();
    }

    public GameResponseDto play(GameRequestDto gameRequestDto) {
        int gameNumber = gameDao.saveGame(gameRequestDto.getCount());
        Cars cars = playGame(gameRequestDto.getNames(), gameRequestDto.getCount()).getCars();
        cars.saveGameLog(gameLogDao, gameNumber);
        cars.saveGameWinner(winnersDao, gameNumber);
        return new GameResponseDto(getWinners(cars), getCars(cars));
    }

    public GameResponseDto playGame(String names, int count) {
        Cars cars = Cars.of(names);
        TrialCount trialCount = TrialCount.of(count);
        for (int i = 0; i < trialCount.getTrialCount(); i++) {
            playOnce(cars);
        }
        return new GameResponseDto(getWinners(cars), getCars(cars));
    }

    public void playOnce(Cars cars) {
        cars.moveCars(new RandomMoveChance());
    }

    public List<Car> getWinners(Cars cars) {
        return cars.getWinners();
    }

    public List<Car> getCars(Cars cars) {
        return cars.getCars();
    }
}
