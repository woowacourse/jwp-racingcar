package racingcar.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameInputDto;
import racingcar.dto.RacingResultDto;
import racingcar.game.Game;
import racingcar.util.RandomNumberGenerator;

@RestController
public class RacingGameController {

    @PostMapping(path = "/plays", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RacingResultDto> play(@RequestBody GameInputDto gameInput) {
        Game game = new Game(gameInput.getNames(), gameInput.getCount());
        game.playGameWithoutPrint(new RandomNumberGenerator());
        return ResponseEntity.ok(new RacingResultDto(game.getWinners(), game.getCars()));
    }
}
