package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import racingcar.dao.PlayResultDao;
import racingcar.dao.RacingCarDao;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.dto.PlayResultDto;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarGameResultDto;

@Service
public class RacingCarService {
    private final PlayResultDao playResultDao;
    private final RacingCarDao racingCarDao;

    public RacingCarService(PlayResultDao playResultDao, RacingCarDao racingCarDao) {
        this.playResultDao = playResultDao;
        this.racingCarDao = racingCarDao;
    }

    public void saveGameResult(Cars cars, TryCount tryCount) {
        Long gameId = playResultDao.insertWithKeyHolder(tryCount.getValue(), cars.getWinner());
        for (Car car : cars.getCars()) {
            racingCarDao.insert(gameId, car.getName().getValue(), car.getPosition().getValue());
        }
    }

    public List<RacingCarGameResultDto> getGameResult() {
        List<RacingCarGameResultDto> gameResults = new ArrayList<>();
        List<PlayResultDto> playResult = playResultDao.selectAll();
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
