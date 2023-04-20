package racingcar.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.controller.dto.GamePlayResponseDto;
import racingcar.controller.dto.RequestDto;
import racingcar.model.Cars;
import racingcar.model.Names;
import racingcar.model.TryCount;
import racingcar.service.GameService;

@RestController
public class WebController {

    private final GameService gameService;

    public WebController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<GamePlayResponseDto> playGame(@RequestBody RequestDto requestDto) {
        final Names carNames = new Names(requestDto.getNames());
        Cars cars = new Cars(carNames);

        TryCount trialCount = new TryCount(requestDto.getCount());
        gameService.executeRacingGame(cars, trialCount);

        final GamePlayResponseDto gamePlayResponseDto = new GamePlayResponseDto(cars.getWinners(), cars.getCars());

        return ResponseEntity.ok()
                .body(gamePlayResponseDto);
    }

    @GetMapping("/plays")
    public List<GamePlayResponseDto> getHistory() {
        return gameService.getGamePlayHistory();
    }
}
