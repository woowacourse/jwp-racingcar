package racingcar.dao;

import java.util.List;

import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarsDto;
import racingcar.dto.TryCountDto;
import racingcar.dto.WinnersDto;

public class RacingCarInMemoryDao implements RacingCarDao{
    @Override
    public void insertGame(RacingCarsDto racingCarsDto, TryCountDto tryCountDto) {
        RacingCarDao.super.insertGame(racingCarsDto, tryCountDto);
    }

    @Override
    public List<WinnersDto> selectWinners() {
        return RacingCarDao.super.selectWinners();
    }

    @Override
    public List<RacingCarDto> selectRace(int gameId) {
        return RacingCarDao.super.selectRace(gameId);
    }
}
