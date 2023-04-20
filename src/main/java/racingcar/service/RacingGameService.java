package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RacingGame;
import racingcar.dto.GameRequest;
import racingcar.dto.GameResponse;
import racingcar.entity.Player;
import racingcar.repositiory.RacingGameRepository;

import java.util.List;

@Service
public class RacingGameService {

    private final NumberGenerator numberGenerator;
    private final RacingGameRepository racingGameRepository;

    public RacingGameService(final NumberGenerator numberGenerator, final RacingGameRepository racingGameRepository) {
        this.numberGenerator = numberGenerator;
        this.racingGameRepository = racingGameRepository;
    }

    @Transactional
    public GameResponse play(final GameRequest gameRequest) {
        final RacingGame racingGame = playRacingGame(gameRequest);

        racingGameRepository.save(racingGame);

        return GameResponse.of(racingGame.findWinners(), racingGame.findCurrentCarPositions());
    }

    public List<GameResponse> findAll() {
        List<Player> players = racingGameRepository.findAll();

        return GameResponse.toGamePlayResponse(players);
    }

    private RacingGame playRacingGame(final GameRequest gameRequest) {
        final RacingGame racingGame = new RacingGame(numberGenerator, gameRequest.getNames(), gameRequest.getCount());

        racingGame.play();

        return racingGame;
    }
}
