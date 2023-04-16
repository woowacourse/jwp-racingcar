package racingcar.service;

import racingcar.dto.GameInputDto;
import racingcar.dto.RacingResultResponseDto;
import racingcar.util.NumberGenerator;

import java.util.List;

public interface RacingGameService {
    public RacingResultResponseDto playGameWithoutPrint(GameInputDto gameInputDto, NumberGenerator numberGenerator);
    public List<RacingResultResponseDto> findAllGameResult();
}
