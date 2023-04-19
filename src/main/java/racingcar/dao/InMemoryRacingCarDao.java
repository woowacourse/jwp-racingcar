package racingcar.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import racingcar.dto.RacingCarDto;

public class InMemoryRacingCarDao implements RacingCarDao {
    private final List<RacingCarDto> racingCarDtos = new ArrayList<>();

    @Override
    public void insert(Long gameId, String playerName, int playerPosition) {
        RacingCarDto racingCarDto = new RacingCarDto(playerName, playerPosition);
        racingCarDtos.add(racingCarDto);
    }

    @Override
    public List<RacingCarDto> selectByGameId(int gameId) {
        return Collections.unmodifiableList(racingCarDtos);
    }
}
