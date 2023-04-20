package racingcar.dao;

import racingcar.dto.CarNameDTO;
import racingcar.dto.CarNamePositionDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LocalCarDao implements CarDao {

    private final List<CarEntity> carEntities = new ArrayList<>();

    @Override
    public int insert(final String name, final int position, final Long gameId, final boolean isWin) {
        carEntities.add(new CarEntity(name, position, gameId, isWin));
        return 1;
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
        return carEntities.stream()
                .filter(carEntity -> gameId.equals(carEntity.getGameId()) && carEntity.isWin)
                .map(carEntity -> new CarNameDTO(carEntity.getName()))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<CarNamePositionDTO> findAllCarNamesAndPositions(final Long gameId) {
        return carEntities.stream()
                .filter(carEntity -> gameId.equals(carEntity.getGameId()))
                .map(carEntity -> new CarNamePositionDTO(carEntity.getName(), carEntity.getPosition()))
                .collect(Collectors.toUnmodifiableList());
    }

    private class CarEntity {

        private final Long id;
        private final String name;
        private final int position;
        private final Long gameId;
        private final boolean isWin;

        public CarEntity(final String name, final int position, final Long gameId, final boolean isWin) {
            this.id = (long) carEntities.size();
            this.name = name;
            this.position = position;
            this.gameId = gameId;
            this.isWin = isWin;
        }

        public Long getId() {
            return id;
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
