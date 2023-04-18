package racingcar.dao;

import racingcar.dto.RacingGameDto;

import java.util.List;

public interface GameRepository {

    void saveGame(RacingGameDto racingGameDto);

    List<RacingGameDto> selectAllGames();
}
