package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;
import racingcar.dto.RacingGameRequest;
import racingcar.persistence.repository.GameRepository;

import java.util.List;

@Service
public class RacingGameService {

    private final GameRepository racingGameRepository;

    public RacingGameService(final GameRepository gameRepository) {
        this.racingGameRepository = gameRepository;
    }

    @Transactional
    public RacingGame playRacingGame(final RacingGameRequest racingGameRequest) {
        RacingGame racingGame = createRacingGame(racingGameRequest);
        racingGame.start();
        racingGameRepository.saveGame(racingGame);
        return racingGame;
    }

    private RacingGame createRacingGame(final RacingGameRequest racingGameRequest) {
        return new RacingGame(
                List.of(racingGameRequest.getNames().split(",")),
                racingGameRequest.getCount(),
                new RandomNumberGenerator()
        );
    }

    public List<RacingGame> makeGameRecords() {
        List<RacingGame> allGames = racingGameRepository.selectAllGames();
        allGames.forEach(RacingGame::start);
        return allGames;
    }
}
