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
import racingcar.service.GameService;
import racingcar.util.NameFormatConverter;
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
        List<String> carNames = NameFormatConverter.splitNameByDelimiter(requestDto.getNames());
        Cars cars = new Cars(carNames);

        gameService.executeRacingGame(cars, requestDto.getCount());

        final String winners = NameFormatConverter.joinNameWithDelimiter(cars.getWinners());
        final GamePlayResponseDto gamePlayResponseDto = new GamePlayResponseDto(winners, cars.getCars());

        outputView.printResult(gamePlayResponseDto);
        return ResponseEntity.ok()
                .body(gamePlayResponseDto);
    }

    @GetMapping("/plays")
    public List<GamePlayResponseDto> getHistory() {
        return gameService.getGamePlayHistory();
    }
}
