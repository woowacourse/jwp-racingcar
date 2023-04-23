package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dao.RacingDao;
import racingcar.domain.RacingGame;
import racingcar.dto.CarDto;
import racingcar.dto.RacingRequest;
import racingcar.dto.RacingResultDto;

@Service
public final class RacingService {

    private final RacingDao racingDao;

    @Autowired
    public RacingService(final RacingDao racingDao) {
        this.racingDao = racingDao;
    }

    public RacingResultDto playRacingGame(final RacingRequest racingRequest) {
        RacingGame racingGame = createRacingGame(racingRequest);
        racingGame.play();
        RacingResultDto racingResultDto = new RacingResultDto(
                mapWinnersToCarDtos(racingGame),
                mapCarDtosFrom(racingGame)
        );
        save(racingResultDto, racingRequest.getCount());
        return racingResultDto;
    }

    private RacingGame createRacingGame(final RacingRequest racingRequest) {
        return RacingGame.from(racingRequest);
    }

    private List<CarDto> mapCarDtosFrom(final RacingGame racingGame) {
        return racingGame.getCars().stream()
                .map(car -> new CarDto(car.getCarName(), car.getPosition()))
                .collect(Collectors.toList());
    }

    private List<CarDto> mapWinnersToCarDtos(final RacingGame racingGame) {
        return racingGame.getWinners().stream()
                .map(car -> new CarDto(car.getCarName(), car.getPosition()))
                .collect(Collectors.toList());
    }

    private void save(final RacingResultDto racingResultDto, final int trialCount) {
        int gameResultId = this.racingDao.saveGameResult(racingResultDto, trialCount);
        this.racingDao.savePlayerResults(racingResultDto.getRacingCars(), gameResultId);
    }
}
