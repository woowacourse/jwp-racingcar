package racingcar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import racingcar.domain.NumberGenerator;
import racingcar.domain.RacingCarGame;
import racingcar.domain.dto.RacingCarResultDto;

@Service
public class RacingCarService {

    private final NumberGenerator numberGenerator;
    private final FindRacingCarResultService findRacingCarResultService;
    private final SaveRacingCarResultService saveRacingCarResultService;

    public RacingCarService(
        final NumberGenerator numberGenerator,
        final FindRacingCarResultService findRacingCarResultService,
        final SaveRacingCarResultService saveRacingCarResultService
    ) {
        this.numberGenerator = numberGenerator;
        this.findRacingCarResultService = findRacingCarResultService;
        this.saveRacingCarResultService = saveRacingCarResultService;
    }

    public List<RacingCarResultDto> findAllResults() {
        return findRacingCarResultService.findAll();
    }

    public RacingCarResultDto raceCar(final List<String> names, final int attempt) {
        final RacingCarGame racingCarGame = new RacingCarGame(names, attempt, numberGenerator);
        racingCarGame.play();
        final RacingCarResultDto racingCarResultDto = racingCarGame.getResult();
        saveRacingCarResultService.save(racingCarResultDto);
        return racingCarResultDto;
    }
}
