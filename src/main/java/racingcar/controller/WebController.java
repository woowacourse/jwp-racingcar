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
import racingcar.service.GameService;
import racingcar.view.OutputView;

@RestController
public class WebController {

    private final GameService gameService;
    private final OutputView outputView;

    public WebController(GameService gameService) {
        this.gameService = gameService;
        this.outputView = new OutputView();
    }

    @PostMapping("/plays")
    public ResponseEntity<GamePlayResponseDto> playGame(@RequestBody RequestDto requestDto) {
        final Names carNames = new Names(requestDto.getNames());
        Cars cars = new Cars(carNames);

        final int trialCount = requestDto.getCount();
        if (trialCount < 1) {
            throw new IllegalArgumentException("게임 시도 횟수는 0보다 커야 합니다.");
        }
        gameService.executeRacingGame(cars, trialCount);

        final GamePlayResponseDto gamePlayResponseDto = new GamePlayResponseDto(cars.getWinners(), cars.getCars());

        outputView.printResult(gamePlayResponseDto);
        return ResponseEntity.ok()
                .body(gamePlayResponseDto);
    }

    @GetMapping("/plays")
    public List<GamePlayResponseDto> getHistory() {
        return gameService.getGamePlayHistory();
    }
}
