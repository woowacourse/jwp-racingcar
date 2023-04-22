package racingcar.dao;

import racingcar.dto.CarNameDTO;
import racingcar.dto.CarNamePositionDTO;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LocalCarDao implements CarDao {

    private static final int UPDATED_ROWS = 1;

    private final Map<Long, CarEntity> carEntities = new LinkedHashMap<>();
    private Long id = 0L;

    @Override
    public int insert(final String name, final int position, final Long gameId, final boolean isWin) {
        carEntities.put(++id, new CarEntity(name, position, gameId, isWin));
        return UPDATED_ROWS;
    }

    @Override
    public int countRows() {
        return carEntities.size();
    }

    @Override
    public void deleteAll() {
        carEntities.clear();
    }

    @Override
    public List<CarNameDTO> findWinners(final Long gameId) {
        return carEntities.values().stream()
                .filter(carEntity -> gameId.equals(carEntity.getGameId()) && carEntity.isWin())
                .map(carEntity -> new CarNameDTO(carEntity.getName()))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<CarNamePositionDTO> findAllCarNamesAndPositions(final Long gameId) {
        return carEntities.values().stream()
                .filter(carEntity -> gameId.equals(carEntity.getGameId()))
                .map(carEntity -> new CarNamePositionDTO(carEntity.getName(), carEntity.getPosition()))
                .collect(Collectors.toUnmodifiableList());
    }

    private static class CarEntity {

        private final String name;
        private final int position;
        private final Long gameId;
        private final boolean isWin;

        public CarEntity(final String name, final int position, final Long gameId, final boolean isWin) {
            this.name = name;
            this.position = position;
            this.gameId = gameId;
            this.isWin = isWin;
        }

        public String getName() {
            return name;
        }

        public int getPosition() {
            return position;
        }

        public Long getGameId() {
            return gameId;
        }

        public boolean isWin() {
            return isWin;
        }
    }
}
