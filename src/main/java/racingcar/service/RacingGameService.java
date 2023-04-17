package racingcar.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dao.RacingGameDao;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.dto.CarDto;
import racingcar.dto.GameResultDto;
import racingcar.dto.RacingGameRequest;

@Service
public final class RacingGameService {

    private final RacingGameDao racingGameDao;

    @Autowired
    public RacingGameService(final RacingGameDao racingGameDao) {
        this.racingGameDao = racingGameDao;
    }

    public GameResultDto playRacingGame(final RacingGameRequest racingGameRequest) {
        RacingGame racingGame = createRacingGame(racingGameRequest);
        play(racingGame);
        GameResultDto gameResultDto = new GameResultDto(
                mapWinnerNamesTextFrom(racingGame),
                mapCarDtosFrom(racingGame)
        );
        save(gameResultDto, racingGameRequest.getCount());
        return gameResultDto;
    }

    private RacingGame createRacingGame(final RacingGameRequest racingGameRequest) {
        return RacingGame.from(racingGameRequest);
    }

    private void play(final RacingGame racingGame) {
        // TODO: 검증 로직 도메인 내로 이동 (미션 요구사항에 따라 2단계에서 수정 예정)
        while (racingGame.isGameOnGoing()) {
            racingGame.start();
        }
    }

    private List<CarDto> mapCarDtosFrom(final RacingGame racingGame) {
        return racingGame.getCars().stream()
                .sorted(Comparator.comparingInt(Car::getPosition).reversed())
                .map(car -> new CarDto(car.getCarName(), car.getPosition()))
                .collect(Collectors.toList());
    }

    private String mapWinnerNamesTextFrom(final RacingGame racingGame) {
        return racingGame.getWinners().stream()
                .map(Car::getCarName)
                .collect(Collectors.joining(","));
    }

    private void save(final GameResultDto gameResultDto, final int trialCount) {
        Number gameResultKey = this.racingGameDao.saveGameResult(gameResultDto.getWinners(), trialCount);
        this.racingGameDao.savePlayerResults(gameResultDto.getRacingCars(), gameResultKey);
    }
}
