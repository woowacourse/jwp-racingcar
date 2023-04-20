package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RacingGame;
import racingcar.dto.GameRequestDto;
import racingcar.dto.GameResponseDto;
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
    public GameResponseDto play(final GameRequestDto gameRequest) {
        final RacingGame racingGame = playRacingGame(gameRequest);

        racingGameRepository.save(racingGame);

        return GameResponseDto.of(racingGame.findWinners(), racingGame.findCurrentCarPositions());
    }

    @Transactional(readOnly = true)
    public List<GameResponseDto> findAll() {
        List<Player> players = racingGameRepository.findAll();

        return GameResponseDto.toGamePlayResponse(players);
    }

    private RacingGame playRacingGame(final GameRequestDto gameRequest) {
        final RacingGame racingGame = new RacingGame(numberGenerator, gameRequest.getNames(), gameRequest.getCount());

        racingGame.play();

        return racingGame;
    }
}
