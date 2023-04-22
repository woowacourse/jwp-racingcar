package racingcar.dao.console;

import racingcar.dao.CarsDao;
import racingcar.dto.CarDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConsoleCarsDao implements CarsDao {

    private final Map<Integer, CarData> carDataMap;
    private int carIdIndex;

    public ConsoleCarsDao() {
        this.carDataMap = new HashMap<>();
        carIdIndex = 1;
    }

    @Override
    public int insert(final int gameId, final CarDto carInfo, final boolean isWin) {
        carDataMap.put(carIdIndex++, new CarData(carInfo.getName(), carInfo.getPosition(), isWin, gameId));
        return carIdIndex;
    }

    @Override
    public List<CarDto> findAllByGameId(final int gameId) {
        return carDataMap.values().stream()
                .filter(carData -> carData.getGameId() == gameId)
                .map(carData -> new CarDto(carData.getName(), carData.getPosition()))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<String> findWinnerNamesByGameId(final int gameId) {
        return carDataMap.values().stream()
                .filter(carData -> carData.getGameId() == gameId)
                .filter(CarData::isWin)
                .map(CarData::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public static class CarData {

        private final String name;
        private final int position;
        private final boolean isWin;
        private final int gameId;

        public CarData(final String name, final int position, final boolean isWin, final int gameId) {
            this.name = name;
            this.position = position;
            this.isWin = isWin;
            this.gameId = gameId;
        }

        public String getName() {
            return name;
        }

        public int getPosition() {
            return position;
        }

        public int getGameId() {
            return gameId;
        }

        public boolean isWin() {
            return isWin;
        }
    }
}
