package racingcar.game.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.game.dto.GameRequestDTO;
import racingcar.game.dto.GameResponseDTO;
import racingcar.game.service.RacingCarGameService;

@RestController
public class WebController {
    
    private final RacingCarGameService racingCarGameService;
    
    public WebController(final RacingCarGameService racingCarGameService) {
        this.racingCarGameService = racingCarGameService;
    }
    
    @PostMapping(path = "/plays")
    public ResponseEntity<GameResponseDTO> play(@RequestBody final GameRequestDTO gameRequestDTO) {
        final GameResponseDTO gameResponseDTO = this.racingCarGameService.createGame(gameRequestDTO);
        return ResponseEntity.ok().body(gameResponseDTO);
    }
}
