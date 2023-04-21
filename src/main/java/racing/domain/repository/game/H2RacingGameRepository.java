package racing.domain.repository.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import racing.domain.Cars;
import racing.domain.RacingCarGame;
import racing.domain.TrialCount;
import racing.persistence.h2.car.CarDao;
import racing.persistence.h2.car.CarEntity;
import racing.persistence.h2.game.RacingGameDao;
import racing.persistence.h2.game.RacingGameEntity;

@Repository
public class H2RacingGameRepository implements RacingGameRepository {

    private final RacingGameDao racingGameDao;
    private final CarDao carDao;

    public H2RacingGameRepository(RacingGameDao racingGameDao, CarDao carDao) {
        this.racingGameDao = racingGameDao;
        this.carDao = carDao;
    }

    public Long saveGameByCount(int count) {
        return racingGameDao.saveGame(count);
    }

    public List<RacingCarGame> findAllGamesOrderByRecent() {
        List<RacingGameEntity> gameEntities = racingGameDao.findAllGameByRecent();
        List<Long> gameIds = gameEntities.stream()
                .map(RacingGameEntity::getGameId)
                .collect(Collectors.toList());
        List<CarEntity> carsInGame = carDao.findCarsInGame(gameIds);

        return generateRacingGamesFrom(gameEntities, carsInGame);
    }

    private List<RacingCarGame> generateRacingGamesFrom(
            List<RacingGameEntity> gameEntities,
            List<CarEntity> carsInGame
    ) {
        List<RacingCarGame> carGames = new ArrayList<>();
        for (RacingGameEntity gameEntity : gameEntities) {
            Cars gameCars = getCarsOfGame(carsInGame, gameEntity);
            int trialCount = gameEntity.getTrialCount();

            carGames.add(new RacingCarGame(gameCars, new TrialCount(trialCount)));
        }

        return carGames;
    }

    private Cars getCarsOfGame(List<CarEntity> carsInGame, RacingGameEntity gameEntity) {
        return new Cars(carsInGame.stream()
                .filter(carEntity -> carEntity.matchGame(gameEntity.getGameId()))
                .map(CarEntity::toCar)
                .collect(Collectors.toList()));
    }

    public RacingCarGame findRacingGameById(Long gameId) {
        RacingGameEntity racingGameEntity = racingGameDao.findGameById(gameId);
        List<CarEntity> carEntitiesInGame = carDao.findCarsInGame(racingGameEntity.getGameId());

        Cars gameCars = getCarsOfGame(carEntitiesInGame, racingGameEntity);
        int trialCount = racingGameEntity.getTrialCount();

        return new RacingCarGame(gameCars, new TrialCount(trialCount));
    }
}
