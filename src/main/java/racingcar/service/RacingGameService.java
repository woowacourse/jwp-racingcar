package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.db.RacingGameRepository;
import racingcar.domain.RacingGame;
import racingcar.dto.CarDto;
import racingcar.dto.GameResultDto;
import racingcar.dto.response.GameResponse;

import java.util.List;

@Service
@Transactional
public class RacingGameService {
    private final RacingGameRepository racingGameRepository;

    public RacingGameService(RacingGameRepository racingGameRepository) {
        this.racingGameRepository = racingGameRepository;
    }

    public GameResponse saveGamePlay(String names, int tryCount) {
        RacingGame racingGame = new RacingGame(names);
        racingGame.moveCars(tryCount);
        String winners = racingGame.decideWinners();
        List<CarDto> resultCars = racingGame.getCars();

        racingGameRepository.saveGame(new GameResultDto(tryCount, winners, resultCars));
        return new GameResponse(winners, resultCars);
    }

    public List<GameResponse> findAllGame() {
        return racingGameRepository.findAllGame();
    }
}
