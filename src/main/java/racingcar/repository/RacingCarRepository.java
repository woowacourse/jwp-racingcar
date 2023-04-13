package racingcar.repository;

import racingcar.dto.RacingGameResultDto;

public interface RacingCarRepository {

    void save(RacingGameResultDto racingGameResultDto);
}
