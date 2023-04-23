package racingcar.dao.console;

import racingcar.dao.RacingCarDao;
import racingcar.entity.CarEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RacingCarConsoleDao implements RacingCarDao {

    private static final int INITIAL_ID = 1;
    private static RacingCarConsoleDao racingCarConsoleDao;

    private List<CarEntity> cars;
    private int id = INITIAL_ID;

    private RacingCarConsoleDao() {
    }

    public static RacingCarConsoleDao of() {
        if (!Objects.isNull(racingCarConsoleDao)) {
            return racingCarConsoleDao;
        }
        racingCarConsoleDao = new RacingCarConsoleDao();
        racingCarConsoleDao.id = racingCarConsoleDao.getId();
        racingCarConsoleDao.cars = new ArrayList<>();
        return racingCarConsoleDao;
    }

    private int getId() {
        return id++;
    }

    @Override
    public List<CarEntity> findCarsByGameId(int gameId) {
        return cars.stream()
                .filter(carEntity -> carEntity.getRacingGameId() == gameId)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void saveCar(CarEntity carEntity) {
        CarEntity createdCarEntity = new CarEntity.Builder()
                .id(getId())
                .name(carEntity.getName())
                .position(carEntity.getPosition())
                .racingGameId(carEntity.getRacingGameId())
                .build();
        cars.add(createdCarEntity);
    }

    @Override
    public int findIdByName(String name) {
        return cars.stream()
                .filter(carEntity -> carEntity.getName().equals(name))
                .findFirst()
                .map(carEntity -> carEntity.getId())
                .orElseThrow(() -> new IllegalArgumentException("찾는 이름의 차가 없습니다."));
    }

    public String findNameById(int id) {
        return cars.stream()
                .filter(carEntity -> carEntity.getId() == id)
                .findFirst()
                .map(carEntity -> carEntity.getName())
                .orElseThrow(() -> new IllegalArgumentException("찾는 아이디의 차가 없습니다." + id));
    }

}
