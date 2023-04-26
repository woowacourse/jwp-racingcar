package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingcar.dao.GameDao;
import racingcar.dao.RacingCarDao;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.dto.GameInitializeDto;
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

    public RacingCarGameResultDto play(GameInitializeDto gameInitializeDto) {
        Cars cars = Cars.from(gameInitializeDto.getNames());
        TryCount tryCount = new TryCount(gameInitializeDto.getCount());
        cars.runRound(tryCount);
        saveGameResult(cars, tryCount);
        return getGameResult(cars);
    }

    private void saveGameResult(Cars cars, TryCount tryCount) {
        Long gameId = gameDao.insert(tryCount.getValue(), cars.getWinner());
        for (Car car : cars.getCars()) {
            racingCarDao.insert(gameId, car.getNameValue(), car.getPositionValue());
        }
    }

    private RacingCarGameResultDto getGameResult(Cars cars) {
        List<RacingCarDto> racingCarDtos = cars.getCars().stream()
                .map(car -> new RacingCarDto(
                        car.getNameValue(),
                        car.getPositionValue())
                )
                .collect(Collectors.toUnmodifiableList());
        return new RacingCarGameResultDto(cars.getWinner(), racingCarDtos);
    }

    public List<RacingCarGameResultDto> getAllGameResult() {
        List<RacingCarGameResultDto> gameResults = new ArrayList<>();
        List<PlayResultDto> playResult = gameDao.selectAll();
        for (PlayResultDto playResultDto : playResult) {
            List<RacingCarDto> racingCar = racingCarDao.selectByGameId(playResultDto.getId());
            gameResults.add(new RacingCarGameResultDto(playResultDto.getWinners(), racingCar));
        }
        return gameResults;
    }
}
