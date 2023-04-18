package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.CarDto;
import racingcar.dto.GameResultDto;
import racingcar.dto.PlayRequestDto;
import racingcar.dto.PlayResultDto;
import racingcar.service.RacingCarService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RacingCarWebController {

    private final RacingCarService racingCarService;

    public RacingCarWebController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<PlayResultDto> play(@RequestBody PlayRequestDto playRequestDto) {
        final PlayResultDto result = racingCarService.play(playRequestDto);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<PlayResultDto>> getAllResult() {
        final List<GameResultDto> results = racingCarService.findAllResult();
        final List<PlayResultDto> allPlayResults = results.stream()
                .map(this::convertToPlayResult)
                .collect(Collectors.toList());

        return ResponseEntity.ok(allPlayResults);
    }

    private PlayResultDto convertToPlayResult(GameResultDto result) {
        final List<CarDto> cars = racingCarService.findCarsByResultId(result.getId()).stream()
                .map(CarDto::new)
                .collect(Collectors.toList());

        return new PlayResultDto(cars, result.getWinners());
    }
}
