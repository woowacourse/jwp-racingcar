package racingcar.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.dto.CarResponse;
import racingcar.dto.GameRequest;
import racingcar.dto.GameResponse;
import racingcar.service.AddRaceService;
import racingcar.service.FindRaceService;

@RestController
public class RacingCarController {

    private static final String CAR_NAME_DELIMITER = ",";

    private final AddRaceService addRaceService;
    private final FindRaceService findRaceService;

    public RacingCarController(
            final AddRaceService addRaceService,
            final FindRaceService findRaceService) {
        this.addRaceService = addRaceService;
        this.findRaceService = findRaceService;
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResponse> raceAdd(@RequestBody @Valid final GameRequest gameRequest) {
        final RacingGame racingGame = addRaceService.addRace(gameRequest.getNames(), gameRequest.getCount());
        final GameResponse gameResponse = toGameResponse(racingGame);

        return ResponseEntity.ok(gameResponse);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<GameResponse>> raceList() {
        final List<RacingGame> racingGames = findRaceService.findAllRace();
        final List<GameResponse> gameResponses = racingGames.stream()
                .map(this::toGameResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(gameResponses);
    }

    private GameResponse toGameResponse(final RacingGame racingGame) {
        final String winners = racingGame.findWinner()
                .stream()
                .map(Car::getCarName)
                .collect(Collectors.joining(CAR_NAME_DELIMITER));
        final List<CarResponse> carResponses = racingGame.getCars()
                .stream()
                .map(car -> new CarResponse(car.getCarName(), car.getPosition()))
                .collect(Collectors.toList());

        return new GameResponse(winners, carResponses);
    }
}
