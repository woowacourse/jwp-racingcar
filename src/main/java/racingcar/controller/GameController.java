package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.controller.dto.SingleGameResultResponse;
import racingcar.controller.dto.SinglePlayRequest;
import racingcar.domain.Car;
import racingcar.domain.Game;
import racingcar.dto.CarDto;
import racingcar.exception.GameException;
import racingcar.service.GameService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GameController {

    private static final String CAR_NAME_SEPARATOR = ",";

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/plays")
    // TODO: 매핑(직렬화/역직렬화)을 누가 해주는지 공부하면 좋을 것 같다.
    public ResponseEntity<SingleGameResultResponse> playGame(@RequestBody SinglePlayRequest singlePlayRequest) {
        final List<String> names = List.of(singlePlayRequest.getNames().split(CAR_NAME_SEPARATOR));
        final int playCount = singlePlayRequest.getCount();

        final Game game = gameService.createGameWith(trim(names), playCount);
        gameService.play(game);

        List<String> winnerNames = getNamesOf(game.findWinners());
        List<CarDto> finishedCars = game.getCars().stream()
                .map(car -> new CarDto(car.getName(), car.getPosition()))
                .collect(Collectors.toUnmodifiableList());

        return ResponseEntity.ok(new SingleGameResultResponse(winnerNames, finishedCars));
    }

    private List<String> getNamesOf(List<Car> cars) {
        return cars.stream()
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    private List<String> trim(List<String> carNames) {
        return carNames.stream()
                .map(String::trim)
                .collect(Collectors.toList());
    }

    @GetMapping("/plays")
    public ResponseEntity<List<SingleGameResultResponse>> getAllGameResults() {
        final List<SingleGameResultResponse> responseBody = new ArrayList<>();

        final List<Integer> allPlayedGameIds = gameService.findAllPlayedGameIds();
        for (final int gameId : allPlayedGameIds) {
            final List<String> winners = gameService.findWinnersIn(gameId);
            final List<CarDto> allCarsIn = gameService.findAllCarsIn(gameId);
            responseBody.add(new SingleGameResultResponse(winners, allCarsIn));
        }

        return ResponseEntity.ok(responseBody);
    }

    @ExceptionHandler(GameException.class)
    private ResponseEntity<String> userInputExceptionHandler(final GameException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
