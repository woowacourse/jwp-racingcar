package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.db.RacingGameRepository;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.domain.TryCount;
import racingcar.dto.GameResultDto;

import java.util.List;

@Service
@Transactional
public class RacingGameService {
    private final RacingGameRepository racingGameRepository;

    public RacingGameService(RacingGameRepository racingGameRepository) {
        this.racingGameRepository = racingGameRepository;
    }

    public GameResultDto saveGamePlay(String names, int tryCount) {
        RacingGame racingGame = new RacingGame(names);
        racingGame.moveCars(tryCount);
        String winners = racingGame.decideWinners();
        List<Car> resultCars = racingGame.getCars();

        racingGameRepository.saveGame(new TryCount(tryCount), winners, racingGame.getCars());
        return new GameResultDto(winners, resultCars);
    }

    public List<GameResultDto> findAllGame() {
        return racingGameRepository.findAllGameResult();
    }
}
