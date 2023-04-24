package racingcar.controller;

import racingcar.domain.strategy.move.RandomBasedMoveStrategy;
import racingcar.dto.GameInputDto;
import racingcar.dto.GameOutputDto;
import racingcar.dto.GameRequestDto;
import racingcar.dto.GameResponseDto;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ConsoleRacingCarController {
    private final RacingCarService racingCarService;
    private final InputView inputView;
    private final OutputView outputView;
    
    public ConsoleRacingCarController(final RacingCarService racingCarService, final InputView inputView, final OutputView outputView) {
        this.racingCarService = racingCarService;
        this.inputView = inputView;
        this.outputView = outputView;
    }
    
    public void run() {
        repeatJustRunnableAtException(this::playGame);
        outputView.printGameResult(parseGameResponseDtos(racingCarService.findAllGameResult()));
    }
    
    private List<GameResponseDto> parseGameResponseDtos(final List<GameOutputDto> gameOutputDtos) {
        return gameOutputDtos.stream()
                .map(gameOutputDto -> new GameResponseDto(gameOutputDto.getWinners(), gameOutputDto.getRacingCars()))
                .collect(Collectors.toUnmodifiableList());
    }
    
    private void playGame() {
        final GameRequestDto gameRequestDto = getGameInputDto();
        final String names = gameRequestDto.getNames();
        final String count = gameRequestDto.getCount();
        racingCarService.playGame(new GameInputDto(names, count), new RandomBasedMoveStrategy());
    }
    
    private GameRequestDto getGameInputDto() {
        final String carNames = repeatSupplierAtException(inputView::inputCarNames);
        final int count = repeatSupplierAtException(inputView::inputCount);
        return getGameInputDto(carNames, count);
    }
    
    private GameRequestDto getGameInputDto(final String carNames, final int count) {
        return GameRequestDto.builder()
                .names(carNames)
                .count(String.valueOf(count))
                .build();
    }
    
    public <T> T repeatSupplierAtException(Supplier<T> inputProcess) {
        try {
            return inputProcess.get();
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println("[ERROR] " + illegalArgumentException);
            return repeatSupplierAtException(inputProcess);
        }
    }
    
    public void repeatJustRunnableAtException(ConsoleGameProcess gameProcess) {
        try {
            gameProcess.run();
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println("[ERROR] " + illegalArgumentException);
            repeatJustRunnableAtException(gameProcess);
        }
    }
}
