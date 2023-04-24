package racingcar.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import racingcar.dao.dto.PlayerDto;
import racingcar.domain.Car;
import racingcar.domain.CarGroup;

public class PlayerInMemoryDao implements PlayerDao {

    private List<PlayerDto> players = new ArrayList<>();

    @Override
    public boolean save(CarGroup carGroup, int racingGameId) {
        for (final Car car : carGroup.getCars()) {
            final PlayerDto player =
                    new PlayerDto(car.getName().getName(), car.getPosition().getPosition(), racingGameId);
            players.add(player);
        }

        return players.size() == carGroup.getCars().size();
    }

    @Override
    public List<PlayerDto> findAllByRacingGameId(int id) {
        return players.stream()
                .filter(player -> player.getRacingGameId() == id)
                .collect(Collectors.toList());
    }
}
