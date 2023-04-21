package racingcar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.PlayRequestDto;
import racingcar.dto.PlayResponseDto;
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
    public PlayResponseDto postInput(@RequestBody PlayRequestDto playRequestDto) {
        gameService.play(playRequestDto.getCount(), playRequestDto.getNames());
        return new PlayResponseDto(gameService.findWinners(), gameService.getCars());
    }

    @GetMapping("/plays")
    public List<PlayResponseDto> getWinners() {
        return getGameLogResponseDtos();
    }

    private List<PlayResponseDto> getGameLogResponseDtos() {
        return gameService.mappingEachGame()
                .stream()
                .map(dto -> new PlayResponseDto(dto.getWinners(), dto.getGameLog()))
                .collect(Collectors.toList());
    }

    public void play() {
        final String carNames = InputView.inputCarNames();
        final int trialCount = InputView.inputTrialCount();
        gameService.play(trialCount, carNames);
        OutputView.noticeResult();
        OutputView.printCars(gameService.getCars());
        OutputView.printWinners(gameService.findWinners());
    }
}
