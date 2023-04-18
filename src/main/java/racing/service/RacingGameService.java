package racing.service;

import java.util.List;
import racing.dto.GameInputDto;
import racing.dto.GameResultDto;
import racing.util.NumberGenerator;

public interface RacingGameService {

    GameResultDto playGame(final GameInputDto gameInputDto, final NumberGenerator numberGenerator);

    List<GameResultDto> showGames();
}
