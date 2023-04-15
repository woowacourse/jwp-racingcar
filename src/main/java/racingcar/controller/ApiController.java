package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.request.GamePlayDto;
import racingcar.dto.request.GameRequestDto;
import racingcar.dto.response.GameResponseDto;
import racingcar.service.GameService;

import java.net.URI;
import java.util.List;

@RestController
public class ApiController {
    private static final int MINIMUM_RANDOM_NUMBER = 0;
    private static final int MAXIMUM_RANDOM_NUMBER = 9;

    private final GameService gameService;

    @Autowired
    public ApiController(final GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping(value = "/plays", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GameResponseDto> playGame(@RequestBody GameRequestDto gameRequestDto) {
        List<String> carNames = List.of(gameRequestDto.getNames().split(","));
        GamePlayDto gamePlayDto = new GamePlayDto(carNames, gameRequestDto.getCount());
        final GameResponseDto gameResponseDto = gameService.playGame(gamePlayDto);

        return ResponseEntity.created(URI.create("/plays")).body(gameResponseDto);
    }

}
