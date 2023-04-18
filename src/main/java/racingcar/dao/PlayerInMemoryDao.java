package racingcar.dao;

import java.util.ArrayList;
import java.util.List;

import racingcar.dao.mapper.PlayerDtoMapper;
import racingcar.domain.Car;
import racingcar.domain.CarGroup;

public class PlayerInMemoryDao implements PlayerDao {

    private List<PlayerDtoMapper> players = new ArrayList<>();

    private int id = 0;

    @Override
    public boolean save(CarGroup carGroup, int racingGameId) {
        for (final Car car : carGroup.getCars()) {
            final PlayerDtoMapper player =
                    new PlayerDtoMapper(id, car.getName().getName(), car.getPosition().getPosition());
            players.add(player);
            id++;
        }

        return players.size() == carGroup.getCars().size();
    }

    @Override
    public List<PlayerDtoMapper> findAllById(int id) {
        return null;
    }
}
