package racingcar.controller;

import java.net.URI;
import java.util.ArrayList;
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
import racingcar.domain.Winners;
import racingcar.dto.CarResponse;
import racingcar.dto.GameResponse;
import racingcar.dto.PlayGameRequest;
import racingcar.service.RaceAddService;
import racingcar.service.RaceFindService;

@RestController
public class RacingCarController {

    private static final String CAR_NAME_DELIMITER = ",";

    private final RaceAddService raceAddService;
    private final RaceFindService raceFindService;

    public RacingCarController(
            final RaceAddService raceAddService,
            final RaceFindService raceFindService) {
        this.raceAddService = raceAddService;
        this.raceFindService = raceFindService;
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResponse> addRace(@RequestBody @Valid final PlayGameRequest playGameRequest) {
        final RacingGame racingGame = raceAddService.addRace(playGameRequest.getNames(), playGameRequest.getCount());
        final List<Car> winners = raceFindService.findWinners(racingGame).getCars();
        final GameResponse gameResponse = toGameResponse(racingGame, winners);

        return ResponseEntity.created(URI.create("/plays" + racingGame.getGameId())).body(gameResponse);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<GameResponse>> findAllRace() {
        final List<RacingGame> racingGames = raceFindService.findAllRace();
        final List<Winners> winners = racingGames.stream()
                .map(raceFindService::findWinners)
                .collect(Collectors.toList());

        final List<GameResponse> gameResponses = toGameResponses(racingGames, winners);
        return ResponseEntity.ok(gameResponses);
    }

    private List<GameResponse> toGameResponses(final List<RacingGame> racingGames, final List<Winners> winners) {
        final List<GameResponse> gameResponses = new ArrayList<>();
        for (int i = 0; i < racingGames.size(); i++) {
            final RacingGame racingGame = racingGames.get(i);
            final List<Car> winnerCars = winners.get(i).getCars();
            gameResponses.add(toGameResponse(racingGame, winnerCars));
        }
        return gameResponses;
    }

    private GameResponse toGameResponse(final RacingGame racingGame, final List<Car> winnerCars) {
        final String winners = winnerCars.stream()
                .map(Car::getCarName)
                .collect(Collectors.joining(CAR_NAME_DELIMITER));
        final List<CarResponse> carResponses = racingGame.getCars()
                .stream()
                .map(car -> new CarResponse(car.getCarName(), car.getPosition()))
                .collect(Collectors.toList());

        return new GameResponse(winners, carResponses);
    }
}
