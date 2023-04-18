package racingcar.repository;

import org.springframework.stereotype.Repository;
import racingcar.dao.RacingCarDao;
import racingcar.dao.RacingGameDao;
import racingcar.dao.RacingWinnerDao;
import racingcar.domain.*;
import racingcar.entity.RacingCarEntity;
import racingcar.entity.RacingGameEntity;
import racingcar.entity.RacingWinnerEntity;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RacingCarRepository {

    private final RacingGameDao racingGameDao;
    private final RacingCarDao racingCarDao;
    private final RacingWinnerDao racingWinnerDao;

    public RacingCarRepository(
            final RacingGameDao racingGameDao,
            final RacingCarDao racingCarDao,
            final RacingWinnerDao racingWinnerDao
    ) {
        this.racingGameDao = racingGameDao;
        this.racingCarDao = racingCarDao;
        this.racingWinnerDao = racingWinnerDao;
    }

    public void save(final RacingGame racingGame, final int trialCount) {
        final Long gameId = saveRacingGame(trialCount);
        final List<RacingCarEntity> savedRacingCars = saveRacingCar(racingGame, gameId);
        saveRacingWinners(racingGame, savedRacingCars);
    }

    private Long saveRacingGame(final int trialCount) {
        final RacingGameEntity racingGameEntity = RacingGameEntity.builder()
                .trialCount(trialCount)
                .build();
        return racingGameDao.save(racingGameEntity);
    }

    private List<RacingCarEntity> saveRacingCar(final RacingGame racingGame, final Long gameId) {
        final List<RacingCarEntity> racingCarEntities = racingGame.getCurrentResult().stream()
                .map(car -> RacingCarEntity.builder()
                        .gameId(gameId)
                        .name(car.getName())
                        .position(car.getPosition())
                        .build()
                )
                .collect(Collectors.toList());
        racingCarDao.saveAll(racingCarEntities);
        return racingCarDao.findAllByGameId(gameId);
    }

    private void saveRacingWinners(
            final RacingGame racingGame,
            final List<RacingCarEntity> racingCarEntities
    ) {
        final List<String> winners = racingGame.findWinners();
        final List<RacingWinnerEntity> racingWinnerEntities = racingCarEntities.stream()
                .filter(car -> winners.contains(car.getName()))
                .map(car -> RacingWinnerEntity.builder()
                        .gameId(car.getGameId())
                        .carId(car.getId())
                        .build())
                .collect(Collectors.toList());
        racingWinnerDao.saveAll(racingWinnerEntities);
    }

    public List<RacingGame> findAll() {
        final List<RacingGameEntity> racingGameEntities = racingGameDao.findAll();
        return racingGameEntities.stream()
                .map(this::createRacingGame)
                .collect(Collectors.toList());
    }

    private RacingGame createRacingGame(final RacingGameEntity racingGameEntity) {
        final List<RacingCarEntity> racingCarEntities = racingCarDao.findAllByGameId(racingGameEntity.getId());
        final List<Car> cars = racingCarEntities.stream()
                .map(racingCarEntity -> new Car(racingCarEntity.getName(), new Position(racingCarEntity.getPosition())))
                .collect(Collectors.toList());
        return new RacingGame(new RandomNumberGenerator(), new Cars(cars), new Count(0));
    }
}
