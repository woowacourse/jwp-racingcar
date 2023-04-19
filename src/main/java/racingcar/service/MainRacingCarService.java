package racingcar.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dto.GameInitializationRequest;
import racingcar.dto.GameResultResponse;

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

    public GameResultResponse raceCar(final GameInitializationRequest gameInitializationRequest) {
        final List<String> names = List.of(gameInitializationRequest.getNames().split(","));
        final int count = gameInitializationRequest.getCount();

        final RacingCarResult racingCarResult = playRacingCarService.playRacingCar(names, count);
        saveRacingCarService.saveRacingCarResult(racingCarResult);

        return new GameResultResponse(racingCarResult.getWinners(), racingCarResult.getCars());
    }
}
