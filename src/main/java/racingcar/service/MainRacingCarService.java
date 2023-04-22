package racingcar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import racingcar.domain.NumberGenerator;
import racingcar.domain.RacingCarGame;
import racingcar.domain.dto.RacingCarResult;

@Service
public class MainRacingCarService {

    private final NumberGenerator numberGenerator;
    private final FindRacingCarResultService findRacingCarResultService;
    private final SaveRacingCarResultService saveRacingCarResultService;

    public MainRacingCarService(
        final NumberGenerator numberGenerator,
        final FindRacingCarResultService findRacingCarResultService,
        final SaveRacingCarResultService saveRacingCarResultService
    ) {
        this.numberGenerator = numberGenerator;
        this.findRacingCarResultService = findRacingCarResultService;
        this.saveRacingCarResultService = saveRacingCarResultService;
    }

    public List<RacingCarResult> findAllResults() {
        return findRacingCarResultService.findAll();
    }

    public RacingCarResult raceCar(final List<String> names, final int attempt) {
        final RacingCarGame racingCarGame = new RacingCarGame(names, attempt, numberGenerator);
        racingCarGame.play();
        final RacingCarResult racingCarResult = racingCarGame.getResult();
        saveRacingCarResultService.save(racingCarResult);
        return racingCarResult;
    }
}
