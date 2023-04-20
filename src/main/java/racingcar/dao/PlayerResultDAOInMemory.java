package racingcar.dao;

import racingcar.dao.entity.PlayerResultEntity;
import racingcar.dto.CarDto;
import racingcar.dto.PlayerResultDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerResultDAOInMemory implements PlayerResultDAO {
    private final List<PlayerResultEntity> playerResults = new ArrayList<>();

    @Override
    public void saveAll(PlayerResultDto playerResultDto) {
        int id = playerResults.size();
        int gameId = playerResultDto.getGameId();

        for (CarDto carDto : playerResultDto.getCarDtos()) {
            PlayerResultEntity entity = PlayerResultEntity.of(id, carDto.getName(), carDto.getPosition(), gameId);

            playerResults.add(entity);
            id++;
        }
    }

    @Override
    public List<PlayerResultEntity> findAll() {
        return Collections.unmodifiableList(playerResults);
    }
}
