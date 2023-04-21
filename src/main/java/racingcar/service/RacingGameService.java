package racingcar.service;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.controller.dto.GameResponse;
import racingcar.dao.Player;
import racingcar.dao.PlayerDao;
import racingcar.dao.RacingGameDao;
import racingcar.domain.Name;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingCars;
import racingcar.domain.TryCount;

@Service
@Transactional
public class RacingGameService {

    private final RacingGameDao racingGameDao;
    private final PlayerDao playerDao;

    public RacingGameService(final RacingGameDao racingGameDao, final PlayerDao playerDao) {
        this.racingGameDao = racingGameDao;
        this.playerDao = playerDao;
    }

    public GameResponse run(final List<String> inputNames, final int inputCount) {
        final List<Name> names = createNames(inputNames);
        final RacingCars racingCars = new RacingCars(createRacingCar(names));
        final TryCount tryCount = createTryCount(inputCount);

        moveCars(racingCars, tryCount);

        saveRacingCars(tryCount, racingCars);

        return new GameResponse(racingCars.getWinnerNames(), racingCars.getRacingCars());
    }

    private List<Name> createNames(final List<String> names) {
        return names.stream()
                .map(Name::new)
                .collect(toList());
    }

    private TryCount createTryCount(final int count) {
        return new TryCount(count);
    }

    private List<RacingCar> createRacingCar(final List<Name> names) {
        return names.stream()
                .map(RacingCar::createRandomMoveRacingCar)
                .collect(toList());
    }

    private void moveCars(final RacingCars racingCars, final TryCount tryCount) {
        while (canProceed(tryCount)) {
            racingCars.moveAll();
            tryCount.deduct();
        }
    }

    private boolean canProceed(final TryCount tryCount) {
        return !tryCount.isZero();
    }

    private void saveRacingCars(final TryCount tryCount, final RacingCars racingCars) {
        final List<String> winnerNames = racingCars.getWinnerNames();
        final List<Player> players = racingCars.getRacingCars().stream()
                .map(racingCar -> createPlayerSaveDto(winnerNames, racingCar))
                .collect(toList());
        final long gameId = racingGameDao.save(tryCount.getCount());

        playerDao.saveAllPlayers(gameId, players);
    }

    private static Player createPlayerSaveDto(final List<String> winnerNames, final RacingCar racingCar) {
        return new Player(racingCar.getName(), racingCar.getPosition(), winnerNames.contains(racingCar.getName()));
    }

    @Transactional(readOnly = true)
    public List<GameResponse> findAll() {
        final List<Player> allPlayers = playerDao.findAll();

        final Map<Long, List<Player>> groupingPlayers = allPlayers.stream()
                .collect(groupingBy(Player::getGameId));

        return groupingPlayers.values().stream()
                .map(RacingCars::create)
                .map(racingcars -> new GameResponse(racingcars.getWinnerNames(), racingcars.getRacingCars()))
                .collect(toList());
    }
}
