package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.request.GameRequestDto;
import racingcar.dto.response.GameResponseDto;
import racingcar.service.GameService;

import java.net.URI;

@RestController
public class ApiController {

    private final GameService gameService;

    @Autowired
    public ApiController(final GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping(value = "/plays", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GameResponseDto> playGame(@RequestBody GameRequestDto request) {
        final GameResponseDto gameResponseDto = gameService.playGame(request);
        return ResponseEntity.created(URI.create("/plays")).body(gameResponseDto);
    }
}
