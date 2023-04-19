package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racingcar.service.GameService;
import racingcar.dto.RequestDto;
import racingcar.dto.ResponseDto;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GameController {
    private final GameService gameService;
    public GameController(final GameService gameService){
        this.gameService = gameService;
    }
    @PostMapping("/plays")
    public ResponseDto postInput(@RequestBody RequestDto requestDto) {
        gameService.setUpGame(requestDto.getNames());
        gameService.play(requestDto.getCount());
        ResponseDto responseDto = new ResponseDto(gameService.findWinners(), gameService.getCars());
        return responseDto;
    }

    @GetMapping("/plays")
    public ResponseEntity getWinners(){

        return ResponseEntity.ok()
                .body(getGameLogResponseDtos());
    }

    private List<ResponseDto> getGameLogResponseDtos() {
        return gameService.mappingEachGame()
                .stream()
                .map(dto->new ResponseDto(dto.getWinners(),dto.getGameLog()))
                .collect(Collectors.toList());
    }
    public void play() {
        gameService.setUpGame(InputView.inputCarNames());
        gameService.playMultipleTimes(InputView.inputTrialCount());
        OutputView.noticeResult();
        showResult();
    }

    public void showResult() {
        OutputView.printCars(gameService.getCars());
        OutputView.printWinners(gameService.findWinners());
    }
}
