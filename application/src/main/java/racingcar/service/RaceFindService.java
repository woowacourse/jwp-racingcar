package racingcar.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.RacingGame;
import racingcar.domain.Winners;
import racingcar.repository.RacingGameRepository;

@Service
public class RaceFindService {

    private final RacingGameRepository racingGameRepository;

    public RaceFindService(final RacingGameRepository racingGameRepository) {
        this.racingGameRepository = racingGameRepository;
    }

    @Transactional(readOnly = true)
    public List<RacingGame> findAllRace() {
        return racingGameRepository.findAll();
    }

    public Winners findWinners(final RacingGame racingGame) {
        return racingGame.findWinner();
    }
}
