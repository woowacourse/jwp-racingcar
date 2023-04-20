package racingcar.game.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    
    @GetMapping(path = "/plays")
    public ResponseEntity<List<GameResponseDTO>> retrieveAllGames() {
        final List<GameResponseDTO> gameResponseDTOs = this.racingCarGameService.retrieveAllGames();
        return ResponseEntity.ok().body(gameResponseDTOs);
    }
}
