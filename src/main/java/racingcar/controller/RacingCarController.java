package racingcar.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.RaceDto;
import racingcar.dto.CarPositionDto;
import racingcar.dto.CarResponse;
import racingcar.dto.GameRequest;
import racingcar.dto.GameResponse;
import racingcar.dto.GameHistoryResponse;
import racingcar.service.WebRacingGameService;

@RestController
public class RacingCarController {

    private static final String CAR_NAME_DELIMITER = ",";

    private final WebRacingGameService webRacingGameService;

    public RacingCarController(final WebRacingGameService webRacingGameService) {
        this.webRacingGameService = webRacingGameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResponse> plays(@RequestBody @Valid final GameRequest gameRequest) {
        final List<String> carNames = Arrays.asList(gameRequest.getNames().split(CAR_NAME_DELIMITER));
        final RaceDto raceDto = webRacingGameService.race(carNames, gameRequest.getCount());
        final GameResponse gameResponse = toGameResponse(raceDto);

        return new ResponseEntity<>(gameResponse, HttpStatus.OK);
    }

    @GetMapping("/plays")
    public ResponseEntity<GameHistoryResponse> findTotalGameHistory() {
        final List<RaceDto> totalGameHistory = webRacingGameService.findTotalGameHistory();
        final List<GameResponse> gameResponses = totalGameHistory.stream()
                .map(this::toGameResponse)
                .collect(Collectors.toList());

        return new ResponseEntity<>(new GameHistoryResponse(gameResponses), HttpStatus.OK);
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
