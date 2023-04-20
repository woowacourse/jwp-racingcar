package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import racingcar.dao.GameDao;
import racingcar.dao.RacingCarDao;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.dto.PlayResultDto;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarGameResultDto;

@Service
public class RacingCarService {
    private final GameDao gameDao;
    private final RacingCarDao racingCarDao;

    public RacingCarService(GameDao gameDao, RacingCarDao racingCarDao) {
        this.gameDao = gameDao;
        this.racingCarDao = racingCarDao;
    }

    public void saveGameResult(Cars cars, TryCount tryCount) {
        Long gameId = gameDao.insertWithKeyHolder(tryCount.getValue(), cars.getWinner());
        for (Car car : cars.getCars()) {
            racingCarDao.insert(gameId, car.getNameValue(), car.getPositionValue());
        }
    }

    public List<RacingCarGameResultDto> getGameResult() {
        List<RacingCarGameResultDto> gameResults = new ArrayList<>();
        List<PlayResultDto> playResult = gameDao.selectAll();
        for (PlayResultDto playResultDto : playResult) {
            List<RacingCarDto> racingCar = racingCarDao.selectByGameId(playResultDto.getId());
            gameResults.add(new RacingCarGameResultDto(playResultDto.getWinners(), racingCar));
        }
        return gameResults;
    }

    public void playRound(Cars cars, TryCount tryCount) {
        for (int i = 0; i < tryCount.getValue(); i++) {
            cars.runRound();
        }
    }
}
