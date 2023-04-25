package racingcar.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.RacingGame;
import racingcar.persistence.repository.RacingRepository;

@Service
public class RacingService {

    private final RacingRepository racingRepository;

    @Autowired
    public RacingService(final RacingRepository racingRepository) {
        this.racingRepository = racingRepository;
    }

    @Transactional
    public RacingGame playRacingGame(final String carNamesText, final int tryCount) {
        RacingGame racingGame = RacingGame.of(
                List.of(carNamesText.split(",")),
                tryCount);
        racingGame.play();
        save(racingGame, tryCount);
        return racingGame;
    }

    private void save(final RacingGame racingGame, final int trialCount) {
        this.racingRepository.saveGameResult(racingGame, trialCount);
    }

    public List<RacingGame> getAllRacingGame() {
        return this.racingRepository.findAllRacingGames();
    }
}
