package racingcar.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.strategy.move.RandomBasedMoveStrategy;
import racingcar.dto.*;
import racingcar.service.RacingCarService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WebRacingCarController {
    private final RacingCarService racingCarService;
    
    public WebRacingCarController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }
    
    @PostMapping(path = "/plays", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GameResponseDto> playGame(@RequestBody @Valid GameRequestDto gameRequestDto) {
        final String names = gameRequestDto.getNames();
        final String count = gameRequestDto.getCount();
        
        final GameOutputDto gameOutputDto = racingCarService.playGame(new GameInputDto(names, count), new RandomBasedMoveStrategy());
        final String winners = gameOutputDto.getWinners();
        final List<CarResponseDto> racingCars = gameOutputDto.getRacingCars();
        return ResponseEntity.ok(new GameResponseDto(winners, racingCars));
    }
    
    @GetMapping(path = "/plays", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GameResponseDto>> findAll() {
        return ResponseEntity.ok(parseGameResponseDtos(racingCarService.findAllGameResult()));
    }
    
    private List<GameResponseDto> parseGameResponseDtos(final List<GameOutputDto> gameOutputDtos) {
        return gameOutputDtos.stream()
                .map(gameOutputDto -> new GameResponseDto(gameOutputDto.getWinners(), gameOutputDto.getRacingCars()))
                .collect(Collectors.toUnmodifiableList());
    }
}
