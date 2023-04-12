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

    private void playGame(TryCount tryCount) {
        RandomMoveStrategy randomMoveStrategy = new RandomMoveStrategy();
        while (tryCount.isAvailable()) {
            racingCarService.moveCars(randomMoveStrategy);
            tryCount.moveUntilZero();
        }
    }

    private PlayResponse save(TryCount tryCount) {
        RacingCarWinnerResponse winners = findWinners();
        List<RacingCarStatusResponse> racingCars = racingCarService.getCarStatuses();
        int gameId = gameInsertDao.insertGame(tryCount).intValue();
        playerInsertDao.insertPlayer(racingCars, winners.getWinners(), gameId);
        return PlayResponse.of(winners, racingCars);
    }

    private RacingCarWinnerResponse findWinners() {
        return racingCarService.findWinners();
    }
}
