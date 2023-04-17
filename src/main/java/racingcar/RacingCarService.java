package racingcar;

import java.util.List;
import org.springframework.stereotype.Service;
import racingcar.dto.RacingCarNamesDto;
import racingcar.dto.RacingCarStatusDto;
import racingcar.dto.RacingCarWinnerDto;
import racingcar.repository.GameRepository;
import racingcar.repository.PlayerRepository;
import racingcar.service.RacingCarGame;
import racingcar.service.RandomMoveStrategy;
import racingcar.service.TryCount;

@Service
public class RacingCarService {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    public RacingCarService(final GameRepository gameRepository, final PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    public PlayResponse play(final PlayRequest playRequest) {
        RacingCarGame racingCarGame = new RacingCarGame();
        createCars(playRequest, racingCarGame);
        TryCount tryCount = new TryCount(playRequest.getCount());
        playGame(tryCount, racingCarGame);
        PlayResponse response = save(playRequest.getCount(), racingCarGame);
        return response;
    }

    private void createCars(final PlayRequest playRequest, final RacingCarGame racingCarGame) {
        RacingCarNamesDto racingCarNamesDto = RacingCarNamesDto.of(playRequest.getNames());
        racingCarGame.createCars(racingCarNamesDto);
    }

    private void playGame(final TryCount tryCount, final RacingCarGame racingCarGame) {
        RandomMoveStrategy randomMoveStrategy = new RandomMoveStrategy();
        while (tryCount.isAvailable()) {
            racingCarGame.moveCars(randomMoveStrategy);
            tryCount.moveUntilZero();
        }
    }

    private PlayResponse save(final int trialCount, final RacingCarGame racingCarGame) {
        RacingCarWinnerDto winners = findWinners(racingCarGame);
        List<RacingCarStatusDto> racingCars = racingCarGame.getCarStatuses();
        long gameId = gameRepository.save(trialCount);
        playerRepository.saveAll(racingCars, winners.getWinners(), gameId);
        return PlayResponse.of(winners, racingCars);
    }

    private RacingCarWinnerDto findWinners(final RacingCarGame racingCarGame) {
        return racingCarGame.findWinners();
    }
}
