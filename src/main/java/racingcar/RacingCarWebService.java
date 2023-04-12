package racingcar;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dto.RacingCarNamesRequest;
import racingcar.dto.RacingCarStatusResponse;
import racingcar.dto.RacingCarWinnerResponse;
import racingcar.service.RacingCarService;
import racingcar.service.RandomMoveStrategy;
import racingcar.service.TryCount;

@Service
public class RacingCarWebService {
    private final RacingCarService racingCarService;
    @Autowired
    private GameInsertDao gameInsertDao;

    @Autowired
    private PlayerInsertDao playerInsertDao;

    public RacingCarWebService() {
        this.racingCarService = new RacingCarService();
    }

    public PlayResponse play(PlayRequest playRequest) {
        RacingCarNamesRequest racingCarNamesRequest = RacingCarNamesRequest.of(playRequest.getNames());
        racingCarService.createCars(racingCarNamesRequest);
        TryCount tryCount = new TryCount(playRequest.getCount());
        int gameId = gameInsertDao.insertGame(tryCount).intValue();
        playGame(tryCount);
        RacingCarWinnerResponse winners = findWinners();
        List<RacingCarStatusResponse> racingCars = racingCarService.getCarStatuses();
        playerInsertDao.insertPlayer(racingCars, winners.getWinners(), gameId);
        // TODO: WINNERS - game_id, player_id 저장
        return PlayResponse.of(winners, racingCars);
    }

    private void playGame(TryCount tryCount) {
        RandomMoveStrategy randomMoveStrategy = new RandomMoveStrategy();
        while (tryCount.isAvailable()) {
            racingCarService.moveCars(randomMoveStrategy);
            tryCount.moveUntilZero();
        }
    }

    private RacingCarWinnerResponse findWinners() {
        return racingCarService.findWinners();
    }
}
