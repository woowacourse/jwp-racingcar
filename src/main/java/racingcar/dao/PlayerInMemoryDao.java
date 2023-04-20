package racingcar.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import racingcar.dao.mapper.PlayerDtoMapper;
import racingcar.domain.Car;
import racingcar.domain.CarGroup;

public class PlayerInMemoryDao implements PlayerDao {

    private List<PlayerDtoMapper> players = new ArrayList<>();

    @Override
    public boolean save(CarGroup carGroup, int racingGameId) {
        for (final Car car : carGroup.getCars()) {
            final PlayerDtoMapper player =
                    new PlayerDtoMapper(car.getName().getName(), car.getPosition().getPosition(), racingGameId);
            players.add(player);
        }

        return players.size() == carGroup.getCars().size();
    }

    @Override
    public List<PlayerDtoMapper> findAllById(int id) {
        return players.stream()
                .filter(player -> player.getRacingGameId() == id)
                .collect(Collectors.toList());
    }
}
