package racingcar.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dto.gameInitializationRequest;
import racingcar.dto.gameResultResponse;

@Service
public class MainRacingCarService {

    private final PlayRacingCarService playRacingCarService;
    private final SaveRacingCarService saveRacingCarService;

    @Autowired
    public MainRacingCarService(final PlayRacingCarService playRacingCarService,
                                final SaveRacingCarService saveRacingCarService) {
        this.playRacingCarService = playRacingCarService;
        this.saveRacingCarService = saveRacingCarService;
    }

    public gameResultResponse raceCar(final gameInitializationRequest gameInitializationRequest) {
        final List<String> names = List.of(gameInitializationRequest.getNames().split(","));
        final int count = gameInitializationRequest.getCount();

        final RacingCarResult racingCarResult = playRacingCarService.playRacingCar(names, count);
        saveRacingCarService.saveRacingCarResult(racingCarResult);

        return new gameResultResponse(racingCarResult.getWinners(), racingCarResult.getCars());
    }
}
