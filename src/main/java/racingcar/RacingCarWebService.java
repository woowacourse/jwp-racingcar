package racingcar;

import java.util.List;
import org.springframework.stereotype.Service;
import racingcar.dto.RacingCarNamesRequest;
import racingcar.dto.RacingCarStatusResponse;
import racingcar.dto.RacingCarWinnerResponse;
import racingcar.repository.GameRepository;
import racingcar.repository.PlayerRepository;
import racingcar.service.RacingCarService;
import racingcar.service.RandomMoveStrategy;
import racingcar.service.TryCount;

@Service
public class RacingCarWebService {

    private final RacingCarService racingCarService;
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    public RacingCarWebService(final GameRepository gameRepository, final PlayerRepository playerRepository) {
        this.racingCarService = new RacingCarService();
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    public PlayResponse play(final PlayRequest playRequest) {
        createCars(playRequest);
        TryCount tryCount = new TryCount(playRequest.getCount());
        playGame(tryCount);
        PlayResponse response = save(tryCount);
        return response;
    }

    private void createCars(final PlayRequest playRequest) {
        RacingCarNamesRequest racingCarNamesRequest = RacingCarNamesRequest.of(playRequest.getNames());
        racingCarService.createCars(racingCarNamesRequest);
    }

    private void playGame(final TryCount tryCount) {
        RandomMoveStrategy randomMoveStrategy = new RandomMoveStrategy();
        while (tryCount.isAvailable()) {
            racingCarService.moveCars(randomMoveStrategy);
            tryCount.moveUntilZero();
        }
    }

    private PlayResponse save(final TryCount tryCount) {
        RacingCarWinnerResponse winners = findWinners();
        List<RacingCarStatusResponse> racingCars = racingCarService.getCarStatuses();
        int gameId = gameRepository.insertGame(tryCount).intValue();
        playerRepository.insertPlayer(racingCars, winners.getWinners(), gameId);
        return PlayResponse.of(winners, racingCars);
    }

    private RacingCarWinnerResponse findWinners() {
        return racingCarService.findWinners();
    }
}
