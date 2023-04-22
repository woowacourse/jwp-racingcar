package racingcar.dao;

import racingcar.dto.CarDto;

import java.util.List;

public interface CarsDao {

    int insert(final int gameId, final CarDto carInfo, final boolean isWin);

    List<CarDto> findAllByGameId(final int gameId);

    List<String> findWinnerNamesByGameId(final int gameId);
}
