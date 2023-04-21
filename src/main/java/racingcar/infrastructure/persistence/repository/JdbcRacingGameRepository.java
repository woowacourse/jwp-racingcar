package racingcar.infrastructure.persistence.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.RacingGame;
import racingcar.domain.RacingGameRepository;
import racingcar.dto.CarDto;
import racingcar.dto.RacingGameDto;
import racingcar.dto.WinnerDto;
import racingcar.infrastructure.persistence.dao.CarDao;
import racingcar.infrastructure.persistence.dao.RacingGameDao;
import racingcar.infrastructure.persistence.dao.WinnerDao;
import racingcar.infrastructure.persistence.entity.CarEntity;
import racingcar.infrastructure.persistence.entity.RacingGameEntity;
import racingcar.infrastructure.persistence.entity.WinnerEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Transactional
@Repository
public class JdbcRacingGameRepository implements RacingGameRepository {

    private final RacingGameDao racingGameDao;
    private final CarDao carDao;
    private final WinnerDao winnerDao;

    public JdbcRacingGameRepository(final RacingGameDao racingGameDao,
                                    final CarDao carDao,
                                    final WinnerDao winnerDao) {
        this.racingGameDao = racingGameDao;
        this.carDao = carDao;
        this.winnerDao = winnerDao;
    }

    @Override
    public Long save(final RacingGame racingGame) {
        final RacingGameEntity racingGameEntity = new RacingGameEntity(racingGame);
        final Long id = racingGameDao.save(racingGameEntity);
        saveCars(racingGame, id);
        saveWinners(racingGame, id);
        return id;
    }

    private void saveCars(final RacingGame racingGame, final Long id) {
        racingGame.getCars()
                .stream()
                .map(it -> new CarEntity(it, id))
                .forEach(carDao::save);
    }

    private void saveWinners(final RacingGame racingGame, final Long id) {
        racingGame.winners().getWinners()
                .stream()
                .map(it -> new WinnerEntity(it, id))
                .forEach(winnerDao::save);
    }

    @Override
    public Optional<RacingGameDto> findById(final Long id) {
        final Optional<RacingGameEntity> racingGameEntity = racingGameDao.findById(id);
        return racingGameEntity.map(gameEntity -> toRacingGame(id, gameEntity));
    }

    private RacingGameDto toRacingGame(final Long id, final RacingGameEntity racingGameEntity) {
        final List<CarDto> cars = carDao.findByGameId(id)
                .stream()
                .map(CarDto::fromEntity)
                .collect(Collectors.toList());
        return new RacingGameDto(
                cars,
                racingGameEntity.getTrialCount()
        );
    }

    @Override
    public Map<Long, List<CarDto>> findAllCars() {
        final List<CarEntity> carEntities = carDao.findAll();

        final Map<Long, List<CarEntity>> carEntityById = carEntities.stream()
                .collect(groupingBy(CarEntity::getGameId));

        final Map<Long, List<CarDto>> carDtoById = new HashMap<>();
        for (final Map.Entry<Long, List<CarEntity>> entry : carEntityById.entrySet()) {
            final Long id = entry.getKey();
            final List<CarDto> toDto = entry.getValue().stream()
                    .map(CarDto::fromEntity)
                    .collect(Collectors.toList());
            carDtoById.put(id, toDto);
        }
        return carDtoById;
    }

    @Override
    public Map<Long, List<WinnerDto>> findAllWinners() {
        final List<WinnerEntity> winnerEntities = winnerDao.findAll();

        final Map<Long, List<WinnerEntity>> winnerEntityById = winnerEntities.stream()
                .collect(groupingBy(WinnerEntity::getGameId));

        final Map<Long, List<WinnerDto>> winnerDtoById = new HashMap<>();
        for (final Map.Entry<Long, List<WinnerEntity>> entry : winnerEntityById.entrySet()) {
            final Long id = entry.getKey();
            final List<WinnerDto> toDto = entry.getValue().stream()
                    .map(WinnerDto::from)
                    .collect(Collectors.toList());
            winnerDtoById.put(id, toDto);
        }
        return winnerDtoById;
    }
}
