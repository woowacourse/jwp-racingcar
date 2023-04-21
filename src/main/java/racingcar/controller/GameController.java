package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.RequestDto;
import racingcar.dto.ResponseDto;
import racingcar.service.GameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GameController {
    private final GameService gameService;

    public GameController(final GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/plays")
    public ResponseDto postInput(@RequestBody RequestDto requestDto) {
        gameService.setUpGame(requestDto.getNames());
        gameService.play(requestDto.getCount());
        return new ResponseDto(gameService.findWinners(), gameService.getCars());
    }

    @GetMapping("/plays")
    public ResponseEntity getWinners() {

        return ResponseEntity.ok()
                .body(getGameLogResponseDtos());
    }

    private List<ResponseDto> getGameLogResponseDtos() {
        return gameService.mappingEachGame()
                .stream()
                .map(dto -> new ResponseDto(dto.getWinners(), dto.getGameLog()))
                .collect(Collectors.toList());
    }

    public void play() {
        gameService.setUpGame(InputView.inputCarNames());
        gameService.play(InputView.inputTrialCount());
        OutputView.noticeResult();
        OutputView.printCars(gameService.getCars());
        OutputView.printWinners(gameService.findWinners());
    }
}
