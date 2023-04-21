package racing.controller;

import java.util.List;
import racing.dto.GameInputDto;
import racing.dto.GameResultDto;

public interface RacingGameController {

    GameResultDto play(final GameInputDto gameInputDto);

    List<GameResultDto> showAllGames();
}
