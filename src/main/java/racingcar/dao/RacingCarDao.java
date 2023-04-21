package racingcar.dao;

import java.util.List;

import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarsDto;
import racingcar.dto.TryCountDto;
import racingcar.dto.WinnersDto;

public interface RacingCarDao {
    default void insertGame(RacingCarsDto racingCarsDto, TryCountDto tryCountDto) {

    }

    default List<WinnersDto> selectWinners() {
        return null;
    }

    default List<RacingCarDto> selectRace(int gameId) {
        return null;
    }
}
