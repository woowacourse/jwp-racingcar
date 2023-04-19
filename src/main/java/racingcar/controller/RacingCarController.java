package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import racingcar.dto.CarDto;
import racingcar.dto.MoveRequestDto;
import racingcar.dto.MoveResponseDto;
import racingcar.dto.PlayRequestDto;
import racingcar.dto.PlayResponseDto;
import racingcar.genertor.NumberGenerator;
import racingcar.service.RacingCarService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RacingCarController {
    private final RacingCarService racingCarService;
    private final NumberGenerator numberGenerator;

    @Autowired
    public RacingCarController(final RacingCarService racingCarService, final NumberGenerator numberGenerator) {
        this.racingCarService = racingCarService;
        this.numberGenerator = numberGenerator;
    }

    @PostMapping("/plays")
    public ResponseEntity<PlayResponseDto> play(@RequestBody PlayRequestDto playRequestDto) {
        final MoveResponseDto moveResponseDto = racingCarService.moveCar(makeMoveRequestDto(playRequestDto));
        return ResponseEntity.ok().body(makePlayResponseDto(moveResponseDto));
    }

    private PlayResponseDto makePlayResponseDto(final MoveResponseDto moveResponseDto) {
        final String winners = moveResponseDto.getWinners().stream()
                .map(CarDto::getName)
                .collect(Collectors.joining(","));
        return new PlayResponseDto(winners, moveResponseDto.getRacingCars());
    }

    private MoveRequestDto makeMoveRequestDto(PlayRequestDto playRequestDto) {
        final List<String> names = Arrays.asList(playRequestDto.getNames().split(","));
        return new MoveRequestDto(names, playRequestDto.getCount());
    }

    @GetMapping("/plays")
    public ResponseEntity<List<PlayResponseDto>> getPlayHistory() {
        return ResponseEntity.ok().body(racingCarService.findAllGameHistory());
    }
}
