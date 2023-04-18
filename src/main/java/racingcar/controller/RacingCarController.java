package racingcar.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.CarPositionDto;
import racingcar.dto.CarResponse;
import racingcar.dto.GameRequest;
import racingcar.dto.GameResponse;
import racingcar.dto.RaceDto;
import racingcar.service.RacingCarsService;

@RestController
public class RacingCarController {

    private static final String CAR_NAME_DELIMITER = ",";

    private final RacingCarsService racingCarsService;

    public RacingCarController(final RacingCarsService racingCarsService) {
        this.racingCarsService = racingCarsService;
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResponse> raceAdd(@RequestBody @Valid final GameRequest gameRequest) {
        final RaceDto raceDto = racingCarsService.race(gameRequest.getNames(), gameRequest.getCount());
        final GameResponse gameResponse = toGameResponse(raceDto);

        return ResponseEntity.ok(gameResponse);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<GameResponse>> raceList() {
        final List<RaceDto> raceDtos = racingCarsService.findRaceResult();
        final List<GameResponse> gameResponses = raceDtos.stream()
                .map(this::toGameResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(gameResponses);
    }

    private GameResponse toGameResponse(final RaceDto raceDto) {
        final String winners = raceDto.getWinners()
                .stream()
                .map(CarPositionDto::getCarName)
                .collect(Collectors.joining(CAR_NAME_DELIMITER));
        final List<CarResponse> carResponses = raceDto.getCarPositionDtos()
                .stream()
                .map(carPositionDto -> new CarResponse(carPositionDto.getCarName(), carPositionDto.getStatus()))
                .collect(Collectors.toList());

        return new GameResponse(winners, carResponses);
    }
}
