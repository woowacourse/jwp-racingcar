package racingcar;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import racingcar.controller.RacingConsoleController;
import racingcar.dao.CarDao;
import racingcar.dao.RacingGameDao;
import racingcar.dao.entity.CarEntity;
import racingcar.dao.entity.RacingGameEntity;
import racingcar.service.RacingGameService;

public class RacingCarConsoleApplication {
    public static void main(String[] args) {
        RacingGameService racingGameService = new RacingGameService(new InnerCarDao(), new InnerRacingGameDao());
        RacingConsoleController racingConsoleController = new RacingConsoleController(racingGameService);
        racingConsoleController.run();
    }

    static class InnerCarDao implements CarDao {
        private final Map<Long, CarEntity> database = new HashMap<>();
        private Long key = 1L;

        @Override
        public void saveAll(List<CarEntity> racingCars) {
            for (CarEntity racingCar : racingCars) {
                database.put(key, racingCar);
                key++;
            }
        }

        @Override
        public List<CarEntity> findByRacingGameId(Long id) {
            return database.values().stream()
                    .filter(carEntity -> Objects.equals(carEntity.getGameId(), id))
                    .collect(Collectors.toList());
        }
    }

    static class InnerRacingGameDao implements RacingGameDao {
        private final Map<Long, RacingGameEntity> database = new HashMap<>();
        private Long id = 1L;

        @Override
        public Long save(RacingGameEntity racingGameEntity) {
            database.put(id, racingGameEntity);
            id++;
            return id - 1;
        }

        @Override
        public List<RacingGameEntity> findAllByCreatedTimeAsc() {
            return database.values()
                    .stream().sorted(Comparator.comparing(RacingGameEntity::getCreatedTime))
                    .collect(Collectors.toList());
        }
    }
}
