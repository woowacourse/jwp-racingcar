package racingcar.repository;

import org.springframework.stereotype.Repository;
import racingcar.dao.CarDao;
import racingcar.dao.RacingGameDao;
import racingcar.dao.entity.CarEntity;
import racingcar.dao.entity.EntityMapper;
import racingcar.dao.entity.RacingGameEntity;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class WebRacingCarRepository implements RacingCarRepository {

    private final RacingGameDao racingGameDao;
    private final CarDao carDao;

    public WebRacingCarRepository(RacingGameDao racingGameDao, CarDao carDao) {
        this.racingGameDao = racingGameDao;
        this.carDao = carDao;
    }

    @Override
    public void saveRacingGame(RacingGame racingGame) {
        RacingGameEntity racingGameEntity = EntityMapper.toRacingGameEntity(racingGame);
        int gameId = racingGameDao.save(racingGameEntity);

        List<CarEntity> carEntities = EntityMapper.toCarEntities(gameId, racingGame);
        carDao.saveAll(carEntities);
    }

    @Override
    public List<RacingGame> findAllEndedRacingGame() {
        List<RacingGameEntity> endedRacingGameEntities = racingGameDao.findEndedRacingGameEntities();
        List<CarEntity> endedCarEntities = carDao.findEndedCars();

        List<RacingGame> racingGames = new ArrayList<>();

        for (RacingGameEntity endedRacingGameEntity : endedRacingGameEntities) {
            List<Car> cars = getCars(endedRacingGameEntity, endedCarEntities);
            racingGames.add(new RacingGame(new Cars(cars), endedRacingGameEntity.getCount()));
        }
        return racingGames;
    }

    private List<Car> getCars(RacingGameEntity racingGameEntity, List<CarEntity> carEntities) {
        return carEntities.stream()
                .filter(carEntity -> carEntity.getGameId() == racingGameEntity.getId())
                .map(carEntity -> new Car(carEntity.getName(), carEntity.getPosition()))
                .collect(Collectors.toList());
    }
}
