package racingcar.dao;

import java.util.List;

import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarsDto;
import racingcar.dto.TryCountDto;
import racingcar.dto.WinnersDto;

public interface RacingCarDao {
    void insertGame(RacingCarsDto racingCarsDto, TryCountDto tryCountDto);

    List<WinnersDto> selectWinners();

    List<RacingCarDto> selectRace(int gameId);
}
